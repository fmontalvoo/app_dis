package ec.edu.ups.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ec.edu.ups.business.ProductoON;
import ec.edu.ups.model.Producto;

@Path("/producto")
public class ProductoWS {

	@Inject
	private ProductoON productoON;

	@GET
	@Path("/{codigo}")
	@Produces("application/json")
	public Producto leer(@PathParam("codigo") int codigo) {
		return productoON.read(codigo);
	}

	@GET
	@Produces("application/json")
	public List<Producto> listar() {
		return productoON.listar();
	}
}
