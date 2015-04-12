package server;

import java.io.IOException;

public class PythonDaemon extends Thread{

	public void run(){
		
		try {
			Runtime.getRuntime().exec("python python_call/server.py") ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
