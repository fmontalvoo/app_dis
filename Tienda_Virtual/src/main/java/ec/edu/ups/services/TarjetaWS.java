package ec.edu.ups.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.business.TarjetaON;
import ec.edu.ups.model.Tarjeta;

@Path("/tarjeta")
public class TarjetaWS {

	@Inject
	private TarjetaON tarjetaON;

	@GET
	@Produces("application/json")
	public List<Tarjeta> listaTarjetasCliente(@QueryParam("codigo") int codigo) {
		return tarjetaON.listaTarjetasCliente(codigo);
	}

}
