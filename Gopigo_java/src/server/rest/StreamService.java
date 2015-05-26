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
 *	<br>
 * 	Service REST permettant d'utiliser le module cam�ra du GoPiGo.<br>
 * 	Compte tenu de la puissance limit�e du pi, et de la consommation �lectrique<br>
 * 	relativement �lev�e d'un tel dispositif, le choix a �t� fait d'utiliser<br>
 * 	des photos prises � intervalles r�guliers au lieu d'un flux vid�o.<br>
 * 	Ainsi, la bande passante est �conomis�e, et le pi n'a pas � faire d'encodage vid�o (exercice ou il est mauvais).<br>
 * <br>
 * 	Les fonctions disponibles permettent de :<br> 
 * 		- d�marrer le streaming {@link #start}<br>
 * 		- le stopper {@link #stop}<br>
 * 		- r�cup�rer la frame actuelle {@link #getStream}<br>
 * <br>
 *	Chaque m�thode est document�e avec un exemple de cas d'utilisation.<br>
 */
@Path("/video")
public class StreamService {

	/**
	 * 	R�cup�re la derni�re image enregistr�e.<br>
	 * 	Attention : Impact important sur la bande passante.<br>
	 * <br>
	 * 		usage:	http://adressePi:8080/video/get<br>
	 * <br>
	 * @return	[BitMap File] - la derni�re frame enregistr�e
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
     * 	D�marre le processus d'acquisition vid�o.<br>
     * 	Une fois ce processus activ�, la frame rendue par {@link #getStream()} changera � intervalle r�gulier.<br>
     * 	Cet intervalle est modifiable dans {@link server.rest.SettingsService#setFPS(String)}<br>
     * <br>
     * 	Attention : Impact important sur l'utilisation du CPU<br>
     * 	Bonne pratique : Stopper le streaming lorsqu'il n'est plus utilis�. {@link #stop()}<br>
     * <br>
	 * 		usage:	http://adressePi:8080/video/start<br>
     * <br>
     * @return	[String] -	"Streaming started !"
     * @throws IOException
     * @see server.rest.SettingsService#setFPS(String)
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
     * 	Stoppe le processus d'acquisition vid�o.<br>
     * 	La derni�re image enregistr�e reste accessible.<br>
     * <br>
	 * 		usage:	http://adressePi:8080/video/stop<br>
	 * <br>
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