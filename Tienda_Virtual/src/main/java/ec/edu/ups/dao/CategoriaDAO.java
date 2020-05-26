package ec.edu.ups.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.model.Categoria;

@Stateless
public class CategoriaDAO {

	@Inject
	private EntityManager em;

	public void create(Categoria categoria) {
		em.persist(categoria);
	}

	public Categoria read(int codigo) {
		return em.find(Categoria.class, codigo);
	}

	public void update(Categoria categoria) {
		em.merge(categoria);
	}

	public void delete(int codigo) {
		// Categoria cat = read(id);
		em.remove(read(codigo));
	}

	public Categoria buscarCategoria(int codigo) {
		String jpql = "SELECT cat from Categoria cat JOIN FETCH  cat WHERE cat.codigo =:codigo";
		Query q = em.createQuery(jpql, Categoria.class);
		q.setParameter("codigo", codigo);
		Categoria categoria = (Categoria) q.getResultList();
		return categoria;
	}

	public List<Categoria> listarCategorias() {
		String jpql = "SELECT cat FROM Categoria cat ";
		Query q = em.createQuery(jpql, Categoria.class);
		List<Categoria> categorias = q.getResultList();
//		for (Categoria cat : categorias) {
//			cat.getProductos().size();
//		}

		return categorias;
	}

	public Categoria readNombre(String nombre) {
		String jpql = "SELECT cat FROM Categoria cat" + "WHERE cat.nombre LIKE ?1";
		Query q = em.createNativeQuery(jpql, Categoria.class);
		Categoria categoria = (Categoria) q.getSingleResult();
		return categoria;

	}

}
