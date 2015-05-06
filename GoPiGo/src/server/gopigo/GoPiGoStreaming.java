package server.gopigo;

import java.io.IOException;

public interface GoPiGoStreaming {

	public void startStreaming() throws IOException;
	
	public void stopStreaming() throws IOException;
}
