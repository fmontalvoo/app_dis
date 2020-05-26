package ec.edu.ups.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.business.DireccionON;
import ec.edu.ups.model.Direccion;

@ManagedBean
@ViewScoped
public class DireccionBean {

	@Inject
	private DireccionON direccionON;
	@Inject
	private FacesContext facesContext;

	private Direccion direccion;
	private List<Direccion> direcciones;
	private int codigo;
	private boolean editing;

	@PostConstruct
	public void init() {
		// IMPORTANTE
		// Inicializar instancias o variables
		editing = false;
		direccion = new Direccion();
		recuperarRegistros();
	}

	/**
	 * Recupera el codigo de la direccion para con este recuperar la
	 * misma(direccion) desde la base de datos.
	 */
	public void cargarDatosDireccion() {
		if (codigo == 0)
			return;
		try {
			direccion = direccionON.read(codigo);
			editing = true;
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
	}

	/**
	 * Registra o edita una direccion.
	 */
	public String guardar() {
		if (editing)
			direccionON.update(direccion);
		else
			direccionON.create(direccion);
		return listar();
	}

	/**
	 * Redirige a la pagina de direccion con el codigo de la direccion en la URL
	 * para su edicion.
	 * 
	 * @param _direccion
	 * @return
	 */
	public String editar(Direccion _direccion) {
		return "direccion?faces-redirect=true&codigo=" + _direccion.getCodigo();
	}

	/**
	 * Elimina una direccion de la base de datos.
	 * 
	 * @param _direccion
	 * @return
	 */
	public String eliminar(Direccion _direccion) {
		direccionON.delete(_direccion.getCodigo());
		return crear();
	}

	/**
	 * Recupera un listado con todas las direccion almacenadas en la base de datos.
	 */
	public void recuperarRegistros() {
		direcciones = new ArrayList<Direccion>();
		direcciones = direccionON.listaDirecciones();
	}

	/**
	 * Redirige a la pagina de "direccion/direccion".
	 * 
	 * @return
	 */
	public String crear() {
		return "direccion";
	}

	/**
	 * Redirige a la pagina de "direccion/lista-direccion".
	 * 
	 * @return
	 */
	public String listar() {
		return "lista-direccion";
	}

	// Generamos getters and setters
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public List<Direccion> getDirecciones() {
		return this.direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

}
