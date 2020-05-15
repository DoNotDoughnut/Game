package net.rhysholloway.game.world.level.levels;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import net.rhysholloway.game.graphics.sprite.Sprite;
import net.rhysholloway.game.graphics.sprite.SpriteSheet;
import net.rhysholloway.game.gui.GUIManager;
import net.rhysholloway.game.gui.guis.GameMenu;
import net.rhysholloway.game.input.GameKeyListener;
import net.rhysholloway.game.world.entity.entities.MobGreen;
import net.rhysholloway.game.world.entity.entities.PlayerBlue;
import net.rhysholloway.game.world.entity.entities.ScreenFocus;
import net.rhysholloway.game.world.level.GameImageLevel;
import net.rhysholloway.game.world.tile.Tile;
import net.rhysholloway.game.world.tile.TileCoordinate;

public class PlatformLevel extends GameImageLevel {

	public GameMenu menu;
	public PlayerBlue player;
	private GUIManager guiManager;

	private MobGreen mob;
	
	public void update() {

		if (GameKeyListener.once(KeyEvent.VK_ESCAPE)) {
			menu.toggle();
			if (menu.currentPanel != -1)
				menu.panels[menu.currentPanel].toggle();
		}

		guiManager.update();
		player.update();
		mob.update();
	}

	public void render() {
		focus.update();
		super.drawLevel();
		mob.render();
		player.render();
		guiManager.render();
	}

	@Override
	public void create() {

		guiManager = new GUIManager(); // Create in game GUI handler

		menu = new GameMenu();

		guiManager.add(menu);
		
		for (int i = 0; i < menu.panels.length; i++)
			guiManager.add(menu.panels[i]);

		// Creates the player (and it has to be near last to initialized because it
		// needs to wait until its parameters are initialized

		player = new PlayerBlue(this, spawnpoint.xpixel, spawnpoint.ypixel);

		mob = new MobGreen(this, spawnpoint.xpixel, spawnpoint.ypixel - 50, true);
		
		focus = new ScreenFocus(player); // Set screen focus on to the player
	}

	@Override
	public void dispose() {

	}

	public PlatformLevel() {
		super("/assets/levels/platformLevel/map.png", colorMap());
		spawnpoint = new TileCoordinate(3, 12);
	}
	
	public static final Tile skyTile = new Tile(new Sprite(0xB8D9E2), false);

	public static final Tile grassTopLeftTile = new PlatformLevelTile(0, 0);
	public static final Tile grassTopMidTile = new PlatformLevelTile(1, 0);
	public static final Tile grassTopRightTile = new PlatformLevelTile(2, 0);

	public static final Tile grassMidLeftTile = new PlatformLevelTile(0, 1);
	public static final Tile grassMiddleTile = new PlatformLevelTile(1, 1);
	public static final Tile grassMidRightTile = new PlatformLevelTile(2, 1);

	public static final Tile grassBotLeftTile = new PlatformLevelTile(0, 2);
	public static final Tile grassBotMidTile = new PlatformLevelTile(1, 2);
	public static final Tile grassBotRightTile = new PlatformLevelTile(2, 2);

	public static final Tile grassTop1xVTile = new PlatformLevelTile(3, 0);
	public static final Tile grassMid1xVTile = new PlatformLevelTile(4, 0);
	public static final Tile grassBot1xVTile = new PlatformLevelTile(5, 0);

	public static final Tile grassLeft1xHTile = new PlatformLevelTile(3, 1);
	public static final Tile grassMid1xHTile = new PlatformLevelTile(4, 1);
	public static final Tile grassRight1xHTile = new PlatformLevelTile(5, 1);

	public static final Tile grassInterTopLeftTile = new PlatformLevelTile(3, 2);
	public static final Tile grassInterTopMidTile = new PlatformLevelTile(4, 2);
	public static final Tile grassInterTopRightTile = new PlatformLevelTile(5, 2);

	public static final Tile grassInterBotLeftTile = new PlatformLevelTile(6, 0);
	public static final Tile grassInterBotMidTile = new PlatformLevelTile(7, 0);
	public static final Tile grassInterBotRightTile = new PlatformLevelTile(8, 0);

	public static final Tile grass1x1Tile = new PlatformLevelTile(9, 0);
	
	private static HashMap<Integer, Tile> colorMap() {
		HashMap<Integer, Tile> colorMap = new HashMap<Integer, Tile>();
		colorMap.put(0xffB8D9E2, skyTile);
		colorMap.put(0xff00ff01, grassTopLeftTile);
		colorMap.put(0xff00ff02, grassTopMidTile);
		colorMap.put(0xff00ff03, grassTopRightTile);
		colorMap.put(0xff00ff04, grassMidLeftTile);
		colorMap.put(0xff00ff05, grassMiddleTile);
		colorMap.put(0xff00ff06, grassMidRightTile);
		colorMap.put(0xff00ff07, grassBotLeftTile);
		colorMap.put(0xff00ff08, grassBotMidTile);
		colorMap.put(0xff00ff09, grassBotRightTile);
		colorMap.put(0xff00ff0A, grass1x1Tile);
		colorMap.put(0xff00ff0B, grassTop1xVTile);
		colorMap.put(0xff00ff0C, grassMid1xVTile);
		colorMap.put(0xff00ff0D, grassBot1xVTile);
		colorMap.put(0xff00ff0E, grassLeft1xHTile);
		colorMap.put(0xff00ff0F, grassMid1xHTile);
		colorMap.put(0xff00ff10, grassRight1xHTile);
		colorMap.put(0xff00ff11, grassInterTopLeftTile);
		colorMap.put(0xff00ff12, grassInterTopMidTile);
		colorMap.put(0xff00ff13, grassInterTopRightTile);
		colorMap.put(0xff00ff14, grassInterBotLeftTile);
		colorMap.put(0xff00ff15, grassInterBotMidTile);
		colorMap.put(0xff00ff16, grassInterBotRightTile);
		return colorMap;
	}

}

class PlatformLevelTile extends Tile {

	private static SpriteSheet platformLevelTiles = new SpriteSheet("/assets/levels/platformLevel/sprites.png", Sprite.defaultSize);

	public PlatformLevelTile(int x, int y) {
		super(new Sprite(platformLevelTiles, x, y), true);
	}

}
