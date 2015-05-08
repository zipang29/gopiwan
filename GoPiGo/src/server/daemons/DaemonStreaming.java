package server.daemons;

import java.io.IOException;

import server.Globals;

public class DaemonStreaming{
	
	Process streamingProcess ;
	
	public void run(){
		
		startDaemon() ;
	}
	
	private void startDaemon(){
		
		System.out.println("[StreamingDaemon] Starting...");
		try{
			String[] mkdir = {
			        "mkdir",
			        "/tmp/stream"
			    };
			Runtime.getRuntime().exec(mkdir);
			String[] raspistill = {
			        "raspistill",
			        "--nopreview",
			        "-tl",
			        Globals.fps,
			        "-w",
			        Globals.StreamingWidth,
			        "-h",
			        Globals.StreamingHeight,
			        "-e",
			        "bmp",
			        "-o",
			        "/tmp/stream/pic.bmp",
			        "-t",
			        "9999999",
			    };
			streamingProcess = Runtime.getRuntime().exec(raspistill);
			System.out.println("[StreamingDameon] Running... "+streamingProcess.toString());
		}catch(IOException e){
			System.out.println("[StreamingDaemon] : Failed to start.");
			if (Globals.verbose) e.printStackTrace();
		}
	}
	
	public void stopDaemon(){
		
		String[] kill = {
		        "killall",
		        "raspistill"
		    };
		try {
			Runtime.getRuntime().exec(kill);
			System.out.println("[StreamingDaemon] : Stopped.");
		} catch (IOException e) {
			System.out.println("[StreamingDaemon] : Failed to stop.");
			e.printStackTrace();
		}
	}
}
