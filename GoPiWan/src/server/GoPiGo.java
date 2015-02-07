package server;

import java.io.IOException;

import com.ergotech.brickpi.BrickPiCommunications;
import com.ergotech.brickpi.BrickPiUpdateListener;
import com.ergotech.brickpi.RemoteBrickPi;
import com.ergotech.brickpi.motion.Motor;

import config.Configuration;
import config.Debug;

/**
 * Classe principale de l'API, représentant le robot.
 * Il est ici possible :
 * 		- d'inréagir directement avec le robot via les methodes de mouvement
 * 		- de récupérer les flux de ses capteurs via les methodes de récupération
 * 
 * 
 * @author Nicolas
 *
 */
public class GoPiGo extends RemoteBrickPi implements GoPiGo_Actions, BrickPiUpdateListener{

	int debugLevel ;
	
	Configuration config ;
	
	private Motor motorLeft, motorRight ;

	
	public GoPiGo(int debugLevel, Configuration config){
		
		this.debugLevel = debugLevel ;
		this.config = config ;
		init() ;
	}
	
	
	/// BrickPiUpdateListener

	@Override
	public <T extends BrickPiCommunications> void updateReceived(T source) {
		// TODO Auto-generated method stub
		
	}
	
	
	/// GoPiGo_Actions

	@Override
	public void init() {
	
        try {
        	        	
        this.setPiAddress("localhost");
        this.setTimeout(60000);	// time out (ms)
        
        motorLeft = new Motor();
        motorRight = new Motor() ;
        motorLeft.setCommandedOutput(0);
        motorLeft.setEnabled(true);
        motorRight.setCommandedOutput(0);
        motorRight.setEnabled(true);

        this.setMotor(motorLeft, Device.MOTOR_LEFT);	// brancher les moteurs sur les bons ports
        this.setMotor(motorRight, Device.MOTOR_RIGHT) ;

        if (debugLevel >= Debug.VERBOSE) System.out.println("[GoPiWanServer] : Testing Motors");
        motorLeft.rotate(0.25, 1);	// petits mouvements de test
        motorRight.rotate(0.25, 1);
        Thread.sleep(5000); // wait ...
        
        } catch (InterruptedException | IOException e) { System.err.println(e.getMessage()) ;}
        
        if (debugLevel >= Debug.VERBOSE) System.out.println("[GoPiWanServer] : Ready");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnLeft(float distance, float speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(PrimitiveMove move) {
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
