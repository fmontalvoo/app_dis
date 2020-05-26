package ec.edu.ups.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.business.CategoriaON;
import ec.edu.ups.model.Categoria;

@ManagedBean
@ViewScoped
public class CategoriaBean {

	@Inject
	private CategoriaON categoriaON;
	@Inject
	private FacesContext facesContext;

	private Categoria categoria;

	private List<Categoria> categorias;

	private int codigo;
	private boolean editing;

	@PostConstruct
	public void init() {
		editing = false;
		categoria = new Categoria();
		recuperarDatos();
	}

	/**
	 * 
	 */
	public void loadDataCategorias() {
		if (codigo == 0)
			return;
		try {
			categoria = categoriaON.read(codigo);
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
			categoriaON.actualizarCategoria(categoria);
		else
			categoriaON.crearCategoria(categoria);
		return listar();
	}

	/**
	 * 
	 * @param _categoria
	 * @return
	 */
	public String editar(Categoria _categoria) {
		return "categoria?faces-redirect=true&codigo=" + _categoria.getCodigo();
	}

	public String eliminar(Categoria _categoria) {
		categoriaON.eliminarCategoria(_categoria.getCodigo());
		return crear();
	}

	/**
	 * 
	 */
	public void recuperarDatos() {
		categorias = new ArrayList<Categoria>();
		categorias = categoriaON.listaCategorias();
	}

	/**
	 * 
	 * @return
	 */
	public String crear() {
		return "categoria";
	}

	/**
	 * 
	 * @return
	 */
	public String listar() {
		return "lista-categoria";
	}

	/**
	 * 
	 * Gets y Sets
	 * 
	 */

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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
