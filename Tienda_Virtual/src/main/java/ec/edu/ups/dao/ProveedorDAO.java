package ec.edu.ups.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.model.Proveedor;

@Stateless
public class ProveedorDAO {

	@Inject
	private EntityManager em;

	public void create(Proveedor prov) {
		em.persist(prov);
	}

	public Proveedor read(int codigo) {
		return em.find(Proveedor.class, codigo);
	}

	public void update(Proveedor prov) {
		if (read(prov.getCodigo()) != null)
			em.merge(prov);
		else
			System.out.println("No existe ese registro!");
	}

	public void delete(int codigo) {
		// Proveedor prov = read(codigo);
		em.remove(read(codigo));
	}

	public List<Proveedor> listarProveedores() {
		String jpql = "SELECT prov FROM Proveedor prov ";
		Query q = em.createQuery(jpql, Proveedor.class);
		List<Proveedor> proveedores = q.getResultList();
		return proveedores;
	}

	public Proveedor readNombre(String nombre) {
		String jpql = "SELECT prov FROM Proveedor prov" + "WHERE prov.nombre LIKE ?1";
		Query q = em.createNativeQuery(jpql, Proveedor.class);
		Proveedor proveedor = (Proveedor) q.getSingleResult();
		return proveedor;

	}

}
