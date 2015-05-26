package server.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dexterind.gopigo.Gopigo;

import server.Globals;

/**
 *		********	Settings SERVICE	********
 *	<br>
 * 	<br>Service REST permettant de modifier quelques param�tres du GoPiGo
 * 	<br>
 *	<br>Chaque m�thode est document�e avec un exemple de cas d'utilisation.
 */
@Path("/settings")
public class SettingsService {

	/**
	 * 	<br>Change le mode de communication.
	 * 	<br>Il est possible de communiquer soit par Socket avec un daemon Python,
	 * 	<br>soit de lancer un interpr�teur � chaque nouvelle commande.
	 * 	<br>
	 * 	<br>Selon si le mouvement change souvent ou pas, l'un ou l'autre sera moins �nergivore...
	 * 	<br>Si le robot doit souvent changer de direction, opter pour la communication par Socket.
	 * 	<br>
	 * 	<br>Cette fonction inverse le mode de communication, et renvoie la valeur actuelle.
	 * 	<br>
	 * 	<br>	usage:	http://adressePi:8080/mode/toggle
	 * 	<br>
	 * @return [String] - "Toggle mode to $newMode"
	 * @throws IOException
	 */
    @GET
    @Path("/mode/toggle")
    @Produces(MediaType.TEXT_PLAIN)
    public String toggleMode() throws IOException {
    	
    	Globals.gopigo = new Gopigo() ;
    	
    	return "Gopigo lancé" ; 
    }
    
    /**
     * 	<br>Change la r�solution des images (snapshots) g�n�r�s par le GoPiGo.
     * 	<br>Le module cam�ra �quip� permet de filmer � une r�solution FullHD, c'est � dire 1920 * 1080 pixels.
     * 	<br>Ceci dit, le processeur ARM pr�sent est tr�s peu performant pour l'encodage.
     * <br>
     * 	<br>Il s'agira donc de faire un compromis entre la r�solution et les fps (frames per second), fr�quence de rafraichissement.
     * 	<br>Pour r�gler les fps, voir la fonction {@link #setFPS}
     * <br>
     * 	<br>Valeurs possibles : <br>
     * 	<ul>
     * 	<li>	- FULLHD 	| 	FHD 	| 	1080 	| 	1080p </li>
     * 	<li>	- HD 		|	720		| 	720p  </li>
     * 	<li>	- 480		| 	480p  </li>
     * 	<li>	- 360		|	360p  </li>
     * 	<li>	- 240		|	240p  </li>
     * 	</ul>
     * 	<br>Note : (insensible � la casse)
     *  <br>Il peut �tre n�c�ssaire de red�marrer le streaming apr�s un appel � cette fonction.	{@link StreamService.start}}
     *  <br>
     *  <br>	usage:	http://adressePi:8080/settings/video/resolution/480p
     *  <br>
     *  <br>Valeurs conseill�es : 360p@10fps
     *  <br>
     * @param resolution
     * @return 	[String] - "Resolution set to $newRes"
     * @throws IOException
     */
    @GET
    @Path("/video/resolution/{mode}")
    @Produces(MediaType.TEXT_PLAIN)
    public String setResolution( @PathParam("mode") String resolution) throws IOException {
    	
    	int height = 240, width = 320 ;
    	resolution = resolution.toUpperCase() ;
    	
    	if(resolution.equals("FULLHD") || resolution.equals("FHD") || resolution.equals("1080") || resolution.equals("1080p")){
    		height = 1080 ;
    		width = 1920 ;
    	}else if(resolution.equals("HD") || resolution.equals("720") || resolution.equals("720p")){
    		height = 720 ;
    		width = 1280 ;
    	}else if(resolution.equals("480p") || resolution.equals("480")){
    		height = 480 ;
    		width = 640 ;
    	}else if(resolution.equals("360p") || resolution.equals("360")){
    		height = 360 ;
    		width = 480 ;
    	}else if(resolution.equals("240p") || resolution.equals("240")){
    		height = 240 ;
    		width = 320 ;
    	}
    	Globals.gopigo.setResolution(height, width);
    	return "Resolution set to "+height ;
    }
    
    /**
     * 	Change la fr�quence de rafraichissement des images (snapshots) g�n�r�s par le GoPiGo.<br>
     * 	Le module cam�ra �quip� permet de filmer jusqu'� 30 fps.<br>
     * 	N�anmoins, chaque image g�n�r�e donne beaucoup de travail d'encodage au processeur.<br>
     * 	Le nombre d'images par secondes (et donc la fluidit�) se fait donc au d�triment de la latence.<br>
     * <br>
     * 	Il s'agira donc de faire un compromis entre la r�solution et les fps (frames per second), fr�quence de rafraichissement.<br>
     * 	Pour r�gler la resoltuion, voir la fonction {@link #setResolution}<br>
     * <br>
     * 	Valeurs possibles : 0 < n < 30<br>
     * <br>
     *  Il peut �tre n�c�ssaire de red�marrer le streaming apr�s un appel � cette fonction.	{@link StreamService.start}}<br>
     *  <br>
     *  	usage:	http://adressePi:8080/settings/video/fps/8<br>
     *  <br>
     *  Valeurs conseill�es : 360p@10fps<br> 
     *  <br>
     * @param resolution
     * @return 	[String] - "FPS set to $newFPS"
     * @throws IOException
     */
    @GET
    @Path("/video/fps/{fps}")
    @Produces(MediaType.TEXT_PLAIN)
    public String setFPS( @PathParam("fps") String fps) throws IOException {
    	
    	try{
    	int FPS = 1000 / Math.round(Integer.parseInt(fps)) ;
    	Globals.gopigo.setFPS(FPS);
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return "Bad value" ;
    	}
    	return "FPS set to "+fps ;
    }
}
