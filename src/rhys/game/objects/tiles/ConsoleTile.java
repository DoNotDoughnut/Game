package rhys.game.objects.tiles;

import rhys.game.main.GameRenderer;
import rhys.game.objects.Sprite;
import rhys.game.objects.Tile;

public class ConsoleTile extends Tile {

	public ConsoleTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, GameRenderer gg) {
		gg.renderTile(x << 4, y << 4, this);
	}

	public boolean isSolid() {
		return true;
	}
	
	public void onClick() {
		onInteract();
	}
	
	public void onInteract() {
		System.out.println("Hello!");
	}
	
}
