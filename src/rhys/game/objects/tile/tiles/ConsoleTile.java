package rhys.game.objects.tile.tiles;

import rhys.game.objects.sprite.Sprite;
import rhys.game.objects.tile.Tile;

public class ConsoleTile extends Tile {

	public ConsoleTile(Sprite sprite) {
		super(sprite);
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
