package ec.edu.ups.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.model.InventarioCabecera;
import ec.edu.ups.model.InventarioDetalle;
import ec.edu.ups.model.objetos.InventarioCabeceraTmp;

@Stateless
public class InventarioCabeceraDAO {

	@Inject
	private EntityManager em;

	public void create(InventarioCabecera cabecera) {
		em.persist(cabecera);

	}

	public InventarioCabecera read(int codigo) {
		return em.find(InventarioCabecera.class, codigo);

	}

	public void update(InventarioCabecera cabecera) {
		em.merge(cabecera);
	}

	public void delete(int codigo) {
		em.remove(read(codigo));
	}

	public List<InventarioCabecera> listar() {
		String jpql = "SELECT ic FROM InventarioCabecera ic ";
		Query q = em.createQuery(jpql, InventarioCabecera.class);
		List<InventarioCabecera> cabeceras = q.getResultList();
		return cabeceras;
	}

	public InventarioCabecera leerPorCodigo(int codigo) {
		String jpql = "SELECT ic FROM InventarioCabecera ic WHERE ic.codigo = :codigo";
		Query query = em.createQuery(jpql, InventarioCabecera.class);
		query.setParameter("codigo", codigo);
		InventarioCabecera cabecera = (InventarioCabecera) query.getSingleResult();
		List<InventarioDetalle> aux = cabecera.getDetalles();
		List<InventarioDetalle> detalles = new ArrayList<>();
		for (InventarioDetalle id : aux) {
			id.setCabecera(new InventarioCabecera(cabecera.getCodigo()));
			detalles.add(id);
		}
		cabecera.setDetalles(detalles);
		return cabecera;
	}

	public List<InventarioCabeceraTmp> listarCabeceras() {
		String jpql = "SELECT ic FROM InventarioCabecera ic ";
		Query q = em.createQuery(jpql, InventarioCabecera.class);
		List<InventarioCabeceraTmp> cabeceras = new ArrayList<>();
		List<InventarioCabecera> lista = q.getResultList();
		for (InventarioCabecera ic : lista) {
			cabeceras.add(new InventarioCabeceraTmp(ic.getCodigo(), ic.getFecha(), ic.getDescripcion()));
		}
		return cabeceras;
	}

}
