package server.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.Globals;
import com.dexterind.gopigo.*;

@Path("/monitoring")
public class MonitoringService {

    @GET
    @Path("/mode/toggle")
    @Produces(MediaType.TEXT_PLAIN)
    public String toggleMode() {
    	
//    	Mode mode = Globals.gopigo.mode ;
//    	String newMode = "DEBUG" ;
//    	
//    	if (mode == Mode.PYTHON) newMode = "SOCKET" ;
//    	else if(mode == Mode.SOCKET) newMode = "PYTHON" ;
    	
    	Globals.gopigo = new Gopigo() ;
    	return "Gopigo lancé";
    	
    }
}
