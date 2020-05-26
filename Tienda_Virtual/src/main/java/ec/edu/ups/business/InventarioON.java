package ec.edu.ups.business;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.dao.InventarioCabeceraDAO;
import ec.edu.ups.dao.InventarioDetalleDAO;
import ec.edu.ups.model.InventarioCabecera;
import ec.edu.ups.model.InventarioDetalle;
import ec.edu.ups.model.objetos.InventarioCabeceraTmp;

@Stateless
public class InventarioON {

	@Inject
	private InventarioCabeceraDAO cabeceraDAO;
	@Inject
	private InventarioDetalleDAO detalleDAO;

	public void crearCabecera(InventarioCabecera cabecera) {
		cabeceraDAO.create(cabecera);
		for (InventarioDetalle detalle : cabecera.getDetalles()) {
			detalle.setCabecera(cabecera);
			detalle.setFechaRegistro(LocalDateTime.now());
			crearDetalle(detalle);
			long cantidadExistene = recuperarCantidad(detalle.getProducto().getCodigo());
			actualizarRegistros(detalle.getProducto().getCodigo(), (cantidadExistene + detalle.getStock()));
		}
	}

	public void crearDetalle(InventarioDetalle detalle) {
		detalleDAO.create(detalle);
	}

	public InventarioCabecera leerCabecera(int codigo) {
		return cabeceraDAO.read(codigo);
	}

	public InventarioDetalle leerDetalle(int codigo) {
		return detalleDAO.read(codigo);
	}

	public void actualizarCabecera(InventarioCabecera cabecera) {
		cabeceraDAO.update(cabecera);
	}

	public void actualizarDetalle(InventarioDetalle detalle) {
		detalleDAO.update(detalle);
	}

	public void delete(int codigo) {
		cabeceraDAO.delete(codigo);
		detalleDAO.eliminarRegistros(codigo);
	}

	public void eliminarCabecera(int codigo) {
		cabeceraDAO.delete(codigo);
	}

	public void eliminarDetalle(int codigo) {
		detalleDAO.delete(codigo);
	}

	public List<InventarioCabecera> listarCabecera() {
		return cabeceraDAO.listar();
	}

	public List<InventarioDetalle> listarDetalle() {
		return detalleDAO.listar();
	}

	public List<InventarioCabeceraTmp> listarCabeceras() {
		return cabeceraDAO.listarCabeceras();
	}

	public InventarioCabecera leerCabeceraPorCodigo(int codigo) {
		return cabeceraDAO.leerPorCodigo(codigo);
	}

	public void actualizarRegistros(int codigo, long cantidad) {
		detalleDAO.actualizarRegistros(codigo, cantidad);
	}

	public long recuperarCantidad(int codigo) {
		return detalleDAO.recuperarCantidad(codigo);
	}

}
