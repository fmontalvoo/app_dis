package ec.edu.ups.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.model.Tarjeta;

@Stateless
public class TarjetaDAO {

	@Inject
	private EntityManager em;

	public void create(Tarjeta tar) {
		em.persist(tar);
	}

	public Tarjeta read(int codigo) {
		return em.find(Tarjeta.class, codigo);

	}

	public void update(Tarjeta tar) {
		em.merge(tar);
	}

	public void delete(int codigo) {
		em.remove(read(codigo));

	}

	public List<Tarjeta> listaTarjetas() {

		String jpql = "SELECT tar FROM Tarjeta tar";
		Query q = em.createNativeQuery(jpql, Tarjeta.class);
		List<Tarjeta> tarjetas = q.getResultList();
		return tarjetas;
	}

	public List<Tarjeta> listaTarjetasCliente(String cedula) {
		String jpql = "SELECT tar FROM Tarjeta tar JOIN tar.cliente c WHERE c.cedula LIKE :cedula";
		Query q = em.createQuery(jpql, Tarjeta.class);
		q.setParameter("cedula", cedula);
		List<Tarjeta> tarjetas = q.getResultList();
		return tarjetas;
	}

	public List<Tarjeta> listaTarjetasCliente(int codigo) {
		String jpql = "SELECT tar FROM Tarjeta tar JOIN tar.cliente c WHERE c.codigo = :codigo";
		Query q = em.createQuery(jpql, Tarjeta.class);
		q.setParameter("codigo", codigo);
		List<Tarjeta> tarjetas = q.getResultList();
		return tarjetas;
	}

}
