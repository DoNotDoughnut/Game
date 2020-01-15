package rhys.game.objects.tiles;

import rhys.game.main.GameRenderer;
import rhys.game.objects.Sprite;
import rhys.game.objects.Tile;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, GameRenderer gg) {
		gg.renderTile(x << 4, y << 4, this);
	}

}
