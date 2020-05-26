package ec.edu.ups.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import ec.edu.ups.model.Cliente;
import ec.edu.ups.model.objetos.Usuario;
import ec.edu.ups.utils.SessionUtils;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

	@Inject
	private ClienteBean clienteBean;

	private Cliente cliente;
	private Usuario usuario;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		usuario = new Usuario();
	}

	/**
	 * Verifica que el Usuario Cliente que desea ingresar este registrado en el
	 * sistema y crea una sesion para el mismo
	 * 
	 * @return
	 */
	public String login() {
		cliente = clienteBean.login(usuario);
		if (cliente != null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", cliente);
			return "cliente";
		} else {
			return null;
		}
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
