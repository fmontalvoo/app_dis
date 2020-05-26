package ec.edu.ups.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.model.DetalleFactura;
import ec.edu.ups.model.Factura;
import ec.edu.ups.model.objetos.ComprasTmp;
import ec.edu.ups.model.objetos.FacturaTmp;

@Stateless
public class FacturaDAO {

	@Inject
	private EntityManager em;

	public void create(Factura fact) {
		em.persist(fact);
	}

	public Factura read(int codigo) {
		return em.find(Factura.class, codigo);
	}

	public void update(Factura fact) {
		em.merge(fact);
	}

	public void delete(int codigo) {
		em.remove(read(codigo));
	}

	public List<Factura> listar() {
		String jpql = "SELECT fac FROM Factura fac";
		Query q = em.createQuery(jpql, Factura.class);
		List<Factura> facturas = q.getResultList();
		return facturas;
	}

	public List<FacturaTmp> listarPorCedula(String cedula) {
		String jpql = "SELECT f FROM Factura f JOIN f.cliente c WHERE c.cedula LIKE :cedula";
		Query q = em.createQuery(jpql, Factura.class);
		q.setParameter("cedula", cedula);
		List<Factura> facturas = q.getResultList();
		List<FacturaTmp> cabeceras = new ArrayList<>();
		for (Factura factura : facturas) {
			FacturaTmp facturaTmp = new FacturaTmp();
			facturaTmp.setCodigo(factura.getCodigo());
			facturaTmp.setFecha(factura.getFecha());
			facturaTmp.setTarjeta(factura.getTarjeta());
			facturaTmp.setSubtotal(factura.getSubtotal());
			facturaTmp.setIva(factura.getIva());
			facturaTmp.setTotal(factura.getTotal());
			cabeceras.add(facturaTmp);
		}
		return cabeceras;
	}

	public List<ComprasTmp> listarClientesQueCompraronMas() {
		String sql = "SELECT c.codigo, c.nombres, c.apellidos, count(f.codigo) FROM factura f, cliente c"
				+ "  WHERE c.codigo = f.cliente_codigo GROUP BY c.codigo, c.nombres, c.apellidos"
				+ "  ORDER BY count(f.codigo) DESC;";
		Query q = em.createNativeQuery(sql);
		List<ComprasTmp> compras = new ArrayList<>();
		List<Object[]> lista = q.getResultList();
		for (Object item[] : lista) {
			ComprasTmp ct = new ComprasTmp();
			ct.setCodigo(Integer.valueOf(item[0].toString()));
			ct.setNombre(item[1].toString());
			ct.setApellidos(item[2].toString());
			ct.setCompras(Integer.valueOf(item[3].toString()));
			compras.add(ct);
		}
		return compras;
	}

	public Factura leerPorCodigo(int codigo) {
		String jpql = "SELECT f FROM Factura f WHERE f.codigo = :codigo";
		Query query = em.createQuery(jpql, Factura.class);
		query.setParameter("codigo", codigo);
		Factura cabecera = (Factura) query.getSingleResult();
		List<DetalleFactura> aux = cabecera.getDetalles();
		List<DetalleFactura> detalles = new ArrayList<>();
		for (DetalleFactura df : aux) {
			df.setFactura(new Factura(cabecera.getCodigo()));
			detalles.add(df);
		}
		cabecera.setDetalles(detalles);
		return cabecera;
	}

}
