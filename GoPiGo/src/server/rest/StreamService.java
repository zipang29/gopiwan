package server.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import server.Globals;

@Path("/video")
public class StreamService {

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
    
    @GET
    @Path("/start")
    @Produces(MediaType.TEXT_PLAIN)
    public String start() throws IOException {

    	Globals.gopigo.startStreaming();
    	System.out.println("[StreamService] : started.");
    	return "Streaming started !\n" ;
    }
    
    @GET
    @Path("/stop")
    @Produces(MediaType.TEXT_PLAIN)
    public String stop(@PathParam("speed") String speed) throws IOException {

    	Globals.gopigo.stopStreaming();
    	System.out.println("[StreamService] : stopped.");
    	return "Streaming stopped.\n" ;
    }
}