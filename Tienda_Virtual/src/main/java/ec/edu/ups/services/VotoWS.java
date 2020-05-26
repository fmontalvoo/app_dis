package ec.edu.ups.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ec.edu.ups.business.VotoON;
import ec.edu.ups.model.Voto;
import ec.edu.ups.model.objetos.VotadosTmp;

@Path("/voto")
public class VotoWS {

	@Inject
	private VotoON votoON;

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response crear(Voto voto) {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		try {
			votoON.votarProducto(voto);
			data.put("codigo", "0");
			data.put("mensaje", "Ok");
			builder = Response.status(Response.Status.OK).entity(data);
		} catch (Exception e) {

			e.printStackTrace();
			data.put("codigo", "1");
			data.put("mensaje", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(data);
		}
		return builder.build();
	}

	@GET
	@Produces("application/json")
	public List<VotadosTmp> listarMasVotados() {
		return votoON.listarMasVotados();
	}

}
