package ec.edu.ups.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.dao.DireccionDAO;
import ec.edu.ups.model.Direccion;

@Stateless
public class DireccionON {

	@Inject
	private DireccionDAO direccionDAO;

	public void create(Direccion direccion) {
		direccionDAO.create(direccion);

	}

	public Direccion read(int codigo) {
		return direccionDAO.read(codigo);

	}

	public void update(Direccion direccion) {
		direccionDAO.update(direccion);
	}

	public void delete(int codigo) {
		direccionDAO.delete(codigo);
	}

	public List<Direccion> listaDirecciones() {
		return direccionDAO.listaDirecciones();

	}

	public Direccion readNombre(String nombre) {
		return direccionDAO.readNombre(nombre);
	}

}
