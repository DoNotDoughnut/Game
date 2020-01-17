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
import rhys.game.objects.gui.components.GUITitleBar;
import rhys.game.objects.level.GameLevel;
import rhys.game.objects.level.levels.PlatformLevel;
import rhys.game.objects.sprite.Sprite;
import rhys.game.objects.sprite.SpriteSheet;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final String name = "Game", 
						version = "INDEV-2-FINAL", 
						author = "Rhys Holloway";
	
	private static final int width = 480, 
							height = width/16*9, 
							scale = 2;
	
	public static final Dimension resolution = new Dimension(width*scale, height*scale);
	
	private static GUILabel playerXL,
							playerYL;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //render image
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); //image data
	
	public JFrame frame;
	public GameRenderer graphics;
	public GameLevel currentLevel;
	public ScreenFocus focus;
	public PlayerBlue player;
	public GameKeyListener keyInput;
	public GameMouseListener mouseInput;
	public GUIManager guiManager;
	
	public int ups, fps;
	
	public void update() {
		
		guiManager.update();
		keyInput.update();
		player.update();
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
		
		renderObjects();
		
		
		System.arraycopy(graphics.pixels, 0, pixels, 0, pixels.length);
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		GameText.render(g);
		
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
		
		graphics = new GameRenderer(width, height, scale); // Establishes game renderer
		
		currentLevel = PlatformLevel.level; //Set the level
		
		GameText.init(graphics); //Initialize the text renderer so it is ready to render text
		
		//Create and add input listeners
		
		keyInput = new GameKeyListener();
				
		addKeyListener(keyInput); 
				
		mouseInput = new GameMouseListener(graphics);
				
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
				
		
		guiManager = new GUIManager(); //Create in game GUI handler
		
		//Create the GUI panel
		
		GUIPanel panel = new GUIPanel(0,0,100,100, new Sprite(new SpriteSheet("/rhys/game/resources/spritesheets/testgui.png", 1, 1, 100), 0, 0, 100, 1), "Test Panel :)"); 
		
		//Initialize and add components to the panel.
		
		panel.add(new GUITitleBar(panel, mouseInput, graphics, 0x3F3F3F));
		
		panel.add(playerXL = new GUILabel(5*graphics.scale, 40*graphics.scale, GameText.guiFont, Color.black, "Player X: ")); 
		panel.add(playerYL = new GUILabel(5*graphics.scale, 56*graphics.scale, GameText.guiFont, Color.black, "Player Y: "));
		
		panel.add(new GUILabel(5*graphics.scale, 18*graphics.scale, GameText.guiFont, Color.black, "Hello!! I am a")); 
		panel.add(new GUILabel(5*graphics.scale, 28*graphics.scale, GameText.dialogueFont, Color.black, "test GUI label."));
		
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
