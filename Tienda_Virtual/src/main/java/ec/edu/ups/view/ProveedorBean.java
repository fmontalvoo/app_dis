package ec.edu.ups.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.business.ProveedorON;
import ec.edu.ups.model.Proveedor;

@ManagedBean
@ViewScoped
public class ProveedorBean {

	@Inject
	private ProveedorON proveedorON;
	@Inject
	private FacesContext facesContext;

	private Proveedor proveedor;
	private List<Proveedor> proveedores;

	private int codigo;
	private boolean editing;

	@PostConstruct
	public void init() {
		proveedor = new Proveedor();
		editing = false;
		recuperarDatos();
	}

	/**
	 * 
	 */
	public void loadDataProveedores() {
		if (codigo == 0)
			return;
		try {
			proveedor = proveedorON.read(codigo);
			editing = true;
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}

	}

	/**
	 * 
	 * @return
	 */
	public String guardar() {
		if (editing)
			proveedorON.actualizarProveedor(proveedor);
		else
			proveedorON.crearProveedor(proveedor);
		return listar();
	}

	/**
	 * 
	 * @param
	 * @return
	 */
	public String editar(Proveedor _proveedor) {
		return "proveedor?faces-redirect=true&codigo=" + _proveedor.getCodigo();
	}

	public String eliminar(Proveedor _proveedor) {
		proveedorON.eliminarProveedor(_proveedor.getCodigo());
		return crear();
	}

	/**
	 * 
	 */
	public void recuperarDatos() {
		proveedores = new ArrayList<Proveedor>();
		proveedores = proveedorON.listadoProveedores();
	}

	/**
	 * 
	 * @return
	 */
	public String crear() {
		return "proveedor";
	}

	/**
	 * 
	 * @return
	 */
	public String listar() {
		return "lista-proveedor";

	}

	/**
	 * 
	 * Gets y Sets
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
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
