package server;

public class Launcher {

	public static void main(String[] args){
		try{
//			if (args[0] != null){
//				if (args[0] == "-v") Globals.verbose = true ;
//			}
			Globals.verbose = true ;
			
			System.out.println("GoPiGo started...");
			GoPiGo gopigo = new GoPiGo() ;
			PythonDaemon python = new PythonDaemon() ; 
			python.start();
			HttpServer http = new HttpServer(gopigo, 80) ;
	        http.start();
        
		}catch(Exception e){ e.printStackTrace(); }
	}
}
