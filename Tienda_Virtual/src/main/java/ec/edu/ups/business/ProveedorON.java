package ec.edu.ups.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.dao.ProveedorDAO;
import ec.edu.ups.model.Proveedor;

@Stateless
public class ProveedorON {

	@Inject
	private ProveedorDAO provDAO;

	public void crearProveedor(Proveedor prov) {
		provDAO.create(prov);
	}

	public Proveedor read(int codigo) {
		return provDAO.read(codigo);
	}

	public void actualizarProveedor(Proveedor prov) {
		provDAO.update(prov);
	}

	public void eliminarProveedor(int codigo) {
		provDAO.delete(codigo);
	}

	public List<Proveedor> listadoProveedores() {
		return provDAO.listarProveedores();

	}

	public Proveedor readNombre(String nombre) {
		return provDAO.readNombre(nombre);
	}

}
