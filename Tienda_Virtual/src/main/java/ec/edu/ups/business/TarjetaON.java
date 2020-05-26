package ec.edu.ups.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.dao.TarjetaDAO;
import ec.edu.ups.model.Tarjeta;

@Stateless
public class TarjetaON {

	@Inject
	private TarjetaDAO tarjetaDAO;

	public void create(Tarjeta tar) {
		tarjetaDAO.create(tar);
	}

	public Tarjeta read(int codigo) {
		return tarjetaDAO.read(codigo);

	}

	public void update(Tarjeta tar) {
		tarjetaDAO.update(tar);
	}

	public void delete(int codigo) {
		tarjetaDAO.delete(codigo);
	}

	public List<Tarjeta> listaTarjetas() {
		return tarjetaDAO.listaTarjetas();
	}

	public List<Tarjeta> listaTarjetasCliente(String cedula) {
		return tarjetaDAO.listaTarjetasCliente(cedula);
	}

	public List<Tarjeta> listaTarjetasCliente(int codigo) {
		return tarjetaDAO.listaTarjetasCliente(codigo);
	}

}
