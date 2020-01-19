package net.rhys.game.objects.tiles;

import net.rhys.gameengine.rendering.texture.ETexture;
import net.rhys.gameengine.tile.ETile;

public class BoundTile extends ETile {

	public BoundTile(ETexture texture) {
		super(texture);
	}
	
	public boolean isSolid() {
		return true;
	}

}
