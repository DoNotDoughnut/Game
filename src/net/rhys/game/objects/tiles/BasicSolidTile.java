package net.rhys.game.objects.tiles;

import net.rhys.gameengine.rendering.texture.ETexture;
import net.rhys.gameengine.tile.ETile;

public class BasicSolidTile extends ETile {

	public BasicSolidTile(ETexture texture) {
		super(texture);
	}
	
	public boolean isSolid() {
		return true;
	}

}
