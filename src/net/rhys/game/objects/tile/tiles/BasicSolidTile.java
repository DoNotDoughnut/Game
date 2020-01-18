package net.rhys.game.objects.tile.tiles;

import net.rhys.game.objects.tile.Tile;
import net.rhys.gameengine.texture.ETexture;

public class BasicSolidTile extends Tile {

	public BasicSolidTile(ETexture texture) {
		super(texture);
	}
	
	public boolean isSolid() {
		return true;
	}

}
