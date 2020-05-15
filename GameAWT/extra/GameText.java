package rhys.game.objects.entity.entities;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

@Deprecated
public class GameText {

	public int x, y;
	public String text;
	public Color color;
	public Font font;

	private static ArrayList<GameText> texts = new ArrayList<>();

	public GameText(int x, int y, Font font, Color color, String text) {
		this.x = x;
		this.y = y;
		this.font = font;
		this.color = color;
		this.text = text;
	}

	public void show() {
		texts.add(this);
	}

	public static void render(Graphics graphics) {

		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (GameText text : texts) {
			if (text != null) {

				g.setFont(text.font);
				g.setColor(text.color);
				g.drawString(text.text, text.x, text.y);

			}
		}

		texts.clear();
	}

}
