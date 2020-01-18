package net.rhys.game.objects.gui.components;

import java.awt.Font;

import net.rhys.game.objects.gui.GUIComponent;
import net.rhys.gameengine.render.ERenderer;
import net.rhys.gameengine.render.EText;

public class GUILabel extends GUIComponent {

	public String text;
	public EText textbox;
	private ERenderer graphics;
	
	public GUILabel(int x, int y, Font font, int color, String text, ERenderer graphics) {
		super(x,y);
		this.text = text;
		this.graphics = graphics;
		textbox = new EText(x, y, font, color, text);
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
		x += newX*graphics.scale;
		y += newY*graphics.scale;
		textbox.x = x;
		textbox.y = y;
	}
	
	public void resetPos() {
		x = startX*graphics.scale;
		y = startY*graphics.scale;
		textbox.x = startX;
		textbox.y = startY;
	}

}
