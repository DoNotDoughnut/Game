package rhys.game.objects.tiles;

import rhys.game.objects.Sprite;
import rhys.game.objects.Tile;

public class BasicSolidTile extends Tile {

	public BasicSolidTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean isSolid() {
		return true;
	}

}
