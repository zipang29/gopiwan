package server.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import server.Globals;

/**
 *		********	STREAM SERVICE	********
 *
 * 	Service REST permettant d'utiliser le module caméra du GoPiGo.
 * 	Compte tenu de la puissance limitée du pi, et de la consommation électrique
 * 	relativement élevée d'un tel dispositif, le choix a été fait d'utiliser
 * 	des photos prises à intervalles réguliers au lieu d'un flux vidéo.
 * 	Ainsi, la bande passante est économisée, et le pi n'a pas à faire d'encodage vidéo (exercice ou il est mauvais).
 * 
 * 	Les fonctions disponibles permettent de : 
 * 		- démarrer le streaming {@link #start}
 * 		- le stopper {@link #stop}
 * 		- récupérer la frame actuelle {@link #getStream}
 * 
 *	Chaque méthode est documentée avec un exemple de cas d'utilisation.
 */
@Path("/video")
public class StreamService {

	/**
	 * 	Récupère la dernière image enregistrée.
	 * 	Attention : Impact important sur la bande passante.
	 * 
	 * 		usage:	http://adressePi:8080/video/get
	 * 
	 * @return	[BitMap File] - la dernière frame enregistrée
	 * @throws IOException
	 */
    @GET
    @Path("/get")
    @Produces("image/bmp")
    public Response getStream() throws IOException {

    		final String file = "/tmp/stream/pic.bmp";

    	    BufferedImage image = ImageIO.read(new File(file));

    	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	    ImageIO.write(image, "bmp", baos);
    	    byte[] imageData = baos.toByteArray();

    	    System.out.println("[StreamService] : getFrame");
    	    return Response.ok(new ByteArrayInputStream(imageData)).build();
    }
    
    /**
     * 	Démarre le processus d'acquisition vidéo.
     * 	Une fois ce processus activé, la frame rendue par {@link #getStream()} changera à intervalle régulier.
     * 	Cet intervalle est modifiable dans {@link SettingsService.setFPS}.
     * 
     * 	Attention : Impact important sur l'utilisation du CPU
     * 	Bonne pratique : Stopper le streaming lorsqu'il n'est plus utilisé. {@link #stop()}
     * 
	 * 		usage:	http://adressePi:8080/video/start
     * 
     * @return	[String] -	"Streaming started !"
     * @throws IOException
     */
    @GET
    @Path("/start")
    @Produces(MediaType.TEXT_PLAIN)
    public String start() throws IOException {

    	Globals.gopigo.startStreaming();
    	System.out.println("[StreamService] : started.");
    	return "Streaming started !\n" ;
    }
    
    /**
     * 	Stoppe le processus d'acquisition vidéo.
     * 	La dernière image enregistrée reste accessible.
     * 
	 * 		usage:	http://adressePi:8080/video/stop
	 * 
     * @return
     * @throws IOException
     */
    @GET
    @Path("/stop")
    @Produces(MediaType.TEXT_PLAIN)
    public String stop() throws IOException {

    	Globals.gopigo.stopStreaming();
    	System.out.println("[StreamService] : stopped.");
    	return "Streaming stopped.\n" ;
    }
}