package server;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import config.Debug;

/**
 * Serveur HTTP execut� sur le RapsberryPi
 * Apr�s le d�marrage, le Pi �coute sur un port (7000 par d�faut) les ordres provenant de l'interface.
 * 
 * 
 * @author Nicolas
 *
 */
public class MainServer{
	
	private static int debug = Debug.VERBOSE ;
	
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
    	
        /**
         * Action r�alis�e lorsqu'on re�oit une requete HTTP de type GET de la part du client
         * Ces requetes est de la forme :
         * ___________________________________
         * (En-t�te eventuel)
         * (FORWARD|LEFT|DOWN|RIGHT) <EOL>
         * speed <EOL>
         * <EOL>
         * ___________________________________
         * 
         * Envoi d'un message d'acquittement t�moignant de l'action effectu�e
         * 
         * @Override HttpServlet : doPost()
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        	if (debug >= Debug.VERBOSE) System.out.println("[MainServer] : doGet :"+request.getMethod()+" "+request.getQueryString());

            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Ici GoPiGo Pi, je re�ois 5 sur 5.\n\nParametres envoy�s : "+request.getQueryString());
            
        	BufferedReader br = request.getReader() ;
        	String req = br.readLine() ;
    		int speed = Integer.parseInt(br.readLine()) ;

        	switch(req){
        	
        	case "FORWARD":{
        		gopigo.forward(1, speed); // arbitraire ; possibilit� de faire plusieurs boutons pour plusieurs vitesses
        		break ;
        	}
        	
        	case "DOWN":{
        		gopigo.back(1, speed);
        		break ;
        	}
        	
        	case "LEFT":{
        		gopigo.turnLeft(1, speed);
        		break ;
        	}
        	
        	case "RIGHT":{
        		gopigo.turnRight(1, speed);
        		break ;
        	}
        	
        	default : break ;
        	}
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(req+" : done");
        }
        
        
        /**
         * Action r�alis�e lorsqu'on re�oit une requete HTTP de type POST de la part du client
         * 
         * @Override HttpServlet : doPost()
         */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        	if (debug >= Debug.VERBOSE) System.out.println("[MainServer] : doPost "+request.toString());
        	
            
        }
    }
    
}