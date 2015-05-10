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
 *
 * 	Service REST permettant de récupérer l'interface embarquée par le GoPiGo.
 * 
 *	La méthode est documentée avec un exemple de cas d'utilisation.
 */
@Path("/")
public class HTMLService {
	
	/**
	 * 	Récupère l'interface embarquée du GoPiGo.
	 * 	Cette interface est un fichier html, réalisant des appels à cette API.
	 * 
	 * 		usage:	http://adressePi:8080/interface
	 * 
	 * @return - [HTML] interface embarquée
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
