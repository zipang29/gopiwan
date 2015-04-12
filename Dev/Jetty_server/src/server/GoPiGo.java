package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

public class GoPiGo implements GoPiGoI{
		
	public GoPiGo(){
		
	}

	
	@Override
	public void turnLeft() {
		if(Globals.verbose) System.out.println("Turn Left");
		try {
			String[] cmd = {"/bin/bash","-c","python py/left.py"};
			Runtime.getRuntime().exec(cmd) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void turnRight() {
		if(Globals.verbose) System.out.println("Turn Right");
		try {
			Runtime.getRuntime().exec("python") ;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void forward() {
		if(Globals.verbose) System.out.println("Go Forward");
		try {
			Runtime.getRuntime().exec("python py/forward.py") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void backward() {
		if(Globals.verbose) System.out.println("Go Backward");
		try {
			Runtime.getRuntime().exec("python serveur.py/backward.py");
			//Runtime.getRuntime().exec("xterm");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() throws IOException {
		if(Globals.verbose) System.out.println("Stop");
		
			/*URL url = this.getClass().getResource("server/py/script.py");
			String path = "";
			try {
				path = url.toURI().toString();
				System.out.println("Echec");
			} catch (URISyntaxException e) 
			{
				System.out.println("Echec");
				e.printStackTrace();
			}*/
			//System.out.println(path);
		


			ClassLoader.class.getResourceAsStream("/server/py/script.py");


			InputStream is = this.getClass().getClassLoader().getResourceAsStream("bin/server/py/script.py");
			System.out.println(is);
			
		
			String [] cmd = new String[]{"xterm","-hold","-e","python","bin/server/py/script.py"};
			//String [] cmd = new String[]{"xterm","-hold","-e","pwd"};
			Runtime.getRuntime().exec(cmd) ;
	}

	@Override
	public void startStreaming() {
		// TODO Auto-generated method stub
		
	}

}
