package net.rhys.game;

import net.rhys.game.objects.level.levels.MainMenuLevel;
import net.rhys.game.objects.level.levels.PlatformLevel;
import net.rhys.gameengine.EEngine;
import net.rhys.gameengine.level.ELevel;
/**
 * 
 * @author Rhys Holloway
 * @version INDEV-3
 * @since 2020-01-06
 *
 */
public class Game extends EEngine {
	
	
	public static ELevel platformLevel;
	public static ELevel mainMenuLevel;
	
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
		super("Game", 480, 480/16*9, 3, "/res/");
		
		platformLevel = new PlatformLevel(this);
		mainMenuLevel = new MainMenuLevel(this);
		
		currentLevel =  new MainMenuLevel(this);

	}
	
	public static void main(String[] args) {
		Game engine = new Game();
		engine.start();
	}
}