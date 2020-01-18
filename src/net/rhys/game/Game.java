package net.rhys.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.swing.JFrame;

import net.rhys.game.objects.entity.entities.PlayerBlue;
import net.rhys.game.objects.entity.entities.ScreenFocus;
import net.rhys.game.objects.gui.GUIManager;
import net.rhys.game.objects.gui.GUIPanel;
import net.rhys.game.objects.gui.components.GUILabel;
import net.rhys.game.objects.gui.components.GUITitleBar;
import net.rhys.game.objects.level.GameLevel;
import net.rhys.game.objects.level.levels.PlatformLevel;
import net.rhys.gameengine.input.EKeyInput;
import net.rhys.gameengine.input.EMouseInput;
import net.rhys.gameengine.render.ERenderer;
import net.rhys.gameengine.render.EText;
import net.rhys.gameengine.texture.ETexture;
import net.rhys.gameengine.texture.ETextureSheet;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final String name = "Game", 
						version = "INDEV-2-FINAL", 
						author = "Rhys Holloway";
	
	private static final int width = 480, 
							height = width/16*9, 
							scale = 2;
	
	public static final Dimension resolution = new Dimension(width*scale, height*scale);

	public static final String sheets = "/net/rhys/game/resources/spritesheets/";
	
	private static GUILabel playerXL, playerYL;
	
	public static Font dialogueFont, guiFont;

	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //render image
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); //image data
	
	public JFrame frame;
	public ERenderer graphics;
	public GameLevel currentLevel;
	public ScreenFocus focus;
	public PlayerBlue player;
	public EKeyInput keyInput;
	public EMouseInput mouseInput;
	public GUIManager guiManager;
	
	public int ups, fps;
	
	public void update() {
		
		guiManager.update();
		keyInput.update();
		player.update();
		ETexture.update();
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
		
		renderObjects();
		
		
		System.arraycopy(graphics.pixels, 0, pixels, 0, pixels.length);
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		EText.render(g);
		
		g.dispose();
		bs.show();
	}
	
	private void renderObjects() {
		focus.render(graphics);
		currentLevel.render(focus.x, focus.y, graphics);
		player.render(graphics);
		guiManager.render(graphics);
	}
	
	public Game() {
		
		//LWJGL stuff
		
		//errorCallback = GLFWErrorCallback.createPrint(System.err);
	
		
		
		//Sets window and canvas size to the resolution specified
		
		setPreferredSize(resolution);
		
		graphics = new ERenderer(width, height, scale); // Establishes game renderer
		
		currentLevel = PlatformLevel.level; //Set the level
		
		try {
			Font basicFont = Font.createFont(Font.TRUETYPE_FONT, Game.class.getClassLoader().getResourceAsStream("net/rhys/gameengine/render/text/monogram.ttf"));
			guiFont = basicFont.deriveFont((float) (12*scale));
			dialogueFont = basicFont.deriveFont((float) (16*scale));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		
		
		//Initialize the text renderer so it is ready to render text
		
		//Create and add input listeners
		
		keyInput = new EKeyInput();
				
		addKeyListener(keyInput); 
				
		mouseInput = new EMouseInput(graphics);
				
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
				
		
		guiManager = new GUIManager(); //Create in game GUI handler
		
		//Create the GUI panel
		
		GUIPanel panel = new GUIPanel(0,0,100,100, new ETexture(new ETextureSheet("/net/rhys/game/resources/spritesheets/testgui.png", 1, 1, 100), 0, 0, 100, 1), "Test Panel :)"); 
		
		//Initialize and add components to the panel.
		
		panel.add(new GUITitleBar(panel, guiFont, 0x3F3F3F, mouseInput, graphics));
		
		panel.add(playerXL = new GUILabel(5*graphics.scale, 40*graphics.scale, guiFont, 0x000000, "Player X: ", graphics)); 
		panel.add(playerYL = new GUILabel(5*graphics.scale, 56*graphics.scale, guiFont, 0x000000, "Player Y: ", graphics));
		
		panel.add(new GUILabel(5*graphics.scale, 18*graphics.scale, guiFont, 0x000000, "Hello!! I am a", graphics)); 
		panel.add(new GUILabel(5*graphics.scale, 28*graphics.scale, dialogueFont, 0x000000, "test GUI label.", graphics));
		
		//Add the panel to the GUI manager
		
		guiManager.add(panel); 
		
		//Creates the player (and it has to be near last to initialized because it needs to wait until its parameters are initialized
		
		int pW = 8, pH = 15, pWO = 12, pHO = 10;
		
		player = new PlayerBlue(currentLevel, keyInput, mouseInput, guiManager, currentLevel.spawnPoint.xp, (currentLevel.spawnPoint.yp)-(PlayerBlue.playerBlue_idle.height-pHO-pH), pW, pH, pWO, pHO); 
		
		focus = new ScreenFocus(player); //Set screen focus on to the player
		
		frame = new JFrame(); //Create a new window and give it properties
		frame.setResizable(false);
		frame.setTitle(name);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double updateEvery = 1000000000.0 / 60.0; // 1 billion / 60 (more accurate than milliseconds)
		double delta = 0;
		int ups = 0, fps = 0;
		
		while (running) {
			long now = System.nanoTime();

			delta += (now - lastTime) / updateEvery; //Sets delta is checked to see if the time in between last 
											//time this was called and now was more than 1/60th of a second

			lastTime = now;

			while(delta>=1) { //Runs 60 times per second so game update speed wont be off
				update();
				ups++;
				delta--;
			}
			render(); //Renders as many times per second as possible
			fps++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				this.ups=ups;
				this.fps=fps;
				ups = 0;
				fps = 0;
			}
			
		}
	}
	
	public synchronized void start() { // See below
		
		running = true;
		thread = new Thread(this, name);
		
		thread.start(); // Start the application thread. I think this calls run();
	}
	
	public synchronized void stop() { // When exit call is given, stop run(); and close the thread.
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
			
		Game game = new Game(); //Create a new instance of the game
				
		game.start(); // call start();
	}
}
