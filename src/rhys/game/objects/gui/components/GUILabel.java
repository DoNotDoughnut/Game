package rhys.game.objects.gui.components;

import java.awt.Color;

import rhys.game.main.Game;
import rhys.game.main.GameRenderer;
import rhys.game.objects.entity.entities.GameText;
import rhys.game.objects.gui.GUIComponent;

public class GUILabel extends GUIComponent {

	public String text;
	
	public GameText textbox;
	
	public GUILabel(int x, int y, String text, Color color) {
		super(x,y);
		this.text = text;
		textbox = new GameText(x, y, text, color);
	}
	
	public void render(GameRenderer graphics) {
		textbox.spawn();
	}
	
	public void update() {
		textbox.text = text;
		textbox.updateGUI(Game.guiManager.alive);
	}

}
