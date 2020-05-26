package ec.edu.ups.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.edu.ups.business.CarritoON;
import ec.edu.ups.model.Carrito;
import ec.edu.ups.model.Cliente;

@ManagedBean
@ViewScoped
public class CarritoBean {

	@Inject
	private CarritoON carritoON;
	@Inject
	private ClienteBean clienteBean;
	private List<Carrito> carritos;
	private Cliente cliente;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
	}

	public void consultarCliente() {
		cliente = clienteBean.getClientesPorCedula(cliente.getCedula());
		carritos = cliente.getCarritos();
	}

	public List<Carrito> getCarritos() {
		return carritos;
	}

	public void setCarritos(List<Carrito> carritos) {
		this.carritos = carritos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
