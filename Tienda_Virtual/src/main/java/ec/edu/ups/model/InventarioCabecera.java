package ec.edu.ups.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class InventarioCabecera {

	@Id
	@GeneratedValue
	private int codigo;
	private Date fecha;
	private String descripcion;
	@OneToMany(mappedBy = "cabecera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<InventarioDetalle> detalles;

	public InventarioCabecera() {
	}

	public InventarioCabecera(int codigo) {
		this.codigo = codigo;
	}

	public InventarioCabecera(int codigo, Date fecha, String descripcion, List<InventarioDetalle> detalles) {

		this.codigo = codigo;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.detalles = detalles;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<InventarioDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<InventarioDetalle> detalles) {
		this.detalles = detalles;
	}

}
