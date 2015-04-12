package server;

import config.Configuration;

public class GoPiGo implements GoPiGo_Actions{
	
	private Wheel leftWheel, rightWheel ;
	
	private int debug ;

	public GoPiGo(int vERBOSE, Configuration configuration) {
		this.debug = vERBOSE ;
		init() ;
	}

	@Override
	public void init() {
		leftWheel = new Wheel(Device.MOTOR_LEFT) ;		
		rightWheel = new Wheel(Device.MOTOR_RIGHT) ;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forward(float distance, float speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void back(float distance, float speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnRight(float distance, float speed) {
		rightWheel.run() ;
	}

	@Override
	public void turnLeft(float distance, float speed) {
		leftWheel.run() ;
	}

	@Override
	public void move(Move move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(CompositeMove way) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void on(Device device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void off(Device device) {
		// TODO Auto-generated method stub
		
	}

}
