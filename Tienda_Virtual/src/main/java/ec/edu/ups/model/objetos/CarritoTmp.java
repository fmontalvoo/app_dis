package ec.edu.ups.model.objetos;

import java.io.Serializable;

import ec.edu.ups.model.Producto;

public class CarritoTmp implements Serializable {

	private int codigo;
	private ClienteTmp cliente;
	private Producto producto;
	private int cantidad;
	private boolean pagado;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public ClienteTmp getCliente() {
		return cliente;
	}

	public void setCliente(ClienteTmp cliente) {
		this.cliente = cliente;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

}
