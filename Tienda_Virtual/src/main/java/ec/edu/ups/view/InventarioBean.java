package ec.edu.ups.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.business.InventarioON;
import ec.edu.ups.model.InventarioCabecera;
import ec.edu.ups.model.InventarioDetalle;
import ec.edu.ups.model.Producto;
import ec.edu.ups.model.objetos.InventarioCabeceraTmp;

@ManagedBean
@ViewScoped
public class InventarioBean {

	@Inject
	private InventarioON inventarioON;
	@Inject
	private ProductoBean productoBean;
	@Inject
	private FacesContext facesContext;

	private int codigo;
	private boolean editing;

	private Producto producto;
	private InventarioCabecera cabecera;
	private InventarioDetalle detalle;

	private List<Producto> productos;
	private List<InventarioCabeceraTmp> cabeceras;
	private List<InventarioDetalle> detalles;

	private String nombreProducto;
	private long cantidadProducto;
	private String descripcion;

	@PostConstruct
	public void init() {
		editing = false;
		producto = new Producto();
		detalle = new InventarioDetalle();
		cabecera = new InventarioCabecera();
		productos = new ArrayList<>();
		recuperarRegistros();
	}

	public void cargarDatosInventario() {
		if (codigo == 0)
			return;
		try {
			cabecera = inventarioON.leerCabeceraPorCodigo(codigo);
			detalles = cabecera.getDetalles();
			editing = true;
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
	}

	public void recuperarRegistros() {
		cabeceras = new ArrayList<>();
		cabecera.setFecha(new Date());
		detalles = new ArrayList<>();
		cabeceras = inventarioON.listarCabeceras();
	}

	public String listarProductos() {
		if (!nombreProducto.isEmpty())
			productos = productoBean.listarPorNombre(nombreProducto);
		return null;
	}

	public void agregarProducto(Producto _producto, long cantidad, String descripcion) {
		InventarioDetalle invDet = new InventarioDetalle();
		invDet.setProducto(_producto);
		invDet.setStock(cantidad);
		invDet.setDescripcion(descripcion);
		detalles.add(invDet);

	}

	public void guardar() {
		cabecera.setDetalles(detalles);
		if (editing)
			inventarioON.actualizarCabecera(cabecera);
		else
			inventarioON.crearCabecera(cabecera);
	}

	public String editar(InventarioCabeceraTmp _cabecera) {
		return "inventario?faces-redirect=true&codigo=" + _cabecera.getCodigo();
	}

	public String eliminar(InventarioCabeceraTmp _cabecera) {
		inventarioON.delete(_cabecera.getCodigo());
		return crear();
	}

	public String crear() {
		return "inventario";
	}

	public String listar() {
		return "lista-inventario";
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

	public InventarioCabecera getCabecera() {
		return cabecera;
	}

	public void setCabecera(InventarioCabecera cabecera) {
		this.cabecera = cabecera;
	}

	public InventarioDetalle getDetalle() {
		return detalle;
	}

	public void setDetalle(InventarioDetalle detalle) {
		this.detalle = detalle;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public List<InventarioCabeceraTmp> getCabeceras() {
		return cabeceras;
	}

	public void setCabeceras(List<InventarioCabeceraTmp> cabeceras) {
		this.cabeceras = cabeceras;
	}

	public List<InventarioDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<InventarioDetalle> detalles) {
		this.detalles = detalles;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public long getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(long cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
