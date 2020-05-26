package ec.edu.ups.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.model.Producto;

@Stateless
public class ProductoDAO {

	@Inject
	private EntityManager em;

	public void create(Producto producto) {
		em.persist(producto);
	}

	public Producto read(int codigo) {
		return em.find(Producto.class, codigo);
	}

	public void update(Producto producto) {
		em.merge(producto);
	}

	public void delete(int codigo) {
		em.remove(read(codigo));
	}

	public List<Producto> listar() {
		String jpql = "Select p from Producto p";
		Query query = em.createQuery(jpql, Producto.class);
		List<Producto> productos = query.getResultList();
		return productos;
	}

	public List<Producto> listarPorNombre(String nombre) {
		String jpql = "Select p from Producto p WHERE UPPER(p.nombre) LIKE UPPER(:nombre)";
		Query query = em.createQuery(jpql, Producto.class);
		query.setParameter("nombre", "%" + nombre + "%");
		List<Producto> productos = query.getResultList();
		return productos;
	}

}
