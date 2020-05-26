package ec.edu.ups.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class InventarioDetalle {

	@Id
	@GeneratedValue
	private int codigo;
	private String descripcion;
	private long stock;
	private long cantidadExistente;
	private LocalDateTime fechaRegistro;
	@OneToOne
	private Producto producto;
	@OneToOne
	private InventarioCabecera cabecera;

	public InventarioDetalle() {
	}

	public InventarioDetalle(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public InventarioCabecera getCabecera() {
		return cabecera;
	}

	public void setCabecera(InventarioCabecera cabecera) {
		this.cabecera = cabecera;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public long getCantidadExistente() {
		return cantidadExistente;
	}

	public void setCantidadExistente(long cantidadExistente) {
		this.cantidadExistente = cantidadExistente;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
