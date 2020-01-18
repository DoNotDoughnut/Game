package rhys.game.objects.tile.tiles;

import rhys.game.objects.sprite.Sprite;
import rhys.game.objects.tile.Tile;

public class BasicSolidTile extends Tile {

	public BasicSolidTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean isSolid() {
		return true;
	}

}
