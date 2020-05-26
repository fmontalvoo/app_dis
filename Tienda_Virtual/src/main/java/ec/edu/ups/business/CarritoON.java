package ec.edu.ups.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.model.Carrito;
import ec.edu.ups.model.objetos.CarritoTmp;

@Stateless
public class CarritoON {

	@Inject
	private CarritoDAO carritoDAO;

	public void agregarCarrito(Carrito carrito) {
		Carrito c = new Carrito();
		c = buscarCarrito(carrito);
		if (c == null || (!carrito.isPagado())) {
			create(carrito);
		} else if ((!c.isPagado())) {
			c.setCantidad(carrito.getCantidad());
			update(c);
		}
	}

	public void create(Carrito carrito) {
		carritoDAO.create(carrito);
	}

	public Carrito read(int codigo) {
		return carritoDAO.read(codigo);
	}

	public void update(Carrito carrito) {
		carritoDAO.update(carrito);
	}

	public void delete(int codigo) {
		carritoDAO.delete(codigo);
	}

	public List<Carrito> listar() {
		return carritoDAO.listar();
	}

	public List<CarritoTmp> listarCarritosCliente(int codigo) {
		return carritoDAO.listarCarritosCliente(codigo);
	}

	public List<CarritoTmp> listarCarritosCliente(int codigo, boolean pagado) {
		return carritoDAO.listarCarritosCliente(codigo, pagado);
	}

	public Carrito buscarCarrito(Carrito carrito) {
		return carritoDAO.buscarCarrito(carrito);
	}

}
