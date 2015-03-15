package server;

import java.io.IOException;

public class GoPiGo implements GoPiGoMovesI{
		
	public GoPiGo(){
		
	}

	@Override
	public void turnRight() {
		if(Globals.verbose) System.out.println("Turn Right");
		try {
			Runtime.getRuntime().exec("python turn_right.py") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void turnLeft() {
		if(Globals.verbose) System.out.println("Turn Left");
		try {
			Runtime.getRuntime().exec("python turn_left.py") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void forward() {
		if(Globals.verbose) System.out.println("Go Forward");
		try {
			Runtime.getRuntime().exec("python forward.py") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void backward() {
		if(Globals.verbose) System.out.println("Go Backward");
		try {
			Runtime.getRuntime().exec("python backward.py") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		
		try {
			Runtime.getRuntime().exec("python stop.py") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
