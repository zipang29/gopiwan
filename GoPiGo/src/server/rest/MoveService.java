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
 *
 * 	Service REST permettant de contr�ler le robot.
 * 	Les fonctions disponibles permettent de : 
 * 		- lui donner un mouvement {@link #move}
 * 		- le stopper {@link #stop}
 * 		- modifier sa vitesse {@link #setSpeed}
 * 
 *	Chaque m�thode est document�e avec un exemple de cas d'utilisation.
 */
@Path("/gopigo")
public class MoveService {

	/**
	 * 	Fait �x�cuter au GoPiGo le mouvement indiqu�.
	 * 	Le mouvement continuera jusqu'� un appel � la fonction {@link #stop}.
	 * 	Les valeurs possibles de direction sont : 
	 * 		- FORWARD
	 * 		- BACKWARD
	 * 		- RIGHT
	 * 		- LEFT
	 * 		- STOP (pr�f�rer {@link #stop} )
	 * 
	 * 	Toute autre direction donn�e ne produira aucun effet.
	 * 
	 * 	Attention : le robot commencera le mouvmeent instantan�ment. V�rifier son emplacement au pr�alable !
	 * 
	 * 		usage:	http://adressePi:8080/gopigo/move/right
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
     * 	Stoppe le robot si celui-ci est en mouvement.
     * 	R�flexe � develloper en environnement hostile...
     * 
	 * 		usage:	http://adressePi:8080/gopigo/stop
     * 
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
     * 	Fixe la vitesse du GoPiGo � une valeur donn�e.
     * 	Si la valeur n'est pas un entier, ne fait rien et le signale en retour.
     * 	Valeur minimale : 
     * 	Valeur maximale :
     * 
	 * 		usage:	http://adressePi:8080/gopigo/speed/set/85
     * 
     * @param speed - [String] Valeur enti�re et positive
     * @return	"Set speed to "+speed+"
     * @throws IOException
     */
    @GET
    @Path("/speed/set/{speed}")
    @Produces(MediaType.TEXT_PLAIN)
    public String setSpeed(@PathParam("speed") String speed) throws IOException {

    	//TODO
    	return "Set speed to "+speed+".\n" ;
    }
    
    /**
     * 	Augmente la vitesse du GoPiGo.
     * 
	 * 		usage:	http://adressePi:8080/gopigo/speed/increase
     * 
     * @return	"Speed ++"
     * @throws IOException
     */
    @GET
    @Path("/speed/increase")
    @Produces(MediaType.TEXT_PLAIN)
    public String speedIncrease() throws IOException {

    	//TODO
    	return "Speed ++\n" ;
    }
    
    /**
     * 	Diminue la vitesse du GiPiGo.
     * 
	 * 		usage:	http://adressePi:8080/gopigo/speed/decrease
     * 
     * @return	"Speed --"
     * @throws IOException
     */
    @GET
    @Path("/speed/decrease")
    @Produces(MediaType.TEXT_PLAIN)
    public String speedDecrease() throws IOException {

    	//TODO
    	return "Speed --\n" ;
    }
    
    
}
