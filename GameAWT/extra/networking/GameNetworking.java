package rhys.game.networking;

public class GameNetworking {
	
	public static int defaultPort = 5284;
	
	Client client;
	Server server;
	
	public GameNetworking() {
		client = new Client("localhost", defaultPort);
	}

}
