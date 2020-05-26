package ec.edu.ups.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.edu.ups.business.VotoON;
import ec.edu.ups.model.Voto;

@ManagedBean
@ViewScoped
public class VotoBean {

	@Inject
	private VotoON votoON;
	private List<Voto> votos;
	private String nombre;

	@PostConstruct
	public void init() {
		votos = new ArrayList<Voto>();
	}

	public String listarVotos() {
		votos = votoON.getVotosPorProducto(nombre);
		return null;
	}

	public List<Voto> getVotos() {
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}