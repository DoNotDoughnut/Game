package net.rhysholloway.game.scene.scenes;

import java.util.ArrayList;

import net.rhysholloway.game.scene.GameScene;
import net.rhysholloway.game.world.level.GameLevel;

public class GameLevelManager extends GameScene {

	private GameLevel currentLevel;
	
	public ArrayList<GameLevel> levels;
	
	public GameLevelManager() {
		super();
		levels = new ArrayList<GameLevel>();
	}

	@Override
	public void create() {
		
		currentLevel.create();
	}

	@Override
	public void dispose() {
		currentLevel.dispose();
	}	
	
	@Override
	public void update() {	
		currentLevel.update();
	}

	@Override
	public void render() {
		currentLevel.render();
	}

	public void addLevel(GameLevel level) {
		levels.add(level);
	}
	
	public void setLevel(int index) {
		if(currentLevel!=null)
			currentLevel.dispose();
		currentLevel = levels.get(index);
		currentLevel.create();
	}

}
