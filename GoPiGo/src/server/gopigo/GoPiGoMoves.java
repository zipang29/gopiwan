package server.gopigo;

import java.io.IOException;


public interface GoPiGoMoves {
	

	public void turnRight() throws IOException;
	
	public void turnLeft() throws IOException;
	
	public void forward() throws IOException;
	
	public void backward() throws IOException;
	
	public void stop() throws IOException ;
}
