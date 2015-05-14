package src.server.fulrest;

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
			Globals.gopigo = gopigo ;
			//gopigo.startStreaming();//Lancement du streaming depuis la camera
			HttpServer http = new HttpServer(8080) ;
        
		}catch(Exception e){ e.printStackTrace(); }
	}
}
