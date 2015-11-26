package de.craid.bound.net;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
	
	DatagramSocket socket; // UDP = User Datagram Protocol~
 // Socket[]         socket; // TCP
	
	public Server(){
		try {
			socket = new DatagramSocket(11333);//TODO int wert als Port übergeben
		} catch (SocketException e) {
			e.printStackTrace();
		 
	}
	
	public void receveive(){
		
	}
	
	private void send(){
		
	}
	
	private void update(){
		receveive();
		compute();
		send();
	}

	private void compute() {
		//Welt berechnet
	}

}
