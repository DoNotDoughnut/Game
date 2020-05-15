package rhys.game.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class Server {

	private DatagramSocket socket;
	private int port;
	private boolean online;

	private ArrayList<ClientObject> clients = new ArrayList<ClientObject>();
	private int clientIDCounter = -1;

	private String lastMessage;

	public Server(int port) {
		try {
			this.port = port;
			socket = new DatagramSocket(this.port);
			online = true;
			System.out.println("Creating server");
			receive();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		

	}

	public void receive() {
		Thread thread = new Thread() {

			public void run() {
				try {
					System.out.println("Waiting for message");
					byte[] rawData = new byte[512];
					DatagramPacket packet = new DatagramPacket(rawData, rawData.length);
					socket.receive(packet);

					String message = new String(rawData);
					message = message.substring(0, message.indexOf("\\e"));

					if (!parseMessage(message, packet)) {
						lastMessage = message;
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		};
		thread.run();
	}

	public boolean parseMessage(String message, DatagramPacket packet) {
		if (message.startsWith("\\c")) {
			// Connect the client to the server
			ClientObject client = new ClientObject(packet.getAddress().toString(), packet.getPort(), clientIDCounter + 1);
			clients.add(client);
			write("\\cid:"+client.getId(), client.getAddress(), client.getPort());
			clientIDCounter++;
			return true;

		} else if (message.startsWith("\\d")) {
			int id = Integer.parseInt(message.substring(3));
			for (int i = 0; i < clients.size(); i++) {
				if (clients.get(i).getId() == id) {
					clients.remove(i);
					return true;
				}
			}
			System.err.println("Server error: Client id +" + id + " not found.");
		}
		return false;
	}

	public void write(String message, String ip, int port) {
		try {
			message = message + "\\n";
			byte[] data = message.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(ip), port);
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateClients(String message) {
		for( int i = 0; i < clients.size(); i++) {
			ClientObject client = clients.get(i);
			write(message, client.getAddress(), client.getPort());
		}
	}
	
	@Deprecated
	public void read(String message) {

	}

	public int getPort() {
		return port;
	}

	public boolean isOnline() {
		return online;
	}

	public ArrayList<ClientObject> getClients() {
		return clients;
	}

	public String getMessage() {
		String m = lastMessage;
		lastMessage = null;
		return m;
	}
	
	

}
