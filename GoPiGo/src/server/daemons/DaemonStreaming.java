package server.daemons;

import java.io.IOException;

import server.Globals;

public class DaemonStreaming extends Thread {

	
	public void run(){
		
		startDaemon() ;
	}
	
	private void startDaemon(){
		
		System.out.println("[StreamingDaemon] Starting...");
		try{
			String[] cmd = {
			        "raspistill",
			        "--nopreview",
			        "-w",
			        Globals.StreamingWidth,
			        "-h",
			        Globals.StreamingHeight,
			        "-q",
			        "5",
			        "-o",
			        "/tmp/stream/pic.jpg",
			        "-tl",
			        "500",
			        "-t",
			        "9999999"
			    };
			Process p = Runtime.getRuntime().exec(cmd);
			System.out.println("[StreamingDameon] Running... "+p.toString());
		}catch(IOException e){
			System.out.println("[StreamingDaemon] : Failed to start.");
			if (Globals.verbose) e.printStackTrace();
		}
	}
}
