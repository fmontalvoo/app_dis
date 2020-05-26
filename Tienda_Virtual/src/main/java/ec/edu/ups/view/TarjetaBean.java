package ec.edu.ups.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.business.TarjetaON;
import ec.edu.ups.model.Cliente;
import ec.edu.ups.model.Tarjeta;

@ManagedBean
@ViewScoped
public class TarjetaBean {

	@Inject
	private TarjetaON tarjetaON;
	@Inject
	private FacesContext facesContext;
	private Tarjeta tarjeta;
	private List<Tarjeta> tarjetas;

	private Cliente cliente;
	@Inject
	private ClienteBean clienteBean;

	private int codigo;
	private boolean editing;

	@PostConstruct
	public void init() {
		tarjeta = new Tarjeta();
		editing = false;
		cliente = new Cliente();
	}

	public void loadDataTarjeta() {
		if (codigo == 0)
			return;
		try {
			tarjeta = tarjetaON.read(codigo);
			cliente = clienteBean.read(tarjeta.getCliente().getCodigo());
			editing = true;

		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}

	}

	public void recuperarRegistros() {

		tarjetas = new ArrayList<>();
		tarjetas = tarjetaON.listaTarjetas();
	}

	public String guardar() {
		tarjeta.setCliente(cliente);
		if (editing) {
			tarjetaON.update(tarjeta);
		} else
			tarjetaON.create(tarjeta);
		return listar();
	}

	public String editar(Tarjeta _tarjeta) {
		return "tarjeta?faces-redirect=true&codigo=" + _tarjeta.getCodigo();
	}

	public String eliminar(Tarjeta _tarjeta) {
		tarjetaON.delete(_tarjeta.getCodigo());
		return crear();
	}

	public void consultarCliente() {
		cliente = clienteBean.getClientesPorCedula(cliente.getCedula());
	}

	public String consultaTarjetasCliente() {
		tarjetas = tarjetaON.listaTarjetasCliente(cliente.getCedula());
		return null;
	}

	public String crear() {
		return "tarjeta";
	}

	public String listar() {
		return "lista-tarjeta";
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}
