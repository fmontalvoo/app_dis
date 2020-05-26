package ec.edu.ups.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.model.Carrito;
import ec.edu.ups.model.Cliente;
import ec.edu.ups.model.Producto;
import ec.edu.ups.model.objetos.CarritoTmp;
import ec.edu.ups.model.objetos.ClienteTmp;

@Stateless
public class CarritoDAO {

	@Inject
	private EntityManager em;

	public void create(Carrito carrito) {
		em.persist(carrito);
	}

	public Carrito read(int codigo) {
		return em.find(Carrito.class, codigo);
	}

	public void update(Carrito carrito) {
		em.merge(carrito);
	}

	public void delete(int codigo) {
		em.remove(read(codigo));
	}

	public List<Carrito> listar() {
		String jpql = "SELECT c FROM Carrito c";
		Query q = em.createQuery(jpql, Carrito.class);
		List<Carrito> productos = q.getResultList();
		return productos;
	}

	public List<CarritoTmp> listarCarritosCliente(int codigo) {
		String jpql = "SELECT c FROM Carrito c WHERE c.cliente.codigo = :codigo";
		Query q = em.createQuery(jpql, Carrito.class);
		q.setParameter("codigo", codigo);
		List<Carrito> aux = q.getResultList();
		List<CarritoTmp> carritos = new ArrayList<>();
		for (Carrito carrito : aux) {
			Cliente c = carrito.getCliente();
			CarritoTmp ct = new CarritoTmp();
			ct.setCodigo(carrito.getCodigo());
			ct.setPagado(carrito.isPagado());
			ct.setCantidad(carrito.getCantidad());
			ct.setCliente(new ClienteTmp(c.getCodigo()));
			Producto p = new Producto();
			p.setCodigo(carrito.getProducto().getCodigo());
			p.setNombre(carrito.getProducto().getNombre());
			p.setPrecio(carrito.getProducto().getPrecio());
			ct.setProducto(p);
			carritos.add(ct);
		}
		return carritos;
	}

	public List<CarritoTmp> listarCarritosCliente(int codigo, boolean pagado) {
		String jpql = "SELECT c FROM Carrito c WHERE c.cliente.codigo = :codigo AND c.pagado = :pagado";
		Query q = em.createQuery(jpql, Carrito.class);
		q.setParameter("codigo", codigo);
		q.setParameter("pagado", pagado);
		List<Carrito> aux = q.getResultList();
		List<CarritoTmp> carritos = new ArrayList<>();
		for (Carrito carrito : aux) {
			Cliente c = carrito.getCliente();
			CarritoTmp ct = new CarritoTmp();
			ct.setCodigo(carrito.getCodigo());
			ct.setPagado(carrito.isPagado());
			ct.setCantidad(carrito.getCantidad());
			ct.setCliente(new ClienteTmp(c.getCodigo()));
			Producto p = new Producto();
			p.setCodigo(carrito.getProducto().getCodigo());
			p.setNombre(carrito.getProducto().getNombre());
			p.setPrecio(carrito.getProducto().getPrecio());
			ct.setProducto(p);
			carritos.add(ct);
		}
		return carritos;
	}

	public Carrito buscarCarrito(Carrito carrito) {
		try {
			String jpql = "SELECT c FROM Carrito c WHERE c.producto.codigo = :productoCodigo AND c.cliente.codigo = :clienteCodigo";
			Query q = em.createQuery(jpql, Carrito.class);
			q.setParameter("productoCodigo", carrito.getProducto().getCodigo());
			q.setParameter("clienteCodigo", carrito.getCliente().getCodigo());
			return (Carrito) q.getSingleResult();
		} catch (Exception e) {
		}
		return null;
	}

}
