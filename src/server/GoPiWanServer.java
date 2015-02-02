package server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ergotech.brickpi.BrickPiCommunications;
import com.ergotech.brickpi.BrickPiTests;
import com.ergotech.brickpi.BrickPiUpdateListener;
import com.ergotech.brickpi.RemoteBrickPi;
import com.ergotech.brickpi.motion.Motor;
import com.ergotech.brickpi.sensors.RawSensor;
import com.ergotech.brickpi.sensors.TouchSensor;
import com.ergotech.brickpi.sensors.UltraSonicSensor;
import com.ergotech.brickpi.sensors.UltraSonicSensorSS;

public class GoPiWanServer implements BrickPiUpdateListener{
	
	/// TODO FIELDS
	
	private static final int DEFAULT_PORT = 7500 ;
	private static final String DEFAULT_ADDRESS = "192.168.1.42" ;
	
	private final boolean debug = true ;
	
	RemoteBrickPi brickPi ;
	
	private Motor motorLeft, motorRight ;

	
	private DatagramSocket socket ;
	
	
	/// TODO CONSTRUCTORS
	
	public GoPiWanServer() throws SocketException{
		
		socket = new DatagramSocket(DEFAULT_PORT) ;
		this.init();
	}
	
	public GoPiWanServer(int port) throws SocketException{
		
		socket = new DatagramSocket(port) ;
		this.init();
	}
	
	
	/// TODO METHODS
	
	/**
	 * Initialise le robot
	 * Met en marche les différents capteurs
	 */
	public void init(){
		
		brickPi = new RemoteBrickPi();
        brickPi.setPiAddress(DEFAULT_ADDRESS);
        try {
            brickPi.setTimeout(60000);	// time out (ms)
        } catch (IOException ex) { }
        
        motorLeft = new Motor();
        motorRight = new Motor() ;
        motorLeft.setCommandedOutput(0);
        motorLeft.setEnabled(true);
        motorRight.setCommandedOutput(0);
        motorRight.setEnabled(true);

        brickPi.setMotor(motorLeft, 0);
        brickPi.setMotor(motorRight, 1) ;

        if (debug) System.out.println("[GoPiWanServer] : Test Motors");
        motorLeft.rotate(0.25, 1);	// petits mouvements de test
        motorRight.rotate(0.25, 1);
        try {
            Thread.sleep(5000); // wait for the values to be read....
        } catch (InterruptedException ex) {        }
        
        if (debug) System.out.println("[GoPiWanServer] : Ready");
    }
	
	public void listen(){
		
        if (debug) System.out.println("[GoPiWanServer] : listening on port "+socket.getPort()+"...");

		
	}
	
	/**
	 * Avancer tout droit
	 */
	public void up(){
		try{
			Thread left = new MotorMotion(10, 1, MotorSide.LEFT ) ;
			Thread right = new MotorMotion(10, 1, MotorSide.RIGHT ) ;
			left.start();
			right.start();
			left.join();
		}catch(InterruptedException e){ System.out.println("[GoPiWanServer] : Command up interrupted") ; }

	}
	
	/**
	 * Reculer
	 */
	public void back(){
		
		
	}
	
	public void left(){
		
	}
	
	public void right(){
		
	}

	@Override
	public <T extends BrickPiCommunications> void updateReceived(T source) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Thread animant un moteur donné d'un mouvement à effecter a une certaine vitesse
	 * Ne concerne qu'un moteur à la fois
	 * 
	 * @author Nicolas
	 *
	 */
	class MotorMotion extends Thread{
		
		private int speed ; // speed performed
		
		private int dist ;	// distance
		
		private MotorSide side ; // the motor actionned by current Thread
		
		public MotorMotion(int dist, int speed, MotorSide side){
			
			this.speed = speed ;
			this.dist = dist ;
			this.side = side ;
		}
		
		public MotorMotion(MotorSide side){
			
			this.speed = 1 ;
			this.dist = 3 ;
			this.side = side ;
		}
		
		public void run(){
			
			switch (side){
			
				case LEFT : {
					motorLeft.rotate(dist, speed);
					break ;
				}
				case RIGHT : {
					motorRight.rotate(dist, speed);
					break ;
				}
			}
		}
	}
	
	enum MotorSide{ LEFT, RIGHT ; }
	

}
