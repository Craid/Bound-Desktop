package de.craid.bound.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import de.craid.bound.Player;

public class Client {

	private MulticastSocket groupSocket;
	private DatagramSocket socket;

	public ArrayList<Player> playerList;

	// darauf achten, dass der gesetzt wird
	public Player player;
	private String serverIP = "localhost";
	private String groupIP = "176.198.203.104";
	private static final int SERVER_PORT = 8082;
	private static final int GROUP_PORT = 8083;
	public InetAddress serverAddress;
	public InetAddress groupAddress;
	private static final int TIME_OUT = 5000;

	public Client() {
		playerList = new ArrayList<Player>();
	}

	/**
	 * Nachdem ein Spieler gesetzt wurde, kann die Connection aufgebaut werden.
	 * Der Spieler darf dafür nicht leer sein!
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
		if (player != null)
			openConnection();
		receive();
	}

	private void openConnection() {
		try {
			socket = new DatagramSocket();
			socket.setSoTimeout(TIME_OUT);
			serverAddress = InetAddress.getByName(serverIP);
			groupSocket = new MulticastSocket(GROUP_PORT);
			groupAddress = InetAddress.getByName(groupIP);
			groupSocket.joinGroup(groupAddress);
		} catch (Exception e) {
			System.out.println("Error occured!");
		}

	}

	/**
	 * Hier senden wir die eigenen Player Informationen an den Server
	 */
	public void sendPlayerData() {
		byte[] a = player.send();
		send(a);
	}

	public void send(byte[] a) {
		DatagramPacket packet = new DatagramPacket(a, a.length, serverAddress,
				SERVER_PORT);
		try {
			socket.send(packet);
		} catch (Exception e) {
		}
	}

	/**
	 * Der Server broadcastet die ganze Zeit über Informationen über Spieler.
	 * Jeder Spieler wird in einem DatagramPacket dargestellt.
	 * 
	 * Diese Methode greift die einzelnen DatagramPAckets auf und aktualisiert
	 * damit die anderen Spieler positionen.
	 */
	public void receive() {
		byte[] a;
		DatagramPacket packet;

		try {
			while(true){
				a = new byte[20];
				packet = new DatagramPacket(a, a.length);
				groupSocket.receive(packet);
				processPacket(packet.getData());
			}
		} catch (IOException e) {
		}
		
	}

	/**
	 * Verarbeitet das Paket. 
	 * In diesem Paket ist stets genau ein Player Objekt enthalten.
	 * Dieses kann entweder in der Liste oder ein neuer Spieler sein.
	 * Im ersten Fall werden dessen aktuelle Koordinaten und Bewegungrichtung überschrieben.
	 * Im zweiten Fall wird der Spieler neu angelegt.
	 * 
	 * @param a
	 */
	private void processPacket(byte[] a) {
		int id = (byte) (a[0] << 24) + (byte) (a[1] << 16) + (byte) (a[2] << 8)
				+ (byte) (a[3]); // signed << und unsigned <<< = 1 bit f�r minus
									// oder plus

		boolean playerNotInList = true;
		for (Player p : playerList) {
			if (p.id == id) {
				playerNotInList = false;
				// player erhält bytes die Position und direction enthalten
				p.receive(a);
				break;
			}
		}

		if (playerNotInList) {
			Player player = new Player();
			player.receive(a);
			playerList.add(player);
		}
	}
}