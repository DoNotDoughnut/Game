package net.rhys.game.objects.level.levels;

import java.util.Arrays;

import net.rhys.game.objects.level.GameLevel;
import net.rhys.game.objects.tile.TileCoordinate;

public class MainMenuLevel extends GameLevel {

	public MainMenuLevel(TileCoordinate spawnPoint) {
		super(spawnPoint);
	}

	protected void loadLevel() {
		tiles = new int[16*16];
		Arrays.fill(tiles, 0x000000);
	}
}
