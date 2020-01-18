package net.rhys.game;

import net.rhys.game.objects.level.GameLevel;
import net.rhys.game.objects.level.levels.PlatformLevel;
import net.rhys.gameengine.EEngine;
/**
 * 
 * @author Rhys Holloway
 * @version INDEV-3
 * @since 2020-01-06
 *
 */
public class Game extends EEngine {
	
	public GameLevel currentLevel;
	
	public void input() {
		keyInput.update();
	}
	
	public void update() {
		currentLevel.update();
	}

	public void render() {
		currentLevel.render(graphics);
	}
	
	public Game() {
		super("Game", 480, 480/16*9, 2);
		
		currentLevel =  new PlatformLevel(this);

	}
	
	public static void main(String[] args) {
		Game engine = new Game();
		engine.start();
	}
}