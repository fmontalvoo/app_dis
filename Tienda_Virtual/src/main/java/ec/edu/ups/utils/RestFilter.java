package ec.edu.ups.utils;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

//@Provider
//@PreMatching
public class RestFilter implements ContainerRequestFilter {
	public final static String KEY = "AppdI5";

	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		String url = request.getUriInfo().getAbsolutePath().toString();
		if (url.contains("/srv/login"))
			return;
		String token = request.getHeaderString("Authorization");
		System.out.println("TOKEN>>>>" + token);
		if (decodeJWT(token) == null)
			request.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
	}

	public static Claims decodeJWT(String jwt) {
		try {
			jwt = jwt.replace("Bearer ", "");
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY)).parseClaimsJws(jwt)
					.getBody();
//			System.out.println("ID: " + claims.getId());
//			System.out.println("Subject: " + claims.getSubject());
//			System.out.println("Issuer: " + claims.getIssuer());
//			System.out.println("Expiration: " + claims.getExpiration());
			return claims;
		} catch (MalformedJwtException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
