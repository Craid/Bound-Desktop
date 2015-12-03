package de.craid.bound.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import de.craid.bound.Constants;

public class Server {

	public String ipString = "localhost";// "176.198.203.104";

	
	DatagramSocket socket;

	ArrayList<Client> clients;

	ArrayList<GameObject> players;

	int playerCount;

	Thread send, receive, manageUsers;
	boolean running;

	public Server() {
		clients = new ArrayList<Client>();
		players = new ArrayList<GameObject>();
		running = false;
		playerCount = 0;
		try {
			socket = new DatagramSocket(8082);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void establishNewConnection() {
	}

	public void receivePlayerObject() {

	}

	private void send() {

	}

	public void update() {
		receivePlayerObject();
		compute();
		send();
	}

	private void compute() {

	}

	public void start() {
		running = true;
		startManagingClients();
		startSending();
		startReceiving();
		System.out.println("Started Server!");
	}

	public void stop() {
		running = false;
	}

	private void startManagingClients() {
		manageUsers = new Thread("Manage") {
			public void run() {
				while (!running) { //TODO ! wegmachen, wenn Methode verwendet wird!
					sleepABit();
					// TODO
				}
			}
		};
		manageUsers.start();
	}

	private void startSending() {
		send = new Thread("Send") {
			public void run() {
				while (!running) { //TODO ! wegmachen, wenn Methode verwendet wird!
					sleepABit();
				}
			}
		};
		send.start();
	}

	private void sleepABit() {
		try {
			Thread.sleep(34);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void startReceiving() {
		receive = new Thread("Receive") {

			public void run() {
				while (running) {
					byte[] data = new byte[Constants.PACKAGE_SIZE];
					DatagramPacket packet = new DatagramPacket(data,
							data.length);
					try {
						socket.receive(packet);
						data = packet.getData();
						int id = ByteBuffer.wrap(data).getInt();
						
						if(id==0){
							//do nothing
						} else if (id == -1) {
							System.out.println("New Player");
							GameObject o = new GameObject(data);
							o.setID(++playerCount);
							players.add(new GameObject(data));
						} else {
							for (GameObject o : players) {
								if (o.getID() == id) {
									o.data = data;
									break;
								}
							}
						}

						packet.setData(data, 0, data.length);
						packet.setAddress(packet.getAddress());
						packet.setPort(packet.getPort());
						socket.send(packet);

					} catch (IOException e) {
						e.printStackTrace();
					}
					sleepABit();
				}
			}
		};
		receive.start();
	}

	public static void main(String args[]) {
		new Server().start();
	}

	class GameObject {

		byte[] data;

		public GameObject(byte[] data) {
			this.data = data;
		}

		public int getID() {
			return ByteBuffer.wrap(data).getInt();
		}

		public void setID(int id) {
			data = ByteBuffer.wrap(data).putInt(id).array();
		}
	}

}