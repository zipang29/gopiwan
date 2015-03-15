package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
public class HttpServer{
		
	/**
	 * Serveur http Jetty
	 * @see org.eclipse.jetty.server.Server
	 */
    private Server server ;
    
    /**
     * Robot contr�l�
     */
    private static GoPiGo gopigo ;
    
    /**
     * Instancie un serveur Http pour �tablir la communication entre l'interface
     * et le gopigo.
     * 
     * @param gopigo
     * @param port - port sur lequel ecoute le serveur (80 par d�faut)
     * @throws Exception
     */
    public HttpServer(GoPiGo gopigo, int port) throws Exception {
    	
    	HttpServer.gopigo = gopigo ;
    	
    	server = new Server(port);
        
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        
        // Passing in the class for the servlet allows jetty to instantite an instance of that servlet and mount it
        // on a given context path.
        handler.addServletWithMapping(Servlet.class, "/*");
    }
    
    public void start() throws Exception{
    	
    	server.start();
    	server.join();
    }
    
    @SuppressWarnings("serial")
    public static class Servlet extends HttpServlet{
    	
    	
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        	if (Globals.verbose) System.out.println("[MainServer] : doGet :"+request.getMethod()+" "+request.getQueryString());

            response.setContentType("text/html");
            
//            try (BufferedReader br = new BufferedReader(new FileReader("Client/GoPiGo_Web/index.html"))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                   response.getWriter().println(line);
//                }
//            }
            
            response.getWriter().println("<!DOCTYPE html>"
            		+ "<html>"
            		+ "<body>"
            		+ "<a href='Client/GoPiGo_Web/index.html'>Bienvenue</a>"
            		+ "</body>"
            		+ "</html>");
            response.setStatus(HttpServletResponse.SC_OK);

             String req = request.getQueryString() ;
             if(req.contains("right")) gopigo.turnRight();
             else if(req.contains("left")) gopigo.turnLeft();
             else if(req.contains("forward")) gopigo.forward();
             else if(req.contains("back")) gopigo.backward();
             else if(req.contains("stop")) gopigo.stop();
        }
        
        
        /**
         * Action r�alis�e lorsqu'on re�oit une requete HTTP de type POST de la part du client
         * 
         * @Override HttpServlet : doPost()
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        	if (Globals.verbose) System.out.println("[MainServer] : doPost "+request.toString());
        	
        	BufferedReader br = request.getReader() ;
        	String req = br.readLine() ;

        	switch(req){
        	
        	case "FORWARD":{
        		gopigo.forward(); // arbitraire ; possibilit� de faire plusieurs boutons pour plusieurs vitesses
        		break ;
        	}
        	
        	case "DOWN":{
        		gopigo.backward();
        		break ;
        	}
        	
        	case "LEFT":{
        		gopigo.turnLeft();
        		break ;
        	}
        	
        	case "RIGHT":{
        		gopigo.turnRight();
        		break ;
        	}
        	case "STOP":{
        		gopigo.stop() ;
        		break ;
        	}
        	
        	default : break ;
        	}
        }
    }
    
}