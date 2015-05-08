package server.rest;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class HTMLService {

    @GET
    @Path("/interface")
    @Produces(MediaType.TEXT_HTML)
    public Response getInterface() throws IOException {

    		InputStream is = getClass().getClassLoader().getResourceAsStream("client/index.html");
    		
    		return Response.ok(is).build();
    }
}
