package server.gopigo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import server.Globals;
import server.daemons.DaemonPython;
import server.daemons.DaemonStreaming;

public class GoPiGo implements GoPiGoMoves, GoPiGoStreaming{
	
	public enum Mode{
		
		SOCKET,
		PYTHON,
		DEBUG ;
	}
	
	public Mode mode ;
	
	private Socket socket ;
		
	private DataOutputStream out ;
	
	private DaemonPython pythond ;
	
	private DaemonStreaming stream ;
	
	public GoPiGo() throws IOException{

		this.mode = Mode.DEBUG ;
		System.out.println("Virtual Gopigo started...");
	}
	
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
