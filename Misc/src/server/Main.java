package server;

import config.Configuration;
import config.Debug;

/**
 * AppLauncher
 * Demarre le serveur HTTP Jetty
 * Y associe le robot GoPiGo indiqu� � l'adresse:port pass� en param�tres
 * 
 * @author Nicolas
 *
 */
public class Main {

	public static void main(String[] args) {
		try {
			// args ...
			
			//int port = (args[0] == null ) ? 7000 : Integer.parseInt(args[0]) ;
			
			// .. args
			GoPiGo gopigo = new GoPiGo(Debug.VERBOSE, new Configuration(Configuration.WHEELS | Configuration.CAMERA)) ;
			
			MainServer http = new MainServer(gopigo, 80) ;
			
		} catch (Exception e) { e.printStackTrace();}
	}
}
