package ec.edu.ups.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.model.DetalleFactura;
import ec.edu.ups.model.objetos.VendidosTmp;

@Stateless
public class DetalleFacturaDAO {

	@Inject
	private EntityManager em;

	public void create(DetalleFactura detalleFactura) {
		em.persist(detalleFactura);
	}

	public DetalleFactura read(int codigo) {
		return em.find(DetalleFactura.class, codigo);
	}

	public void update(DetalleFactura detalleFactura) {
		em.merge(detalleFactura);
	}

	public void delete(int codigo) {
		em.remove(read(codigo));
	}

	public List<DetalleFactura> listar() {
		String jpql = "SELECT df FROM DetalleFactura df";
		Query q = em.createQuery(jpql, DetalleFactura.class);
		List<DetalleFactura> detalles = q.getResultList();
		return detalles;
	}

	public List<VendidosTmp> listarMasVendidos() {
		String sql = "SELECT df.producto_codigo, p.nombre, count(df.producto_codigo), sum(df.cantidad)"
				+ "  FROM  detallefactura df, producto p WHERE p.codigo = df.producto_codigo"
				+ "  GROUP BY df.producto_codigo, p.nombre ORDER BY  sum(df.cantidad) DESC;";
		Query q = em.createNativeQuery(sql);
		List<VendidosTmp> masVendidos = new ArrayList<>();
		List<Object[]> lista = q.getResultList();
		for (Object item[] : lista) {
			VendidosTmp vt = new VendidosTmp();
			vt.setCodigo(Integer.valueOf(item[0].toString()));
			vt.setNombre(item[1].toString());
			vt.setCantidad(Integer.valueOf(item[3].toString()));
			masVendidos.add(vt);
		}
		return masVendidos;
	}

}
