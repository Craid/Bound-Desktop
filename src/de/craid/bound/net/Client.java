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
	
	//darauf achten, dass der gesetzt wird
	public Player dasSindWirPlayer;
	
	public String name;
	public String serverIP = "176.198.203.104";
	public int serverPort = 11333;
	public InetAddress ip;
	
	public Client(String name){
		this.name = name;
		playerList = new ArrayList<Player>();
	}
	
	public void openConnection(){
		try{
			socket = new DatagramSocket();
			ip = InetAddress.getByName(serverIP);
		}catch(Exception e){
			System.out.println("Error occured!");
		}
	}

	/**
	 * Hier senden wir die eigenen Player Informationen an den Server
	 */
	public void send(){
		byte[] a  = dasSindWirPlayer.send();
		DatagramPacket packet = new DatagramPacket(a, a.length);
		try{
			socket.send(packet);
		}catch(Exception e){}
	}
	
	/**
	 * Der Server broadcastet die ganze Zeit über Informationen über Spieler.
	 * Jeder Spieler wird in einem DatagramPacket dargestellt.
	 * 
	 * Diese Methode greift die einzelnen DatagramPAckets auf und aktualisiert damit die anderen Spieler positionen.
	 */
	public void receive(){
		byte[] a  = new byte[20];
		DatagramPacket packet = new DatagramPacket(a, a.length);
		
		try{
			socket.receive(packet);
		}catch(IOException e){}
		a = packet.getData();
		int id = (byte)(a[0] << 24) + (byte)(a[1] << 16) + (byte)(a[2] << 8) + (byte)(a[3]); //signed << und unsigned <<< = 1 bit für minus oder plus
	
		boolean PlayerNotInList = true;
		for(Player p : playerList){
			if(p.id == id){
				PlayerNotInList = false;
				//player erhält bytes die Position und direction enthalten
				p.receive(a);
				break;
			}
		}
		
		if(PlayerNotInList){
			Player player = new Player();
			player.receive(a);
			playerList.add(player);
		}
	}
}
