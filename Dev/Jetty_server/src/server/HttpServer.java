package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Serveur HTTP executé sur le RapsberryPi
 * Après le démarrage, le Pi écoute sur un port (7000 par défaut) les ordres provenant de l'interface.
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
	 * Robot "idéalement" contrôlé
	 */
	private static GoPiGo gopigo ;

	/**
	 * Instancie un serveur Http pour établir la communication entre l'interface
	 * et le gopigo.
	 * 
	 * @param gopigo
	 * @param port - port sur lequel ecoute le serveur (80 par défaut)
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

	public static class Servlet extends HttpServlet
	{


		/**
		 * 
		 */

		private static final long serialVersionUID = 512529337341576745L;


		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
			//if (Globals.verbose) System.out.println("[MainServer] : doGet :"+request.getMethod()+" "+request.getQueryString());

			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			try (BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResource("client/index.html").openStream()))) {
				String line;
				while ((line = br.readLine()) != null) {
					response.getWriter().println(line);
				}
			}


			String req = request.getQueryString();		

			try
			{
				if(req.contains("RIGHT")) gopigo.turnRight();
				else if(req.contains("LEFT")) gopigo.turnLeft();
				else if(req.contains("FORWARD")) gopigo.forward();
				else if(req.contains("DOWN")) gopigo.backward();
				else if(req.contains("STOP")) gopigo.stop();
			}
			catch
			(NullPointerException npe){}
		}



		/**
		 * Action réalisée lorsqu'on reçoit une requete HTTP de type POST de la part du client
		 * 
		 * @Override HttpServlet : doPost()
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			if (Globals.verbose) System.out.println("[MainServer] : doPost "+request.toString());

			BufferedReader br = request.getReader() ;
			String req = br.readLine() ;

			switch(req){

			case "FORWARD":{
				System.out.println("FORWARD");
				gopigo.forward();
				break ;
			}

			case "DOWN":{
				System.out.println("DOWN");
				gopigo.backward();
				break ;
			}

			case "LEFT":{
				System.out.println("LEFT");
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