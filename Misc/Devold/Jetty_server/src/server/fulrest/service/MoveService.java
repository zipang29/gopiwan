package src.server.fulrest.service;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import src.server.fulrest.Globals;
 
@Path("/gopigo")
public class MoveService {

	// exemple d'utilisation : http://localhost:8080/gopigo/move/right
    @GET
    @Path("/move/{direction}")
    @Produces(MediaType.TEXT_PLAIN)
    public String move( @PathParam("direction") String direction ) throws IOException {

    	direction = direction.toUpperCase() ;
		try
		{
			if(direction.contains("RIGHT"))
			{
				Globals.gopigo.turnRight();
			}
			else if(direction.contains("LEFT"))
			{
				Globals.gopigo.turnLeft();
			}
			else if(direction.contains("FORWARD")) 
			{
				Globals.gopigo.forward();
			}
			//Oui je sais, c'est très sale
			else if(direction.contains("DOWN") && !direction.contains("SHUT")) // #lol
			{
				Globals.gopigo.backward();
			}
			else if(direction.contains("STOP"))
			{
				Globals.gopigo.stop();
			}
			else if(direction.contains("SHUTDOWN"))
			{
				Globals.gopigo.stop();
				Globals.gopigo.stopStreaming();
			}
		}
		catch
		(NullPointerException npe){}
    	return "Moving "+direction+" ..." ;
    }
    
    /* Version sans parametre, moins propre
    @GET
    @Path("/move")
    @Produces(MediaType.TEXT_PLAINL)
    public String move( @PathParam("direction") String direction ) {


    	return "Moving "+direction;
    }
    */
}
