package rhys.game.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

	private DatagramSocket socket;
	private InetAddress serverAddress;
	private int port, clientId;
	
	private String lastMessage;

	public Client(String address, int port) {

		try {
			this.serverAddress = InetAddress.getByName(address);
			this.port = port;

			socket = new DatagramSocket();

			receive();
		} catch (SocketException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void receive() {
		Thread thread = new Thread() {

			public void run() {
				try {
					byte[] rawData = new byte[512];
					DatagramPacket packet = new DatagramPacket(rawData, rawData.length);
					socket.receive(packet);

					String message = new String(rawData);
					message = message.substring(0, message.indexOf("\\e"));

					if (!parseMessage(message)) {
						lastMessage = message;
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		};
		thread.run();

	}
	
	public void write(String message) {
		try {
			message = message + "\\n";
			byte[] data = message.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, port);
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean parseMessage(String message) {
		if(message.startsWith("\\cid:")) {
			this.clientId = Integer.parseInt(message.substring(5));
			return true;
		}
		return false;
	}

	public int getClientId() {
		return clientId;
	}

	public String getMessage() {
		return lastMessage;
	}

}
