package ec.edu.ups.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.model.Producto;

@Stateless
public class ProductoON {

	@Inject
	private ProductoDAO productoDAO;

	public void create(Producto producto) {
		productoDAO.create(producto);
	}

	public Producto read(int codigo) {
		return productoDAO.read(codigo);
	}

	public void update(Producto producto) {
		productoDAO.update(producto);
	}

	public void delete(int codigo) {
		productoDAO.delete(codigo);
	}

	public List<Producto> listar() {
		return productoDAO.listar();
	}

	public List<Producto> listarPorNombre(String nombre) {
		return productoDAO.listarPorNombre(nombre);
	}

}
