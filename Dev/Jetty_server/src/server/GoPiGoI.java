package server;

import java.io.IOException;


/**
 * @author Nicolas
 *
 */
public interface GoPiGoI {
	

	public void turnRight() ;
	public void turnLeft() ;
	public void forward() ;
	public void backward() ;
	public void stop() throws IOException ;
	public void startStreaming();

}
