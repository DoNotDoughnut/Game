package net.rhys.game.objects.level.levels;

import net.rhys.game.objects.entity.entities.PlayerBlue;
import net.rhys.game.objects.entity.entities.ScreenFocus;
import net.rhys.game.objects.gui.GUIManager;
import net.rhys.game.objects.gui.guis.TestGUI;
import net.rhys.game.objects.tiles.BasicSolidTile;
import net.rhys.gameengine.EEngine;
import net.rhys.gameengine.level.ELevel;
import net.rhys.gameengine.rendering.ERenderer;
import net.rhys.gameengine.rendering.texture.ETexture;
import net.rhys.gameengine.rendering.texture.ETextureSheet;
import net.rhys.gameengine.tile.ETile;

public class PlatformLevel extends ELevel {

	public ScreenFocus focus;
	public PlayerBlue player;
	public GUIManager guiManager;

	public void update() {
		guiManager.update();
		player.update();
		ETexture.update();
	}
	
	protected void loadLevel() {
		super.loadLevel();
	}

	public void generateLevel(EEngine engine) {
		
		guiManager = new GUIManager();

		int pW = 8, pH = 15, pWO = 12, pHO = 10;

		player = new PlayerBlue(this, engine.keyInput, engine.mouseInput, guiManager, ETile.size*3, ETile.size*12, pW, pH, pWO, pHO);

		focus = new ScreenFocus(player);

		guiManager.add(new TestGUI(engine, player));
	}
	
	public void render(ERenderer graphics) {
		focus.render(graphics);
		super.renderTiles(focus.x, focus.y, graphics);
		player.render(graphics);
		guiManager.render(graphics);
	}

	public PlatformLevel(EEngine engine) {
		super("Level 1", "platformLevel.png", engine);
	}

	public ETile getTileFromColor(int color) {
		if (color == 0xffB8D9E2)
			return skyTile;
		// if(color==0xFFFF0000) return Tile.spawnTile;
		// if(color==0xff202020) return Tile.consoleTile;
		if (color == 0xff00ff01)
			return grassTopLeftTile;
		if (color == 0xff00ff02)
			return grassTopMidTile;
		if (color == 0xff00ff03)
			return grassTopRightTile;
		if (color == 0xff00ff04)
			return grassMidLeftTile;
		if (color == 0xff00ff05)
			return grassMiddleTile;
		if (color == 0xff00ff06)
			return grassMidRightTile;
		if (color == 0xff00ff07)
			return grassBotLeftTile;
		if (color == 0xff00ff08)
			return grassBotMidTile;
		if (color == 0xff00ff09)
			return grassBotRightTile;
		if (color == 0xff00ff0A)
			return grass1x1Tile;
		if (color == 0xff00ff0B)
			return grassTop1xVTile;
		if (color == 0xff00ff0C)
			return grassMid1xVTile;
		if (color == 0xff00ff0D)
			return grassBot1xVTile;
		if (color == 0xff00ff0E)
			return grassLeft1xHTile;
		if (color == 0xff00ff0F)
			return grassMid1xHTile;
		if (color == 0xff00ff10)
			return grassRight1xHTile;
		if (color == 0xff00ff11)
			return grassInterTopLeftTile;
		if (color == 0xff00ff12)
			return grassInterTopMidTile;
		if (color == 0xff00ff13)
			return grassInterTopRightTile;
		if (color == 0xff00ff14)
			return grassInterBotLeftTile;
		if (color == 0xff00ff15)
			return grassInterBotMidTile;
		if (color == 0xff00ff16)
			return grassInterBotRightTile;
		return ETile.voidTile;
	}

	public static ETexture skyTexture = new ETexture(0xB8D9E2);
	public static ETexture grassTopTextures = new Level1Texture(0, 0, 3);
	public static ETexture grassMidTextures = new Level1Texture(0, 1, 3);
	public static ETexture grassBotTextures = new Level1Texture(0, 2, 3);
	public static ETexture grass1xVTextures = new Level1Texture(3, 0, 3);
	public static ETexture grass1xHTextures = new Level1Texture(3, 1, 3);
	public static ETexture grassInterTopTexture = new Level1Texture(3, 2, 3);
	public static ETexture grassInterBotTexture = new Level1Texture(6, 0, 3);
	public static ETexture grass1x1Texture = new Level1Texture(9, 0, 1);

	public static ETile skyTile = new ETile(skyTexture);

	public static ETile grassTopLeftTile = new BasicSolidTile(grassTopTextures);
	public static ETile grassTopMidTile = new BasicSolidTile(grassTopTextures.getVariant(2));
	public static ETile grassTopRightTile = new BasicSolidTile(grassTopTextures.getVariant(3));

	public static ETile grassMidLeftTile = new BasicSolidTile(grassMidTextures);
	public static ETile grassMiddleTile = new BasicSolidTile(grassMidTextures.getVariant(2));
	public static ETile grassMidRightTile = new BasicSolidTile(grassMidTextures.getVariant(3));

	public static ETile grassBotLeftTile = new BasicSolidTile(grassBotTextures);
	public static ETile grassBotMidTile = new BasicSolidTile(grassBotTextures.getVariant(2));
	public static ETile grassBotRightTile = new BasicSolidTile(grassBotTextures.getVariant(3));

	public static ETile grassTop1xVTile = new BasicSolidTile(grass1xVTextures);
	public static ETile grassMid1xVTile = new BasicSolidTile(grass1xVTextures.getVariant(2));
	public static ETile grassBot1xVTile = new BasicSolidTile(grass1xVTextures.getVariant(3));

	public static ETile grassLeft1xHTile = new BasicSolidTile(grass1xHTextures);
	public static ETile grassMid1xHTile = new BasicSolidTile(grass1xHTextures.getVariant(2));
	public static ETile grassRight1xHTile = new BasicSolidTile(grass1xHTextures.getVariant(3));

	public static ETile grassInterTopLeftTile = new BasicSolidTile(grassInterTopTexture);
	public static ETile grassInterTopMidTile = new BasicSolidTile(grassInterTopTexture.getVariant(2));
	public static ETile grassInterTopRightTile = new BasicSolidTile(grassInterTopTexture.getVariant(3));

	public static ETile grassInterBotLeftTile = new BasicSolidTile(grassInterBotTexture);
	public static ETile grassInterBotMidTile = new BasicSolidTile(grassInterBotTexture.getVariant(2));
	public static ETile grassInterBotRightTile = new BasicSolidTile(grassInterBotTexture.getVariant(3));

	public static ETile grass1x1Tile = new BasicSolidTile(grass1x1Texture);

}

class Level1Texture extends ETexture {

	private static ETextureSheet level1Textures = new ETextureSheet(EEngine.resources+"spritesheets/level1Sheet.png", ETexture.defaultSize, ETexture.defaultSize);

	public Level1Texture(int x, int y, int variants) {
		super(level1Textures, x, y, variants);
	}

}
