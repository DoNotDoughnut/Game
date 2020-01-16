package rhys.game.objects.gui.components;

import java.awt.Color;
import java.awt.Font;

import rhys.game.main.GameRenderer;
import rhys.game.objects.entity.entities.GameText;
import rhys.game.objects.gui.GUIComponent;

public class GUILabel extends GUIComponent {

	public String text;
	public GameText textbox;
	
	public GUILabel(int x, int y, Font font, Color color, String text, boolean precise) {
		super(x,y);
		this.text = text;
		textbox = new GameText(x, y, font, color, text, precise);
	}
	
	public GUILabel(int x, int y, Font font, Color color, String text) {
		super(x,y);
		this.text = text;
		textbox = new GameText(x, y, font, color, text, false);
	}
	
	public void update() {
		if(textbox.text!=text)
			textbox.text = text;
		textbox.x = x;
		textbox.y = y;
	}
	
	public void render(GameRenderer graphics) {
		//Leave here to override
	}
	
	public void spawn() {
		x = startX;
		y = startY;
		alive = true;
		textbox.spawn();
	}
	
	public void despawn() {
		alive = false;
		textbox.despawn();
	}

}
