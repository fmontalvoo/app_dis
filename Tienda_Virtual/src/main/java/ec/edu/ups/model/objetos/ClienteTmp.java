package ec.edu.ups.model.objetos;

public class ClienteTmp {
	private int codigo;
	private String nombres;
	private String apellidos;
	private String email;
	private String clave;

	public ClienteTmp() {
	}

	public ClienteTmp(int codigo) {
		this.codigo = codigo;
	}

	public ClienteTmp(int codigo, String nombres, String apellidos, String email, String clave) {
		this.codigo = codigo;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.email = email;
		this.clave = clave;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

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
