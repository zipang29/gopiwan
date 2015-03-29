package server;


public class GoPiGo implements GoPiGoMovesI{
	
	private GoPiGoCommunication comm ;
		
	public GoPiGo(){
		
		comm = new GoPiGoCommunication(5005) ;
	}

	@Override
	public void turnRight() {
		if(Globals.verbose) System.out.println("Turn Right");

		comm.send("right") ;
	}

	@Override
	public void turnLeft() {
		if(Globals.verbose) System.out.println("Turn Left");

		comm.send("left") ;
	}

	@Override
	public void forward() {
		if(Globals.verbose) System.out.println("Go Forward");

		comm.send("forward") ;
	}

	@Override
	public void backward() {
		if(Globals.verbose) System.out.println("Go Backward");

		comm.send("backward") ;
	}

	@Override
	public void stop() {
		
		comm.send("stop") ;
	}

}
