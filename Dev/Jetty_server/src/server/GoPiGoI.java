package server;

import java.io.IOException;


/**
 * @author Nicolas
 *
 */
public interface GoPiGoI {
	

	public void turnRight() throws IOException;
	public void turnLeft() throws IOException;
	public void forward() throws IOException;
	public void backward() throws IOException;
	public void stop() throws IOException;
	public void startStreaming() throws IOException;
	public void stopStreaming() throws IOException;

}
