package ec.edu.ups.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.model.Cliente;
import ec.edu.ups.model.objetos.Usuario;

@Stateless
public class ClienteDAO {

	@Inject
	private EntityManager em;

	public void create(Cliente c) {
		em.persist(c);

	}

	public Cliente read(int codigo) {
		return em.find(Cliente.class, codigo);

	}

	public void update(Cliente c) {
		em.merge(c);

	}

	public void delete(int codigo) {
		em.remove(read(codigo));
	}

	public List<Cliente> listar() {
		String jpql = "SELECT  c FROM Cliente c ";
		Query q = em.createQuery(jpql, Cliente.class);
		List<Cliente> clientes = q.getResultList();

		return clientes;

	}

	public List<Cliente> getByNombre(String filtroBusqueda) {
		String jpql = "SELECT c FROM Cliente c" + "WHERE c.nombre like :filtro";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter("filtro", "%" + filtroBusqueda + "%");

		List<Cliente> clientes = q.getResultList();
		System.err.println(clientes);
		return clientes;

	}

	public Cliente getUserbyEmailAndPassword(Usuario usuario) {
		String jpql = "SELECT c FROM Cliente c WHERE c.email LIKE :email AND c.clave LIKE :clave";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter("email", usuario.getEmail());
		q.setParameter("clave", usuario.getClave());
		Cliente cl = (Cliente) q.getSingleResult();
		return cl;
	}

	public Cliente getClientePorCedula(String cedula) {
		String jpql = "SELECT c FROM Cliente c WHERE c.cedula LIKE :cedula";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter("cedula", cedula);
		Cliente cliente = (Cliente) q.getSingleResult();
		return cliente;
	}

}
