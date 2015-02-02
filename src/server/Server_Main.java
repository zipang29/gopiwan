package server;

import java.net.DatagramSocket;
import java.net.SocketException;

/***
 * Programme s'executant sur le Raspberri Pi, le mettant à l'écoute d'un socket
 * 
 * 
 * @author Nicolas
 *
 */
public class Server_Main {

	public static void main(String[] args) throws NumberFormatException, SocketException {
		
		GoPiWanServer pi = null ;

		if(args.length == 1 ) pi = new GoPiWanServer() ;
		else pi = new GoPiWanServer(Integer.parseInt(args[1])) ;
		
		pi.init();
		pi.listen() ;
	}

}
