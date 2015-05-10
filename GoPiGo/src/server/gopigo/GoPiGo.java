package server.gopigo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import server.Globals;
import server.daemons.DaemonPython;
import server.daemons.DaemonStreaming;
/**
 * 	Interface entre l'API (appels utilisateurs), les démons et le matériel.<br>
 * 	L'instance de cette classe, selon le {@link #mode} actuel,<br>
 * 	va communiquer avec les PIN de la carte GoPiGo.<br>
 * 	<br>
 * 	Dans le cas du mode SOCKET, un packet contenant l'action à effectuer est envoyée<br>
 * 	au DaemonPython {@link #pythond}.<br>
 *  Sinon, un interpréteur est éxécuté avec le code idoine.<br>
 *
 */
public class GoPiGo implements GoPiGoMoves, GoPiGoStreaming{
	
	/**
	 *	Modes de communication interne possibles
	 */
	public enum Mode{
		
		SOCKET,
		PYTHON,
		DEBUG ;
	}
	
	/**
	 * Mode de communication interne
	 */
	public Mode mode ;
	
	/**
	 * Socket de sortie vers le DaemonPython
	 */
	private Socket socket ;
	
	/**
	 * {@link #socket}
	 */
	private DataOutputStream out ;
	
	/**
	 * Démon Python pouvant recevoir les ordres
	 */
	private DaemonPython pythond ;
	
	/**
	 * Processus d'acquisition vidéo
	 */
	private DaemonStreaming stream ;
	
	/**
	 * Constructeur debug
	 * @throws IOException
	 */
	public GoPiGo() throws IOException{

		this.mode = Mode.DEBUG ;
		System.out.println("Virtual Gopigo started...");
	}
	
	/**
	 * Initialise une instance dans un mode de communication concret
	 * @param mode
	 * @throws IOException
	 */
	public GoPiGo(String mode) throws IOException{

		this.mode = Mode.valueOf(mode);
		this.stream = new DaemonStreaming() ;
		
		if(this.mode == Mode.SOCKET){
			pythond = new DaemonPython() ;
			pythond.start();
			//Runtime.getRuntime().exec("python /home/pi/scripts/server.py &") ;
			socket = new Socket( InetAddress.getLocalHost(), 5005) ;
			out = new DataOutputStream(socket.getOutputStream());
		}
		System.out.println("GoPiGo started in mode "+mode+"...");
	}

	@Override
	public void turnRight() throws IOException {
		switch (this.mode){
			case SOCKET : {
				out.writeUTF("right");
				out.flush();
				break ;
			}
			case PYTHON : {
				String [] cmd = new String[]{"python","/home/pi/scripts/right.py"};
				Runtime.getRuntime().exec(cmd);
				break ;
			}
			default : break ;
		}
		System.out.println("[Gopigo] : Turning right...");
	}
	
	@Override
	public void turnRightRot() throws IOException {
		switch (this.mode){
			case SOCKET : {
				out.writeUTF("right_rot");
				out.flush();
				break ;
			}
			case PYTHON : {
				String [] cmd = new String[]{"python","/home/pi/scripts/right_rot.py"};
				Runtime.getRuntime().exec(cmd);
				break ;
			}
			default : break ;
		}
		System.out.println("[Gopigo] : Turning right in rotation...");
	}

	@Override
	public void turnLeft() throws IOException {
		switch (this.mode){
		case SOCKET : {
			out.writeUTF("left");
			out.flush();
			break ;
		}
		case PYTHON : {
			String [] cmd = new String[]{"python","/home/pi/scripts/left.py"};
			Runtime.getRuntime().exec(cmd);
			break ;
		}
		default : break ; 
	}
		System.out.println("[Gopigo] : Turning left...");
	}
	
	@Override
	public void turnLeftRot() throws IOException {
		switch (this.mode){
		case SOCKET : {
			out.writeUTF("left_rot");
			out.flush();
			break ;
		}
		case PYTHON : {
			String [] cmd = new String[]{"python","/home/pi/scripts/left_rot.py"};
			Runtime.getRuntime().exec(cmd);
			break ;
		}
		default : break ; 
	}
		System.out.println("[Gopigo] : Turning left in rotation...");
	}

	@Override
	public void forward() throws IOException {
		switch (this.mode){
		case SOCKET : {
			out.writeUTF("fwd");
			out.flush();
			break ;
		}
		case PYTHON : {
			String [] cmd = new String[]{"python","/home/pi/scripts/forward.py"};
			Runtime.getRuntime().exec(cmd);
			break ;
		}
		default : break ;
	}
		System.out.println("[Gopigo] : Going forward...");
	}

	@Override
	public void backward() throws IOException {
		switch (this.mode){
		case SOCKET : {
			out.writeUTF("bwd");
			out.flush();
			break ;
		}
		case PYTHON : {
			String [] cmd = new String[]{"python","/home/pi/scripts/backward.py"};
			Runtime.getRuntime().exec(cmd);
			break ;
		}
		default : break ;
	}
		System.out.println("[Gopigo] : Going backward...");
	}

	@Override
	public void stop() throws IOException {
		switch (this.mode){
		case SOCKET : {
			out.writeUTF("stop");
			out.flush();
			break ;
		}
		case PYTHON : {
			String [] cmd = new String[]{"python","/home/pi/scripts/stop.py"};
			Runtime.getRuntime().exec(cmd);
			break ;
		}
		default : break ;
	}
		System.out.println("[Gopigo] : Stopped !");
	}
	
	@Override
	public void increaseSpeed() throws IOException {
		switch (this.mode){
		case SOCKET : {
			out.writeUTF("speed++");
			out.flush();
			break ;
		}
		case PYTHON : {
			String [] cmd = new String[]{"python","/home/pi/scripts/increase_speed.py"};
			Runtime.getRuntime().exec(cmd);
			break ;
		}
		default : break ;
	}
		System.out.println("[Gopigo] : Speed ++ !");		
	}

	@Override
	public void decreaseSpeed() throws IOException {
		switch (this.mode){
		case SOCKET : {
			out.writeUTF("speed--");
			out.flush();
			break ;
		}
		case PYTHON : {
			String [] cmd = new String[]{"python","/home/pi/scripts/decrease.py"};
			Runtime.getRuntime().exec(cmd);
			break ;
		}
		default : break ;
	}
		System.out.println("[Gopigo] : Speed -- !");		
	}

	@Override
	public void setSpeed(int speed) throws IOException {
		switch (this.mode){
		case SOCKET : {
			out.writeUTF("speed:"+speed);
			out.flush();
			break ;
		}
		case PYTHON : {
			String [] cmd = new String[]{"python","/home/pi/scripts/set_speed.py", String.valueOf(speed)};
			Runtime.getRuntime().exec(cmd);
			break ;
		}
		default : break ;
	}
		System.out.println("[Gopigo] : Set speed to "+speed+" !");		
	}

	@Override
	public void startStreaming() {

		stream.run();
		System.out.println("[Gopigo] : Start Streaming...");
	}

	@Override
	public void stopStreaming() throws IOException {
		
		stream.stopDaemon();
		System.out.println("[Gopigo] : Streaming stopped !");
	}

	@Override
	public void setResolution(int h, int w) {
		
		stream.stopDaemon();
		Globals.StreamingHeight = String.valueOf(h) ;
		Globals.StreamingWidth = String.valueOf(w) ;
		stream = new DaemonStreaming() ;
		startStreaming() ;
	}

	@Override
	public void setFPS(int fps) {

		stream.stopDaemon();
		Globals.fps = String.valueOf(fps) ;
		stream = new DaemonStreaming() ;
		startStreaming() ;
	}
}
