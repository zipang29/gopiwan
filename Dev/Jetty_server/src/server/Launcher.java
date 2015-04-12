package server;

/**
 * 
 * @author David Barrat
 *
 */

public class Launcher {

	public static void main(String[] args){
		try{
			Globals.verbose = true ;
			
			System.out.println("GoPiGo started...");
			GoPiGo gopigo = new GoPiGo() ;
			gopigo.startStreaming();//Lancement du streaming depuis la camera
			HttpServer http = new HttpServer(gopigo, 80) ;
	        http.start();
        
		}catch(Exception e){ e.printStackTrace(); }
	}
}
