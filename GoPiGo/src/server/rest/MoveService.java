package server.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.Globals;
 
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
    	return "Moving "+direction.toLowerCase()+"...\n" ;
    }
    
    @GET
    @Path("/stop")
    @Produces(MediaType.TEXT_PLAIN)
    public String stop() throws IOException {

    	Globals.gopigo.stop();
    	return "Stopped !\n" ;
    }
    
    @GET
    @Path("/speed/set/{speed}")
    @Produces(MediaType.TEXT_PLAIN)
    public String setSpeed(@PathParam("speed") String speed) throws IOException {

    	
    	return "Set speed to "+speed+".\n" ;
    }
    
    @GET
    @Path("/speed/increase")
    @Produces(MediaType.TEXT_PLAIN)
    public String speedIncrease() throws IOException {

    	
    	return "Speed ++\n" ;
    }
    
    @GET
    @Path("/speed/decrease")
    @Produces(MediaType.TEXT_PLAIN)
    public String speedDecrease() throws IOException {

    	
    	return "Speed --\n" ;
    }
    
    
}
