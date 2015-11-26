package de.craid.bound.net;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class Server {
	
	DatagramSocket socket;
	ArrayList<Connection> connections = new ArrayList<Connection>();
	
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
	
	private void update(){
		receivePlayerObject();
		compute();
		send();
	}

	private void compute() {
		
	}

}
