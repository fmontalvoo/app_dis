package ec.edu.ups.services;

import java.util.Date;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ec.edu.ups.business.ClienteON;
import ec.edu.ups.model.Cliente;
import ec.edu.ups.model.objetos.Usuario;
import ec.edu.ups.utils.RestFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/login")
public class AuthWS {

	@Inject
	private ClienteON clienteON;

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response login(Usuario usuario) {
		Cliente cliente = clienteON.login(usuario);
		if (cliente != null) {
			String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, RestFilter.KEY).setSubject("Tienda_Virtual")
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() * 100)).claim("email", usuario.getEmail())
					.claim("codigo", cliente.getCodigo()).compact();
			JsonObject json = Json.createObjectBuilder().add("jwt", jwt).build();
			return Response.status(Response.Status.CREATED).entity(json).build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}

}
