package rhys.game.objects.gui.components;

import java.awt.Color;
import java.awt.Font;

import rhys.game.main.graphics.Renderer;
import rhys.game.objects.entity.entities.GameText;
import rhys.game.objects.gui.GUIComponent;

public class GUILabel extends GUIComponent {

	public String text;
	public GameText textbox;
	private int scale;
	
	public GUILabel(int x, int y, Font font, Color color, String text) {
		super(x,y);
		this.text = text;
		textbox = new GameText(x, y, font, color, text);
		scale = textbox.getScale();
	}
	
	public void update() {
		if(textbox.text!=text)
			textbox.text = text;
	}
	
	public void render(Renderer graphics) {
		//Leave here to override
	}
	
	public void spawn() {
		alive = true;
		textbox.spawn();
	}
	
	public void despawn() {
		alive = false;
		textbox.despawn();
	}
	
	public void move(int newX, int newY) {
		x += newX*scale;
		y += newY*scale;
		textbox.x = x;
		textbox.y = y;
	}
	
	public void resetPos() {
		x = startX*scale;
		y = startY*scale;
		textbox.x = startX;
		textbox.y = startY;
	}

}
