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

import ec.edu.ups.business.FacturaON;
import ec.edu.ups.model.objetos.ComprarTmp;
import ec.edu.ups.model.objetos.ComprasTmp;
import ec.edu.ups.model.objetos.VendidosTmp;

@Path("/factura")
public class FacturaWS {

	@Inject
	private FacturaON facturaON;

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response facturar(ComprarTmp cTmp) {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		try {
			facturaON.facturar(cTmp.getCodigo());
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
	@Path("vendidos")
	@Produces("application/json")
	public List<VendidosTmp> listarMasVendidos() {
		return facturaON.listarMasVendidos();
	}

	@GET
	@Path("compras")
	@Produces("application/json")
	public List<ComprasTmp> listarClientesQueCompraronMas() {
		return facturaON.listarClientesQueCompraronMas();
	}

}
