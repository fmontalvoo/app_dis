package ec.edu.ups.model.objetos;

import java.io.Serializable;

public class Usuario implements Serializable {
	private String email;
	private String clave;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
}
