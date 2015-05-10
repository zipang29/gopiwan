package server.rest;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *		********	HTML SERVICE	********
 *	<br>
 * 	<br>Service REST permettant de r�cup�rer l'interface embarqu�e par le GoPiGo.
 * 	<br>
 */
@Path("/")
public class HTMLService {
	
	/**
	 * 	<br>R�cup�re l'interface embarqu�e du GoPiGo.
	 * 	<br>Cette interface est un fichier html, r�alisant des appels � cette API.
	 * 	<br>
	 * 	<br>	usage:	http://adressePi:8080/interface
	 * 	<br>
	 * @return - [HTML] interface embarqu�e
	 * @throws IOException
	 */
    @GET
    @Path("/interface")
    @Produces(MediaType.TEXT_HTML)
    public Response getInterface() throws IOException {

    		InputStream is = getClass().getClassLoader().getResourceAsStream("client/index.html");
    		
    		return Response.ok(is).build();
    }
}
