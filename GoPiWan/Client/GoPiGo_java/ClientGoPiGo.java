package test;

import java.io.*;
import java.net.*;

class ClientGoPiGo
{
	public static void main(String argv[]) throws Exception
	{
		
		System.out.println("Avancer : f Reculer : b Stop : s Gauche : l  Droite : r");
		String msg;
		BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
		Socket clientSocket = new Socket("localhost", 5005);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

		while(true)
		{
			msg = inFromUser.readLine();
			outToServer.writeBytes(msg);
		}
		
	}
}