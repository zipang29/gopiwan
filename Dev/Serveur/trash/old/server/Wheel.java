package server;

import java.io.IOException;

/**
 * Approche Threadée du mouvement d'une roue.
 * 
 * Inutilisable en l'état, avec les appels Python
 * 
 * @author Nicolas
 *
 */
public class Wheel extends Thread{

	//int speed ;
	Runtime run ;
	Device side ;
	
	public Wheel(Device dev){
		//this.speed = 0 ;
		this.run = Runtime.getRuntime() ;
		this.side = dev ;
	}
	
	public void increaseSpeed() throws IOException{
		
		run.exec("python /macros_python/increase_speed.py");
	}
	
	public void decreaseSpeed() throws IOException{
		
		run.exec("python /macros_python/decrease_speed.py");
	}
	
	public void halt() throws IOException{
		
		run.exec("python /macros_python/stop.py") ;
	}
	
	public void run(){
		try{
			switch (side) {
			
				case MOTOR_LEFT : {
					run.exec("python /macros_python/left.py");
					break ;
				}
				case MOTOR_RIGHT : {
					run.exec("python /macros_python/right.py");
					break ;
				}
				default : break ;
			}
		}catch (IOException e){ e.printStackTrace(); }
	}
}
