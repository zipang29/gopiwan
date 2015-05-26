package server;

import com.dexterind.gopigo.*;

/**
 * 	Lanceur de l'application.<br>
 *	Se charge de traiter les �ventuels arguments, et de cr�er les instances de classes n�c�ssaires.<br>
 */
public class Launcher {

	public static void main(String[] args){
		try{
			Globals.verbose = true ;
			
			Gopigo  gopigo = new Gopigo() ; // mode debug
			
			// TODO traiter proprement les arguments
			Globals.gopigo = gopigo ;
			@SuppressWarnings("unused")
			HttpServer http = new HttpServer(8080) ;
        
		}catch(Exception e){ e.printStackTrace(); }
	}
}
