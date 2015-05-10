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
 * 	Service REST permettant de r�cup�rer l'interface embarqu�e par le GoPiGo.
 * 
 *	La m�thode est document�e avec un exemple de cas d'utilisation.
 */
@Path("/")
public class HTMLService {
	
	/**
	 * 	R�cup�re l'interface embarqu�e du GoPiGo.
	 * 	Cette interface est un fichier html, r�alisant des appels � cette API.
	 * 
	 * 		usage:	http://adressePi:8080/interface
	 * 
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
