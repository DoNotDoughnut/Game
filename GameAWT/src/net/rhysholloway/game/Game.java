package net.rhysholloway.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import net.rhysholloway.game.graphics.GameRenderer;
import net.rhysholloway.game.input.GameKeyListener;
import net.rhysholloway.game.input.GameMouseListener;
import net.rhysholloway.game.scene.GameSceneManager;
import net.rhysholloway.game.scene.scenes.GameLevelManager;
import net.rhysholloway.game.scene.scenes.GameLevelSelectScene;
import net.rhysholloway.game.scene.scenes.MainMenuScene;
import net.rhysholloway.game.world.level.levels.JSONLevelTest;
import net.rhysholloway.game.world.level.levels.PlatformLevel;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final String name = "Game", version = "INDEV 0.4.2", author = "Rhys Holloway";

	public static int canvasWidth = 480, canvasHeight = canvasWidth / 16 * 9, scale = 3;

	public static final Dimension resolution = new Dimension(canvasWidth * scale, canvasHeight * scale);

	private BufferedImage image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB); // render image
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // image data

	public static Game game;
	
	public JFrame frame;

	public static GameSceneManager gsm;
	public static GameLevelManager glm;

	public int ups, fps;

	public void update() {
		gsm.update();
	}
	
	public void resize() {
		this.setSize(new Dimension(canvasWidth, canvasHeight));
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		GameRenderer.clear();

		gsm.render();

		System.arraycopy(GameRenderer.pixels, 0, pixels, 0, pixels.length);

		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		//	GameText.render(g);

		g.dispose();
		bs.show();
	}

	public Game() {

		// Sets window and canvas size to the resolution specified

		setPreferredSize(resolution);
		
		// Creates the scene manager

		gsm = new GameSceneManager();
		
		// Adds main menu scene and level manager scene
		
		gsm.addScene(new MainMenuScene());
		
		
		glm = new GameLevelManager();
		
		glm.addLevel(new PlatformLevel());
		glm.addLevel(new JSONLevelTest());
		
		GameLevelSelectScene glss = new GameLevelSelectScene();
		
		gsm.addScene(glss);
		
		gsm.addScene(glm);
		
		// Enables main menu scene
		
		gsm.enable(0);

		// Create and add input listeners

		GameKeyListener keyInput = new GameKeyListener();

		addKeyListener(keyInput);

		GameMouseListener mouseInput = new GameMouseListener();

		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		
		// Create a new window and give it properties
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle(name);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public void run() {
		
		// Request to bring window to front and focus on it

		requestFocus();
		
		// Game loop variables
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double updateEvery = 1000000000 / 60.0; // 1 billion / 60 (more accurate than milliseconds)
		double delta = 0;

		int ups = 0, fps = 0;

		while (running) {

			long now = System.nanoTime();

			delta += (now - lastTime); // Sets delta is checked to see if the time in between last
										// time this was called and now was more than 1/60th of a second

			lastTime = now;

			while (delta >= updateEvery) { // Runs 60 times per second so game update speed wont be off
				update();
				ups++;
				delta-=updateEvery;
			}

			// Renders as many times per second as possible
			
			render(); 
			fps++;
			
			// UPS and FPS counter

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				this.ups = ups;
				this.fps = fps;
				ups = 0;
				fps = 0;
			}

			// Sleep to save cpu
			
			try {
				long sleepTime = (long) ((lastTime - System.nanoTime() + updateEvery) / 1000000);
				//System.out.println("Sleeping for " + sleepTime + " ms.");
				if (sleepTime > 0) {
					Thread.sleep(sleepTime, (int) (lastTime - System.nanoTime() + updateEvery) / 1000000000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	private boolean running = false;
	private Thread thread;

	public synchronized void start() {

		running = true;
		thread = new Thread(this, name);

		thread.start(); // Start the application thread. Calls run();
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

		game = new Game(); // Create a new instance of the game

		game.start(); // call start();
	}
	
	// Force exit

	public static void close() {
		System.exit(0);
	}
}
