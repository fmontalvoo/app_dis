package ec.edu.ups.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.UploadedFile;

import ec.edu.ups.business.ProductoON;
import ec.edu.ups.model.Categoria;
import ec.edu.ups.model.Producto;
import ec.edu.ups.model.Proveedor;

@ManagedBean
@ViewScoped
public class ProductoBean {

	@Inject
	private ProductoON productoON;
	@Inject
	private ProveedorBean proveedorBean;
	@Inject
	private CategoriaBean categoriaBean;
	@Inject
	private FacesContext facesContext;

	private int codigo;
	private boolean editing;
	private Producto producto;
	private List<Producto> productos;
	private List<Categoria> categorias;
	private List<Proveedor> proveedores;
	private int codigoCategoria;
	private int codigoProveedor;
	private UploadedFile file;
	private String nombre;

	@PostConstruct
	public void init() {
		editing = false;
		producto = new Producto();
		recuperarRegistros();
		categoriaBean.recuperarDatos();
		categorias = categoriaBean.getCategorias();
		proveedorBean.recuperarDatos();
		proveedores = proveedorBean.getProveedores();

	}

	public void cargarDatosProducto() {
		if (codigo == 0)
			return;
		try {
			producto = productoON.read(codigo);
			codigoCategoria = producto.getCategoria().getCodigo();
			codigoProveedor = producto.getProveedor().getCodigo();
			editing = true;
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
	}

	public String guardar() {
		uploadFile();
		producto.setCategoria(new Categoria(codigoCategoria));
		producto.setProveedor(new Proveedor(codigoProveedor));
		if (editing) {
			productoON.update(producto);
		} else {
			productoON.create(producto);
		}
		producto = new Producto();
		return listar();
	}

	public String editar(Producto _producto) {
		return "producto?faces-redirect=true&codigo=" + _producto.getCodigo();
	}

	public String eliminar(Producto _producto) {
		productoON.delete(_producto.getCodigo());
		return crear();
	}

	public void uploadFile() {
		if (file != null) {
			byte[] contenido = file.getContents();
			String nombre = file.getFileName();
			producto.setImagen(contenido);
		}
	}

	public void recuperarRegistros() {
		productos = new ArrayList<>();
		productos = productoON.listar();
	}

	public String listarProductos() {
		if (!nombre.isEmpty())
			productos = productoON.listarPorNombre(nombre);
		else
			recuperarRegistros();
		return null;
	}

	public List<Producto> listarPorNombre(String nombre) {
		return productoON.listarPorNombre(nombre);
	}

	public String crear() {
		return "producto";
	}

	public String listar() {
		return "lista-producto";
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public int getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(int codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public int getCodigoProveedor() {
		return codigoProveedor;
	}

	public void setCodigoProveedor(int codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
