package rhys.game.objects.entity.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import rhys.game.main.Game;
import rhys.game.objects.entity.MapEntity;

public class GameText extends MapEntity {

	public String text;
	
	public static Font font;
	private static File ttf = new File(System.getProperty("user.dir")+"/src/rhys/game/resources/fonts/monogram.ttf");
	private static ArrayList<GameText> texts = new ArrayList<>();
	private Color color;
	
	//public static GameText exampleText = new GameText(Game.graphics.width/2-10, Game.graphics.height/2-12, "hello", Color.black);
	
	public GameText(int x, int y, String text, Color color) {
		this.x=x;
		this.y=y;
		this.text=text;
		this.color=color;
	}
	
	public void updateGUI(boolean isAlive) {
		if(!isAlive)
			despawn();
	}
	
	public void spawn() {
		texts.add(this);
		alive = true;
	}
	
	public void despawn() {
		texts.clear();
		alive = false;
	}

	public static void init() {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, ttf);
			font = font.deriveFont(24F);
		} catch (FontFormatException | IOException e) {
				e.printStackTrace();
		}
		System.out.println("Loaded font.");
	}

	public static void render(Graphics graphics) {
		
		Graphics2D g = (Graphics2D) graphics;
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(font);
		
		for(GameText text : texts) {
			g.setColor(text.color);
			g.drawString(text.text, (text.x*Game.scale), (text.y*Game.scale));
		}
		
	}
	
}
