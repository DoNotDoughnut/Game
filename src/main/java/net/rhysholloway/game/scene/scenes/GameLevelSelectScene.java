package net.rhysholloway.game.scene.scenes;

import net.rhysholloway.game.Game;
import net.rhysholloway.game.graphics.sprite.Sprite;
import net.rhysholloway.game.gui.GUIManager;
import net.rhysholloway.game.gui.GUIPanel;
import net.rhysholloway.game.gui.components.GUIButton;
import net.rhysholloway.game.scene.GameScene;

public class GameLevelSelectScene extends GameScene {

	@SuppressWarnings("unused")
	private int levels;
	
	private GUIManager guiManager;
	private GUIPanel levelPanel;
	
	private GUIButton testLevelButton, platformLevelButton;
	
	@Override
	public void create() {
		levels = Game.glm.levels.size();
		guiManager = new GUIManager();
		levelPanel = new GUIPanel(0, 0, Game.canvasWidth, Game.canvasHeight, new Sprite(0x550000, Game.canvasWidth, Game.canvasHeight));
		
		platformLevelButton = new GUIButton(new Sprite(0xFF00FF, 40, 40), levelPanel.width - 50, levelPanel.height - 100) {
			public void onClick() {
				System.out.println("Starting platform level.");
				Game.glm.setLevel(0);
				Game.gsm.enable(2);
			}
		};
		
		testLevelButton = new GUIButton(new Sprite(0xFF0000, 40, 40), levelPanel.width - 50, levelPanel.height - 50) {
			public void onClick() {
				System.out.println("Starting json level.");
				Game.glm.setLevel(1);
				Game.gsm.enable(2);
			}
		};
		
		levelPanel.add(testLevelButton);
		levelPanel.add(platformLevelButton);
		
		guiManager.add(levelPanel);
		guiManager.enable(0);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void update() {
		guiManager.update();
	}

	@Override
	public void render() {
		guiManager.render();
	}

}
