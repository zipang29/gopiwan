package server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Serveur HTTP execut� sur le RapsberryPi
 * Apr�s le d�marrage, le Pi �coute sur un port (7000 par d�faut) les ordres provenant de l'interface.
 * 
 * 
 * @author Nicolas
 *
 */
public class MainServer{
	
    private Server server ;
    
    private static GoPiGo gopigo ; 
    
    public MainServer(GoPiGo gopigo, int port) throws Exception {
    	
    	this.gopigo = gopigo ;
    	
    	server = new Server(port);
        
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
 
        // Passing in the class for the servlet allows jetty to instantite an instance of that servlet and mount it
        // on a given context path.
        handler.addServletWithMapping(Servlet.class, "/*");

        server.start();
        server.join();
    }
    
    @SuppressWarnings("serial")
    public static class Servlet extends HttpServlet{
    	
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Ici GoPiGo Pi, je re�ois 5 sur 5");
            
            gopigo.forward(1, 100); // pour le fun
        }
    }
}