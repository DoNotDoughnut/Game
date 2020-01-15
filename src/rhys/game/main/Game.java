package rhys.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import rhys.game.input.GameKeyListener;
import rhys.game.input.GameMouseListener;
import rhys.game.objects.entity.entities.GameText;
import rhys.game.objects.entity.entities.PlayerBlue;
import rhys.game.objects.entity.entities.ScreenFocus;
import rhys.game.objects.gui.GUIManager;
import rhys.game.objects.gui.GUIPanel;
import rhys.game.objects.gui.components.GUILabel;
import rhys.game.objects.level.GameLevel;
import rhys.game.objects.level.levels.PlatformLevel;
import rhys.game.objects.sprite.Sprite;
import rhys.game.objects.tile.Tile;
import rhys.game.objects.tile.TileCoordinate;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final String name = "Game", version = "1", author = "Rhys Holloway";
	
	public static final int width = 480, height = width/16*9, scale = 2;
	
	public static final Dimension resolution = new Dimension(width*scale, height*scale);
	
	private boolean running = false;
	private Thread thread;
	
	public static GameRenderer graphics = new GameRenderer(width, height);
	public static GameKeyListener keyInput = new GameKeyListener();
	public static GameMouseListener mouseInput = new GameMouseListener();
	
	public static GUIManager guiManager;
	private static GUILabel playerXL = new GUILabel(5, 32, "Player X: ", Color.black), 
							playerYL = new GUILabel(5, 48, "Player Y: ", Color.black);
	
	public static ScreenFocus focus;
	public static PlayerBlue player;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	//public static GameLevel fountainLevel = new LevelT1("/rhys/game/resources/levels/fountainLevel.png", new TileCoordinate(12,33));
	public static GameLevel platformLevel = new PlatformLevel("/rhys/game/resources/levels/platformLevel.png", new TileCoordinate(3,11));
	
	public static GameLevel currentLevel = platformLevel;
	
	public void update() {
		
		guiManager.update();
		keyInput.update();
		player.update();
		Tile.update();
		Sprite.update();
		playerXL.text="Player X: "+player.hitbox.x;
		playerYL.text="Player Y: "+player.hitbox.y;
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		graphics.clear();
		
		focus.render(graphics);
		currentLevel.render(focus.x, focus.y, graphics);
		player.render(graphics);
		guiManager.render(graphics);
		
		
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = graphics.pixels[i];
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		GameText.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public Game() {
		setPreferredSize(resolution);
		currentLevel = platformLevel;
		player = new PlayerBlue(currentLevel, currentLevel.spawnPoint.x, currentLevel.spawnPoint.y);
		focus = new ScreenFocus(player);
		guiManager = new GUIManager();
		GUIPanel panel = new GUIPanel(0,0,100,100, new Sprite(0xFF0000, 100));
		panel.add(new GUILabel(5, 10, "Hello!! I am a", Color.black));
		panel.add(new GUILabel(5, 20, "test GUI label.", Color.black));
		
		panel.add(playerXL);
		panel.add(playerYL);
		
		guiManager.add(panel);
		addKeyListener(keyInput);
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			while(delta>=1) {
				update();
				delta--;
			}
			render();
		}
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, name);
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Game main = new Game();
		
		JFrame gf = new JFrame();
		gf.setResizable(false);
		gf.setTitle(name);
		gf.add(main);
		gf.pack();
		gf.setLocationRelativeTo(null);
		gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gf.setVisible(true);
		
		GameText.init();
				
		main.start();
	}
}
