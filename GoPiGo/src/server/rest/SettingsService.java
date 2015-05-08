package server.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.Globals;
import server.gopigo.GoPiGo;
import server.gopigo.GoPiGo.Mode;

@Path("/settings")
public class SettingsService {

    @GET
    @Path("/mode/toggle")
    @Produces(MediaType.TEXT_PLAIN)
    public String toggleMode() throws IOException {
    	
    	Mode mode = Globals.gopigo.mode ;
    	String newMode = "DEBUG" ;
    	
    	if (mode == Mode.PYTHON) newMode = "SOCKET" ;
    	else if(mode == Mode.SOCKET) newMode = "PYTHON" ;
    	
    	Globals.gopigo = new GoPiGo(newMode) ;
    	
    	return "Toggle mode to : "+newMode ; 
    }
    
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
