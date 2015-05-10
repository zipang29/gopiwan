package server.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.Globals;

/**
 *		********	MOVE SERVICE	********
 *	<br>
 * 	<br>Service REST permettant de contrôler le robot.
 * 	<br>Les fonctions disponibles permettent de : 
 * <ul>
 * 	<li>	- lui donner un mouvement {@link #move} </li>
 * 	<li>	- le stopper {@link #stop}</li>
 * 	<li>	- modifier sa vitesse {@link #setSpeed}</li>
 * </ul>
 * 	<br>
 *	<br>Chaque méthode est documentée avec un exemple de cas d'utilisation.
 */
@Path("/gopigo")
public class MoveService {

	/**
	 * 	<br>Fait éxécuter au GoPiGo le mouvement indiqué.
	 * 	<br>Le mouvement continuera jusqu'à un appel à la fonction {@link #stop}.
	 * 	<br>Les valeurs possibles de direction sont : <br>
	 * 	<ul>
	 * 	<li>	- FORWARD</li>
	 * 	<li>	- BACKWARD</li>
	 * 	<li>	- RIGHT</li>
	 * 	<li>	- LEFT</li>
	 * 	<li>	- ROTR</li>
	 * 	<li>	- ROTL</li>
	 * 	<li>	- STOP (préférer {@link #stop} )</li>
	 * 	</ul>
	 * 	<br>Toute autre direction donnée ne produira aucun effet.
	 * 	<br>
	 * 	<br>Attention : le robot commencera le mouvmeent instantanément. Vérifier son emplacement au préalable !
	 * 	<br>
	 * 	<br>usage:	http://adressePi:8080/gopigo/move/right
	 * 
	 * @param direction	- [String] direction 
	 * @return	"Moving $direction..." | "Invalid Direction"
	 * @throws IOException
	 */
    @GET
    @Path("/move/{direction}")
    @Produces(MediaType.TEXT_PLAIN)
    public String move( @PathParam("direction") String direction ) throws IOException {

    	direction = direction.toUpperCase() ;	// ignoreCase
		try
		{
			if(direction.equals("RIGHT"))		// Java 6 compliance : pas de switch...
			{
				Globals.gopigo.turnRight();
			}
			else if(direction.equals("LEFT"))
			{
				Globals.gopigo.turnLeft();
			}
			else if(direction.equals("FORWARD")) 
			{
				Globals.gopigo.forward();
			}
			else if(direction.equals("BACKWARD"))
			{
				Globals.gopigo.backward();
			}
			else if(direction.equals("ROTR"))
			{
				Globals.gopigo.turnRightRot();;
			}
			else if(direction.equals("ROTL"))
			{
				Globals.gopigo.turnLeftRot();
			}
			else if(direction.equals("STOP"))
			{
				Globals.gopigo.stop();
			}
			else return "Invalid Direction" ;
		}
		catch
		(NullPointerException npe){}
    	return "Moving "+direction.toLowerCase()+"...\n" ;
    }
    
    /**
     * 	<br>Stoppe le robot si celui-ci est en mouvement.
     * 	<br>Réflexe à developper en environnement hostile...
     * 	<br>
	 * 	<br>	usage:	http://adressePi:8080/gopigo/stop
     * 	<br>
     * @return "Stopped"
     * @throws IOException
     */
    @GET
    @Path("/stop")
    @Produces(MediaType.TEXT_PLAIN)
    public String stop() throws IOException {

    	Globals.gopigo.stop();
    	return "Stopped !\n" ;
    }
    
    /**
     * 	<br>Fixe la vitesse du GoPiGo à une valeur donnée.
     * 	<br>Si la valeur n'est pas un entier, ne fait rien et le signale en retour.
     * 	<br>Valeur minimale : 0
     * 	<br>Valeur maximale : 255
     * 	<br>
	 * 	<br>	usage:	http://adressePi:8080/gopigo/speed/set/85
     * 	<br>
     * @param speed - [String] Valeur entière et positive
     * @return	"Set speed to $speed"
     * @throws IOException
     */
    @GET
    @Path("/speed/set/{speed}")
    @Produces(MediaType.TEXT_PLAIN)
    public String setSpeed(@PathParam("speed") String speed) throws IOException {

    	int sp = Integer.parseInt(speed) ;
    	Globals.gopigo.setSpeed(sp);
    	return "Set speed to "+speed+".\n" ;
    }
    
    /**
     * <br>	Augmente la vitesse du GoPiGo.
     * <br>
	 * 	<br>	usage:	http://adressePi:8080/gopigo/speed/increase
     * <br>
     * @return	"Speed ++"
     * @throws IOException
     */
    @GET
    @Path("/speed/increase")
    @Produces(MediaType.TEXT_PLAIN)
    public String speedIncrease() throws IOException {

    	Globals.gopigo.increaseSpeed();
    	return "Speed ++\n" ;
    }
    
    /**
     * 	<br>Diminue la vitesse du GiPiGo.
     * 	<br>
	 * 	<br>	usage:	http://adressePi:8080/gopigo/speed/decrease
     * 	<br>
     * @return	"Speed --"
     * @throws IOException
     */
    @GET
    @Path("/speed/decrease")
    @Produces(MediaType.TEXT_PLAIN)
    public String speedDecrease() throws IOException {

    	Globals.gopigo.decreaseSpeed();
    	return "Speed --\n" ;
    }
    
    
}
