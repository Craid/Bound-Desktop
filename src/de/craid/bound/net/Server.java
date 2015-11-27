package de.craid.bound.net;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
	
	public String ipString = "176.198.203.104";
	
	DatagramSocket socket;
	
	public Server(){
		try {
			socket = new DatagramSocket(11333);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void establishNewConnection(){}
	
	public void receivePlayerObject(){
		
	}
	
	private void send(){
		
	}
	
	public void update(){
		receivePlayerObject();
		compute();
		send();
	}

	private void compute() {
		
	}

}