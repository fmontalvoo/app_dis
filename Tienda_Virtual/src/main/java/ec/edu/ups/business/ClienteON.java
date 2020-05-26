package ec.edu.ups.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.dao.ClienteDAO;
import ec.edu.ups.model.Cliente;
import ec.edu.ups.model.objetos.Usuario;

@Stateless
public class ClienteON {

	@Inject
	private ClienteDAO cliDAO;

	public void create(Cliente cliente) {
		cliDAO.create(cliente);
	}

	public Cliente read(int codigo) {
		return cliDAO.read(codigo);
	}

	public void update(Cliente cliente) {
		cliDAO.update(cliente);
	}

	public void delete(int codigo) {
		cliDAO.delete(codigo);
	}

	public List<Cliente> listaClientes() {
		return cliDAO.listar();

	}

	public List<Cliente> listaPorNombres(String nombre) {
		return cliDAO.getByNombre(nombre);
	}

	public Cliente getClientesPorCedula(String cedula) {
		return cliDAO.getClientePorCedula(cedula);

	}

	/**
	 * Verifica que la cedula ingresada sea Ecuatoriana
	 * 
	 * @param cedula
	 * @return
	 */
	public boolean validadorDeCedula(String cedula) {
		boolean cedulaCorrecta = false;
		try {
			if (cedula.length() == 10) {
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			System.out.println(err.getStackTrace());
			System.out.println("Ingrese una cedula correcta por favor");
			cedulaCorrecta = false;
		}
		return cedulaCorrecta;
	}

	public Cliente login(Usuario usuario) {
		return cliDAO.getUserbyEmailAndPassword(usuario);
	}

}
