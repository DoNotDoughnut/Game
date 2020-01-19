package net.rhys.game.objects.level.levels;

import net.rhys.game.Game;
import net.rhys.game.objects.gui.GUIManager;
import net.rhys.game.objects.gui.GUIPanel;
import net.rhys.game.objects.gui.components.GUIButton;
import net.rhys.game.objects.gui.components.GUILabel;
import net.rhys.game.objects.gui.components.GUITitleBar;
import net.rhys.gameengine.EEngine;
import net.rhys.gameengine.level.ELevel;
import net.rhys.gameengine.rendering.ERenderer;
import net.rhys.gameengine.rendering.texture.ETexture;
import net.rhys.gameengine.rendering.texture.ETextureSheet;
import net.rhys.gameengine.tile.ETile;

public class MainMenuLevel extends ELevel {
	
	private GUIPanel mainMenu;
	
	private GUIButton playButton;//, selectButton;
	
	protected GUILabel titleLabel;
	
	protected GUIPanel levelSelection;
	
	private GUIManager guiManager;
	
	public MainMenuLevel(EEngine engine) {
		super("Main Menu", engine);
	}

	protected void loadLevel() {

	}
	
	public ETile getTileFromCoordinates(int x, int y) {
		return PlatformLevel.skyTile;
	}
	
	public void generateLevel(EEngine engine) {
		
		guiManager = new GUIManager();
		
		mainMenu = new GUIPanel(0, 0, new ETexture(new ETextureSheet(EEngine.resources+"levels/mainMenu/background.png", engine.width, engine.height)), "Main Menu");
		
		levelSelection = new LevelSelection(engine);
		
		
		int buttonWidth = 80, buttonHeight = 24;
		
		ETextureSheet buttons = new ETextureSheet(EEngine.resources+"levels/mainMenu/buttons.png", buttonWidth, buttonHeight);
		
		
		titleLabel = new GUILabel(engine.width/2-37, 50, engine.scale, EEngine.monogram.title, 0xFFFFFF, engine.title);
		playButton = new PlayButton(new ETexture(buttons, 0, 0, 2), (engine.width-buttonWidth)/2, 100+75, buttonWidth, buttonHeight, engine);
		//selectButton = new SelectButton(guiManager, new ETexture(buttons, 0, 1, 2), (engine.width-buttonWidth)/2, 75+buttonHeight*3/2, buttonWidth, buttonHeight, engine);
		
		//mainMenu.add(titleLabel);
		
		mainMenu.add(playButton);
		//mainMenu.add(selectButton);
		
		guiManager.add(mainMenu);
		guiManager.add(levelSelection);
		
		guiManager.enable(0);
		
	}
	
	public void update() {
		guiManager.update();
	}
	
	public void render(ERenderer graphics) {
		guiManager.render(graphics);
	}
	
	public void dispose() {
		guiManager.disable(0);
		guiManager.disable(1);
	}
}

class LevelSelection extends GUIPanel {

	private GUILabel text1, text2, text3;
	private GUITitleBar titleBar;

	public LevelSelection(EEngine engine) {
		super(engine.width/2-50, engine.height/2-50, new ETexture(new ETextureSheet(EEngine.resources+"spritesheets/testgui.png", 100, 100), 0, 0), "Select Level");
		
		int xa = this.startX;
		int ya = this.startY;
		
		titleBar = new GUITitleBar(this, EEngine.monogram.gui, 0x3F3F3F, engine);
		text1 = new GUILabel(xa+5, ya+18, engine.graphics.scale, EEngine.monogram.gui, 0x000000, "Type level id on");
		text2 = new GUILabel(xa+5, ya+24, engine.graphics.scale, EEngine.monogram.gui, 0x000000, "your keyboard now");
		text3 = new GUILabel(xa+5, ya+30, engine.graphics.scale, EEngine.monogram.gui, 0x000000, "unimplemented");

		add(titleBar);

		add(text1);
		add(text2);
		add(text3);
	}
	
}



class PlayButton extends GUIButton {
	
	public PlayButton(ETexture texture, int x, int y, int width, int height, EEngine engine) {
		super(engine, texture, x, y, width, height);
	}
	
	public void update() {
		if(clicked()) {
				engine.currentLevel.dispose();
				engine.currentLevel = Game.platformLevel;
		}
	}
	
}

/*class SelectButton extends GUIButton {
	
	private GUIManager guiManager;
	
	public SelectButton(GUIManager guiManager, ETexture texture, int x, int y, int width, int height, EEngine engine) {
		super(engine, texture, x, y, width, height);
		this.guiManager = guiManager;
	}
	
	public void update() {
		if(clicked() && !guiManager.isAlive(1))
			;
			//guiManager.enable(1);
	}
	
	public boolean clicked() {
		if(!holdingClick&&engine.mouseInput.clicking) {
			holdingClick = true;
			
			if(x + width >= engine.mouseInput.getX() && 
					   x         <= engine.mouseInput.getX() && 
					   y + width >= engine.mouseInput.getY() && 
					   y         <= engine.mouseInput.getY() ) {
					return true;
			}
			
		} else if(!engine.mouseInput.clicking)
			holdingClick = false;
		return false;
	}
	
} */