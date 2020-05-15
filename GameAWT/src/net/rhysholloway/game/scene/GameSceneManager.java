package net.rhysholloway.game.scene;

import java.util.ArrayList;

public class GameSceneManager {
	
	private GameScene currentScene;
	
	public ArrayList<GameScene> scenes;
	
	public GameSceneManager() {
		scenes = new ArrayList<>();
	}
	
	public void addScene(GameScene scene) {
		scenes.add(scene);
	}
	
	public void enable(int index) {
		if(!scenes.isEmpty()&&currentScene!=null)
			disable();
		currentScene = scenes.get(index);
		currentScene.create();
	}
	
	public void disable() {
		currentScene.dispose();
		currentScene = null;
	}
	
	public void update() {
		currentScene.update();
	}
	
	public void render() {
		currentScene.render();
	}	
	
}
