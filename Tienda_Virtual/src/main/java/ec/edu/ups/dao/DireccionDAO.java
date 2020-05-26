package ec.edu.ups.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.model.Direccion;

@Stateless
public class DireccionDAO {

	@Inject
	private EntityManager em;

	public void create(Direccion dir) {
		em.persist(dir);

	}

	public Direccion read(int codigo) {
		// em.find() -> Solo funciona con el primary key.
		return em.find(Direccion.class, codigo);

	}

	public void update(Direccion dir) {
		em.merge(dir);
	}

	public void delete(int codigo) {
		em.remove(read(codigo));
	}

	public List<Direccion> listaDirecciones() {
		String jpql = "SELECT dir FROM Direccion dir";
		Query q = em.createQuery(jpql, Direccion.class);
		List<Direccion> direcciones = q.getResultList();
		return direcciones;

	}

	public Direccion readNombre(String nombre) {
		String jpql = "SELECT dir FROM Direccion dir " + "WHERE dir.callePrincipal LIKE ?1 "
				+ "OR dir.calleSecundaria LIKE ?1";
		Query q = em.createNativeQuery(jpql, Direccion.class);
		Direccion direccion = (Direccion) q.getSingleResult();
		return direccion;
	}

}
