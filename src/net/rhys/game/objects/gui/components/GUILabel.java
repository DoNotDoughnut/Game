package net.rhys.game.objects.gui.components;

import java.awt.Font;

import net.rhys.game.objects.gui.GUIComponent;
import net.rhys.gameengine.rendering.ERenderer;
import net.rhys.gameengine.rendering.text.EText;

public class GUILabel extends GUIComponent {

	private int scale;
	public String text;
	public EText textbox;
	
	public GUILabel(int x, int y, int scale, Font font, int color, String text) {
		super(x*scale, y*scale);
		this.scale = scale;
		this.text = text;
		textbox = new EText(x*scale, y*scale, font, color, text);
	}
	
	public void update() {
		if(textbox.text!=text)
			textbox.text = text;
	}
	
	public void render(ERenderer graphics) {
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
		x = startX;
		y = startY;
		textbox.x = startX;
		textbox.y = startY;
	}

}
