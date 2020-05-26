package ec.edu.ups.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import ec.edu.ups.business.CarritoON;
import ec.edu.ups.model.Carrito;
import ec.edu.ups.model.objetos.CarritoTmp;

@Path("/carrito")
public class CarritoWS {

	@Inject
	private CarritoON carritoON;

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response crear(Carrito carrito) {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		try {
			carritoON.agregarCarrito(carrito);
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
	public List<CarritoTmp> listar(@QueryParam("codigo") int codigo) {
		return carritoON.listarCarritosCliente(codigo, false);
	}

	@DELETE
	@Path("/{codigo}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response delete(@PathParam("codigo") int codigo) {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		try {
			carritoON.delete(codigo);
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

}
