package server;

import server.gopigo.GoPiGo;

public class Globals {

	public static boolean verbose = true ;
	
	public static GoPiGo gopigo ;
	
	public static Process pythond ;
	
	public static String StreamingWidth = "480" , StreamingHeight = "360", fps = "100", SnapshotEncoding = "bmp" ;
}
