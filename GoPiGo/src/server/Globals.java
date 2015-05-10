package server;

import server.gopigo.GoPiGo;

/**
 * 	Variables globales, �tant partag�es par plusieurs packages
 *
 */
public class Globals {

	/**
	 * 	Mode debug ou silencieux
	 */
	public static boolean verbose = true ;
	
	/**
	 * 	Interface entre le programme (API) et le mat�riel (GoPiGo)
	 */
	public static GoPiGo gopigo ;
	
	/**
	 * 	D�mon python recevant les requetes par socket, si le programme est configur� en mode SOCKET
	 */
	public static Process pythond ;
	
	/**
	 * 	Param�tres vid�o
	 */
	public static String StreamingWidth = "480" , StreamingHeight = "360", fps = "100", SnapshotEncoding = "bmp" ;
}
