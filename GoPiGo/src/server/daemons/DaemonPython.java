package server.daemons;

import java.io.IOException;

import server.Globals;

public class DaemonPython extends Thread{
	

	private void startDameon(){
		
		System.out.println("[PythonDameon] Starting...");
		try {
			String[] cmd = {
			        "python",
			        "server.py"
			    };
			Process p = Runtime.getRuntime().exec(cmd);
			System.out.println("[PythonDameon] Running... "+p.toString());
			Globals.pythond = p ;
		} catch (IOException e) {
			System.out.println("[PythonDameon] Fichier server.py non trouvé");
			try {
				String[] cmd = {
				        "python",
				        "/home/pi/scripts/server.py"
				    };
				Process p = Runtime.getRuntime().exec(cmd);
				System.out.println("[PythonDameon] Running /home/pi/scripts/server.py... "+p.toString());
				
			} catch (IOException e1) {
				System.out.println("[PythonDameon] Scripts par defaut absents...");
				if (Globals.verbose) e1.printStackTrace();
			}
		}
	}
	
	public void run(){
		this.startDameon();
	}
}
