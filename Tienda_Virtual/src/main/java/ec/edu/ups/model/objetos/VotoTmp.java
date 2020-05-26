package ec.edu.ups.model.objetos;

import ec.edu.ups.model.Producto;

public class VotoTmp {
	private int codigo;
	private boolean estado;
	private ClienteTmp cliente;
	private Producto producto;

	public VotoTmp() {
	}

	public VotoTmp(int codigo, boolean estado, ClienteTmp cliente, Producto producto) {
		this.codigo = codigo;
		this.estado = estado;
		this.cliente = cliente;
		this.producto = producto;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
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

}
