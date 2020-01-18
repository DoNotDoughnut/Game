package rhys.game;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwTerminate;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import rhys.game.graphics.Renderer;
import rhys.game.graphics.Window;
import rhys.game.input.KeyInput;
import rhys.game.input.MouseMotionInput;
import rhys.game.objects.entity.entities.PlayerBlue;
import rhys.game.objects.entity.entities.ScreenFocus;
import rhys.game.objects.gui.GUIManager;
import rhys.game.objects.gui.components.GUILabel;
import rhys.game.objects.level.GameLevel;
import rhys.game.objects.level.levels.PlatformLevel;
import rhys.game.update.Timer;

public class Game {

	public static final String name = "Game", version = "INDEV-2", author = "Rhys Holloway";
	
	public static final int width = 480, height = width / 16 * 9, scale = 2, targetUPS = 30;
	
	private static GameLevel platformLevel = new PlatformLevel();
	
	private boolean running = false;	
	private static GUILabel playerXL, playerYL;
	private MouseMotionInput mouseMotionInput;
	private KeyInput keyInput;
	private PlayerBlue player;
	private GUIManager guiManager;
	private GameLevel currentLevel;
	private ScreenFocus focus;
	
	//LWJGL stuff
	
	protected Window window;
	protected Timer timer;
	protected Renderer graphics;
	protected GLFWErrorCallback errorCallback;
		
	
	public void input() {
    }

    public void update() {
    	
    	guiManager.update();
    	player.update();
    	playerXL.text="Player X: "+player.hitbox.x;
		playerYL.text="Player Y: "+player.hitbox.y;
    }

    public void render() {
    	focus.render(graphics);
    	currentLevel.render(focus.x, focus.y, graphics);
    	player.render(graphics);
    	guiManager.render(graphics);
    }
	
	
	private void init() {
		
		  errorCallback = GLFWErrorCallback.createPrint();
	        glfwSetErrorCallback(errorCallback);

	        /* Initialize GLFW */
	        if (!glfwInit()) {
	            throw new IllegalStateException("Unable to initialize GLFW!");
	        }

	        /* Create GLFW window */
	        
	        window = new Window(width, height, name);

	        /* Initialize timer */
	        timer.init();

	        /* Initialize graphics */
	        graphics.init();

	        /* Initializing done, set running to true */
	        running = true;
	}
	
	private void gameLoop() {
		float delta;
		float accumulator = 0f;
		float updateInterval = 1f / targetUPS;
		
		while (running) {			
			delta = timer.getDelta();
			accumulator += delta; 
			
			input();
			
			while(accumulator >= updateInterval) { //Runs 60 times per second so game update speed wont be off
				update();
				accumulator -= updateInterval;
			}
			render();
			
		}
	}
	



	public Game() {
		timer = new Timer();
		keyInput = new KeyInput();
		mouseMotionInput = new MouseMotionInput();
		graphics = new Renderer(width, height);
		currentLevel = platformLevel;
		guiManager = new GUIManager();
		int pW = 8, pH = 15, pWO = 12, pHO = 10;
		player = new PlayerBlue(currentLevel, keyInput, mouseMotionInput, guiManager, currentLevel.spawnPoint.xp, (currentLevel.spawnPoint.yp)-(PlayerBlue.playerBlue_idle.height-pHO-pH), pW, pH, pWO, pHO); 
		focus = new ScreenFocus(player);
	}
	
	
	private void end() {
		graphics.dispose();
		window.destroy();
		glfwTerminate();
		errorCallback.free();
	}

	public void start() {
		init();
		gameLoop();
		end();
	}
	
	
	public static boolean isDefaultContext() {
        return GL.getCapabilities().OpenGL32;
    }

	public static void main(String[] args) {
		new Game().start(); //Create a new instance of the game
	}
	
}
