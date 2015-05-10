package server.daemons;

import java.io.IOException;

import server.Globals;

/**
 * 	Processus de type D�mon, faisant tourner en tache de fond un serveur python sur un socket.<br>
 * 	Ce D�mon est utilis� dans le cas ou la communication interne du programme (entre l'API et le mat�riel)<br>
 * 	se fait par socket plutot que par appels � des macros python.<br>
 * 	Ce mode est pr�f�rable si les mouvements sont courts et fr�quents.<br>
 * 	Il est possible de changer le mode de communication en couirs d'utilisation, en invoquant le service ad�quat.<br>
 * 
 * 	@see server.rest.SettingsService#toggleMode()
 *
 */
public class DaemonPython extends Thread{
	

	/**
	 * 	Execution du d�mon lors de son appel.
	 */
	private void startDameon(){
		
		System.out.println("[PythonDameon] Starting...");
		try {
			String[] cmd = {
			        "python",
			        "/home/pi/scripts/server.py"
			    };
			Process p = Runtime.getRuntime().exec(cmd);
			System.out.println("[PythonDameon] Running... "+p.toString());
			Globals.pythond = p ;
		} catch (IOException e) {
			System.out.println("[PythonDameon] Fichier server.py non trouv�");
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
	
	/**
	 * 	Appel du Thread
	 * @Override java.lang.Thread#run()
	 */
	public void run(){
		this.startDameon();
	}
}
