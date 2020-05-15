package rhys.game.objects.gui.components;

import java.awt.Color;
import java.awt.Font;

import rhys.game.main.GameRenderer;
import rhys.game.objects.entity.entities.GameText;
import rhys.game.objects.gui.GUIComponent;

@Deprecated
public class GUILabel extends GUIComponent {

	public String text;
	public GameText textbox;

	public GUILabel(int x, int y, Font font, Color color, String text) {
		super(x, y);
		this.text = text;
		textbox = new GameText(x, y, font, color, text);
	}

	public void update() {
		if (textbox.text != text)
			textbox.text = text;
	}

	public void render() {
		if (alive)
			textbox.show();
	}

	public void spawn() {
		alive = true;
	}

	public void despawn() {
		alive = false;
	}

	public void move(int newX, int newY) {
		x += newX * GameRenderer.scale;
		y += newY * GameRenderer.scale;
		textbox.x = x;
		textbox.y = y;
	}

	public void resetPos() {
		x = startX * GameRenderer.scale;
		y = startY * GameRenderer.scale;
		textbox.x = startX;
		textbox.y = startY;
	}

}
