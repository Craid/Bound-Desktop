package de.craid.bound.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import de.craid.bound.Player;

public class Client {
	
	public DatagramSocket socket;
	
	public ArrayList<Player> playerList;
	
	public String name;
	public String ipString = "176.198.203.104";
	public int port = 11333;
	public InetAddress ip;
	
	public Client(String name){
		this.name = name;
		playerList = new ArrayList<Player>();
	}
	
	public void openConnection(){
		try{
			socket = new DatagramSocket();
			ip = InetAddress.getByName(ipString);
		}catch(Exception e){
		}
	}
	
	public void send(){
		
		
	}
	
	public void receive(){
		byte[] a  = new byte[20];
		DatagramPacket packet = new DatagramPacket(a, a.length);
		try{
			socket.receive(packet);
		}catch(IOException e){}
		
		Player player = new Player();
		
	}
}
