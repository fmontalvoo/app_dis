package ec.edu.ups.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.model.Cliente;
import ec.edu.ups.model.Producto;
import ec.edu.ups.model.Voto;
import ec.edu.ups.model.objetos.ClienteTmp;
import ec.edu.ups.model.objetos.VotadosTmp;
import ec.edu.ups.model.objetos.VotoTmp;

@Stateless
public class VotoDAO {

	@Inject
	private EntityManager em;

	public void create(Voto voto) {
		em.persist(voto);
	}

	public Voto read(int codigo) {
		return em.find(Voto.class, codigo);
	}

	public void update(Voto voto) {
		em.merge(voto);
	}

	public void delete(int codigo) {
		em.remove(read(codigo));
	}

	public List<Voto> listar() {
		String jpql = "SELECT v FROM Voto v";
		Query q = em.createQuery(jpql, Voto.class);
		List<Voto> votos = q.getResultList();
		return votos;
	}

	public List<VotoTmp> listarWS() {
		String jpql = "SELECT v FROM Voto v";
		Query q = em.createQuery(jpql, Voto.class);
		List<Voto> aux = q.getResultList();
		List<VotoTmp> votos = new ArrayList<>();
		for (Voto voto : aux) {
			Cliente c = voto.getCliente();
			VotoTmp vt = new VotoTmp();
			vt.setCodigo(voto.getCodigo());
			vt.setEstado(voto.isEstado());
			vt.setCliente(new ClienteTmp(c.getCodigo()));
			vt.setProducto(new Producto(voto.getProducto().getCodigo()));
			votos.add(vt);
		}
		return votos;
	}

	public List<Voto> getVotosPorProducto(String nombre) {
		String jpql = "SELECT v FROM Voto v JOIN v.producto p WHERE UPPER(p.nombre) LIKE UPPER(:nombre)";
		Query q = em.createQuery(jpql, Voto.class);
		q.setParameter("nombre", "%" + nombre + "%");
		List<Voto> votos = q.getResultList();
		return votos;
	}

	public List<VotadosTmp> listarMasVotados() {
		String sql = "SELECT v.producto_codigo, p.nombre, count(v.producto_codigo) FROM voto v, producto p"
				+ "  WHERE v.producto_codigo = p.codigo"
				+ "   GROUP BY p.nombre, v.producto_codigo ORDER BY  count(v.producto_codigo) DESC;";
		Query q = em.createNativeQuery(sql);
		List<VotadosTmp> masVotados = new ArrayList<>();
		List<Object[]> lista = q.getResultList();
		for (Object item[] : lista) {
			VotadosTmp vt = new VotadosTmp();
			vt.setCodigo(Integer.valueOf(item[0].toString()));
			vt.setNombre(item[1].toString());
			vt.setVotos(Integer.valueOf(item[2].toString()));
			masVotados.add(vt);
		}
		return masVotados;
	}

	public Voto buscarVoto(Voto voto) {
		try {
			String jpql = "SELECT v FROM Voto v WHERE v.producto.codigo = :productoCodigo AND v.cliente.codigo = :clienteCodigo";
			Query q = em.createQuery(jpql, Voto.class);
			q.setParameter("productoCodigo", voto.getProducto().getCodigo());
			q.setParameter("clienteCodigo", voto.getCliente().getCodigo());
			return (Voto) q.getSingleResult();
		} catch (Exception e) {
		}
		return null;
	}
}
