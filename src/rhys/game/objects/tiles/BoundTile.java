package rhys.game.objects.tiles;

import rhys.game.objects.Sprite;
import rhys.game.objects.Tile;

public class BoundTile extends Tile {

	public BoundTile(Sprite sprite) {
		super(sprite);
	}
	
/*	public void render(int x, int y, GameRenderer gg) {
		gg.renderTile(x << 4, y << 4, this);
	}*/
	
	public boolean isSolid() {
		return true;
	}

}