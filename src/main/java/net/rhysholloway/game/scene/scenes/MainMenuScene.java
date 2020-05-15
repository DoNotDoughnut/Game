package net.rhysholloway.game.scene.scenes;

import net.rhysholloway.game.Game;
import net.rhysholloway.game.graphics.GameRenderer;
import net.rhysholloway.game.graphics.sprite.Sprite;
import net.rhysholloway.game.gui.GUIPanel;
import net.rhysholloway.game.gui.components.GUIButton;
import net.rhysholloway.game.scene.GameScene;

public class MainMenuScene extends GameScene {

	private GUIPanel buttonPanel;
	private GUIButton[] buttons;

	public MainMenuScene() {
		super();
	}

	@Override
	public void create() {

		int buttonAmount = 2;
		buttons = new GUIButton[buttonAmount];

		buttonPanel = new GUIPanel(8, 8, 124, buttons.length * 40 + 8, new Sprite(0x303030, 124, buttons.length * 40 + 4));

		buttons[0] = new GUIButton(new Sprite("/assets/gui/mainmenu/buttons/play.png"), 2 + buttonPanel.x, 4 + buttonPanel.y) {

			public void onClick() {
				Game.gsm.enable(1);
			}

		};
		
		/*

		buttons[1] = new GUIButton(new Sprite("/assets/gui/mainmenu/buttons/client.png"), 2 + buttonPanel.x, 4 + buttonPanel.y + 40 * 1) {

			// Start client

			public void onClick() {
				createClient();
				
			}

		};

		buttons[2] = new GUIButton(new Sprite("/assets/gui/mainmenu/buttons/server.png"), 2 + buttonPanel.x, 4 + buttonPanel.y + 40 * 2) {

			// Start server

			public void onClick() {
				MainMenuScene.createServer();
			}

		};

		*/

		buttons[buttonAmount - 1] = new GUIButton(new Sprite("/assets/gui/mainmenu/buttons/exit.png"), 2 + buttonPanel.x, 40 * (buttonAmount - 1) + 4 + buttonPanel.y) {

			public void onClick() {
				Game.close();
			}

		};

		for (GUIButton button : buttons) {
			buttonPanel.add(button);
		}
		
		buttonPanel.spawn();

	}
	
	/*

	Client client;
	Server server;

	protected void createServer() {
		System.out.println("Creating server");
		server = new Server(GameNetworking.defaultPort);
	}

	protected void createClient() {
		client = new Client("localhost", GameNetworking.defaultPort);
	}

	 */
	
	@Override
	public void dispose() {

	}

	//Sprite servSprite = new Sprite(0xFFFFFF);

	@Override
	public void update() {

		/*
		
		if (client != null) {
			String message = client.getMessage();
			if (message != null) {
				servSprite = new Sprite(Integer.parseInt(message));
				System.out.println(message);
			}
		} else
		if (server != null) {
			Random rand = new Random();
			int hex = rand.nextInt(16777215);
			server.updateClients(hex + "");
		}

		 */

		buttonPanel.update();
	}

	private static Sprite cheems = new Sprite("/assets/gui/mainmenu/cheems.png");

	@Override
	public void render() {
		buttonPanel.render();
		GameRenderer.render(180, 30, cheems, false);
		//GameRenderer.render(300, 4, servSprite, false);
	}

}
