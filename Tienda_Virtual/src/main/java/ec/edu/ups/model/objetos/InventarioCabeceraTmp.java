package ec.edu.ups.model.objetos;

import java.util.Date;

public class InventarioCabeceraTmp {

	private int codigo;
	private Date fecha;
	private String descripcion;

	public InventarioCabeceraTmp() {
	}

	public InventarioCabeceraTmp(int codigo, Date fecha, String descripcion) {
		this.codigo = codigo;
		this.fecha = fecha;
		this.descripcion = descripcion;
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

}
