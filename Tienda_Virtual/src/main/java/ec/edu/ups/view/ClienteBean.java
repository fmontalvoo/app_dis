package ec.edu.ups.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.business.ClienteON;
import ec.edu.ups.model.Cliente;
import ec.edu.ups.model.Direccion;
import ec.edu.ups.model.objetos.Usuario;

@ManagedBean
@ViewScoped
public class ClienteBean {

	private int codigo;
	private boolean editing;
	private Cliente cliente;
	private List<Cliente> clientes;
	private List<Direccion> direcciones;
	private int codigoDireccion;

	@Inject
	private ClienteON clienteON;
	@Inject
	private FacesContext facesContext;
	@Inject
	private DireccionBean direccionBean;

	@PostConstruct
	public void init() {
		editing = false;
		cliente = new Cliente();
		recuperarRegistros();
		direccionBean.recuperarRegistros();
		direcciones = direccionBean.getDirecciones();
	}

	public void cargarDatosCliente() {
		if (codigo == 0)
			return;
		try {
			cliente = clienteON.read(codigo);
			codigoDireccion = cliente.getDir().getCodigo();
			editing = true;
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
	}

	public String guardar() {
		cliente.setDir(new Direccion(codigoDireccion));
		if (editing) {
//			
			if (clienteON.validadorDeCedula(cliente.getCedula()))
				clienteON.update(cliente);

		} else {
			if (clienteON.validadorDeCedula(cliente.getCedula()))
				clienteON.create(cliente);
		}
		return listar();
	}

	public String editar(Cliente _cliente) {
		return "cliente?faces-redirect=true&codigo=" + _cliente.getCodigo();
	}

	public String eliminar(Cliente _cliente) {
		clienteON.delete(_cliente.getCodigo());
		return crear();
	}

	public Cliente login(Usuario usuario) {
		cliente = clienteON.login(usuario);
		return cliente;
	}

	public Cliente getClientesPorCedula(String cedula) {
		return clienteON.getClientesPorCedula(cedula);
	}

	public Cliente read(int codigo) {
		return clienteON.read(codigo);
	}

	public String crear() {
		return "cliente";
	}

	public String listar() {
		return "lista-cliente";
	}

	public void recuperarRegistros() {
		clientes = new ArrayList<>();
		clientes = clienteON.listaClientes();
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public int getCodigoDireccion() {
		return codigoDireccion;
	}

	public void setCodigoDireccion(int codigoDireccion) {
		this.codigoDireccion = codigoDireccion;
	}

}
