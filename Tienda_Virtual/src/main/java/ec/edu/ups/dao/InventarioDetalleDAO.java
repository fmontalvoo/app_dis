package ec.edu.ups.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.model.InventarioDetalle;

@Stateless
public class InventarioDetalleDAO {

	@Inject
	private EntityManager em;

	public void create(InventarioDetalle detalle) {
		em.persist(detalle);

	}

	public InventarioDetalle read(int codigo) {
		return em.find(InventarioDetalle.class, codigo);
	}

	public void update(InventarioDetalle detalle) {
		em.merge(detalle);
	}

	public void delete(int codigo) {
		em.remove(read(codigo));
	}

	public List<InventarioDetalle> listar() {
		String jpql = "SELECT id FROM InventarioDetalle id";
		Query query = em.createQuery(jpql, InventarioDetalle.class);
		List<InventarioDetalle> detalles = query.getResultList();
		return detalles;
	}

	public long recuperarCantidad(int codigo) {
		String jpql = "SELECT id FROM InventarioDetalle id "
				+ "WHERE id.producto.codigo = :codigo ORDER BY id.fechaRegistro DESC";
		Query query = em.createQuery(jpql, InventarioDetalle.class);
		query.setParameter("codigo", codigo);
		List<InventarioDetalle> detalles = query.getResultList();
		for (InventarioDetalle inventarioDetalle : detalles) {
			System.out.println(inventarioDetalle.getDescripcion() + " | " + inventarioDetalle.getFechaRegistro());
		}
		long cantidadExistente = detalles.get(detalles.size() - 1).getCantidadExistente();
		if (!detalles.isEmpty())
			return cantidadExistente;
		else
			return 0;
	}

	public void actualizarRegistros(int codigo, long cantidad) {
		String sql = "UPDATE inventariodetalle SET cantidadexistente = :cantidad WHERE producto_codigo = :codigo";
		Query query = em.createNativeQuery(sql);
		query.setParameter("cantidad", cantidad);
		query.setParameter("codigo", codigo);
		query.executeUpdate();
	}

	public void eliminarRegistros(int codigo) {
		String sql = "DELETE FROM inventariodetalle WHERE cabecera_codigo = :codigo";
		Query query = em.createNativeQuery(sql);
		query.setParameter("codigo", codigo);
		query.executeUpdate();
	}

}
