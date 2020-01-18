package net.rhys.game.objects.level.levels;

import net.rhys.game.objects.gui.GUIManager;
import net.rhys.game.objects.gui.GUIPanel;
import net.rhys.game.objects.gui.components.GUIButton;
import net.rhys.game.objects.gui.components.GUILabel;
import net.rhys.game.objects.level.GameLevel;
import net.rhys.game.objects.tile.Tile;
import net.rhys.game.objects.tile.TileCoordinate;
import net.rhys.gameengine.EEngine;
import net.rhys.gameengine.input.EMouseInput;
import net.rhys.gameengine.render.EFonts;
import net.rhys.gameengine.render.ERenderer;
import net.rhys.gameengine.texture.ETexture;

public class MainMenuLevel extends GameLevel {

	private ETexture background, playTexture, selectTexture;
	
	private GUIPanel mainMenu;
	
	private GUIButton playButton, selectButton;
	
	private GUILabel titleLabel;
	
	private GUIManager guiManager;
	
	public MainMenuLevel(EEngine engine) {
		super(new TileCoordinate(8,8), engine);
	}

	protected void loadLevel() {

	}
	
	public Tile getTileFromCoordinates(int x, int y) {
		return PlatformLevel.skyTile;
	}
	
	public void generateLevel(EEngine engine) {
		
		guiManager = new GUIManager();
		
		background = new ETexture(0xFF00FF, engine.width, engine.height);
		
		mainMenu = new GUIPanel(0, 0, engine.width, engine.height, background, "Main Menu");
		
		titleLabel = new GUILabel(engine.width/3, 20, 4, EFonts.dialogueFont, 0xFFFF, engine.title);
		
		int buttonWidth = 80, buttonHeight = 24;
		
		playTexture = new ETexture(0xFFFFFF, buttonWidth, buttonHeight);
		
		selectTexture = new ETexture(0xFFFFFF, buttonWidth, buttonHeight);
		
		playButton = new PlayButton(engine.mouseInput, engine.graphics, playTexture, (engine.width-buttonWidth)/2, 100, buttonWidth, buttonHeight);
		
		selectButton = new SelectButton(engine.mouseInput, engine.graphics, selectTexture, (engine.width-buttonWidth)/2, 100+2*buttonHeight, buttonWidth, buttonHeight);
		
		mainMenu.add(titleLabel);
		mainMenu.add(playButton);
		mainMenu.add(selectButton);
		
		guiManager.add(mainMenu);
		
	}
	
	public void update() {
		guiManager.update();
	}
	
	public void render(ERenderer graphics) {
		guiManager.render(graphics);
	}
}

class PlayButton extends GUIButton {

	public PlayButton(EMouseInput mouseInput, ERenderer graphics, ETexture texture, int x, int y, int width, int height) {
		super(mouseInput, graphics, texture, x, y, width, height);
	}
	
}

class SelectButton extends GUIButton {

	public SelectButton(EMouseInput mouseInput, ERenderer graphics, ETexture texture, int x, int y, int width, int height) {
		super(mouseInput, graphics, texture, x, y, width, height);
	}
	
}