package server;

import server.gopigo.GoPiGo;

/**
 * 
 * @author David Barrat
 *
 */

public class Launcher {

	public static void main(String[] args){
		try{
			Globals.verbose = true ;
			
			GoPiGo gopigo;
			if(args.length == 0 ) gopigo = new GoPiGo() ; // mode debug
			else{
				String mode = args[0] ; // usage : Laucher (SOCKET | PYTHON | DEBUG )
				gopigo = new GoPiGo(mode) ;
			}
			// TODO traiter proprement les arguments
			Globals.gopigo = gopigo ;
			HttpServer http = new HttpServer(8080) ;
        
		}catch(Exception e){ e.printStackTrace(); }
	}
}
