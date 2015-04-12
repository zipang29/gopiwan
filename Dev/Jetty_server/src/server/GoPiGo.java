package server;

/**
 * @author David Barrat
 */

import java.io.IOException;

public class GoPiGo implements GoPiGoI{

	public GoPiGo() throws IOException{
		startStreaming();
	}

	@Override
	public void turnRight() throws IOException {
		String [] cmd = new String[]{"python","/home/pi/scripts/right.py"};
		Runtime.getRuntime().exec(cmd);
	}

	@Override
	public void turnLeft() throws IOException {
		String [] cmd = new String[]{"python","/home/pi/scripts/left.py"};
		Runtime.getRuntime().exec(cmd);
	}

	@Override
	public void forward() throws IOException {
		String [] cmd = new String[]{"python","/home/pi/scripts/forward.py"};
		Runtime.getRuntime().exec(cmd);
	}

	@Override
	public void backward() throws IOException {
		String [] cmd = new String[]{"python","/home/pi/scripts/backward.py"};
		Runtime.getRuntime().exec(cmd);
	}

	@Override
	public void stop() throws IOException {
		String [] cmd = new String[]{"python","/home/pi/scripts/stop.py"};
		Runtime.getRuntime().exec(cmd);
	}

	@Override
	public void startStreaming() throws IOException {
		//System.out.println("Start stream");
		String [] cmd = new String[]{"python","/home/pi/scripts/camera_streamer.py","startStreaming"};
		Runtime.getRuntime().exec(cmd);
	}

	@Override
	public void stopStreaming() throws IOException {
		//System.out.println("Stop stream");
		String [] cmd = new String[]{"python","/home/pi/scripts/camera_streamer.py","stopStreaming"};
		Runtime.getRuntime().exec(cmd);
	}



}
