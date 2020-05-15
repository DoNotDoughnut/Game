package rhys.game.objects.tile.tiles;

import rhys.game.objects.sprite.Sprite;
import rhys.game.objects.tile.Tile;
import rhys.game.objects.tile.TileCoordinate;

public class ConsoleTile extends Tile {
	
	public static TileCoordinate console1Coords = new TileCoordinate(6,15);
	public static TileCoordinate console2Coords = new TileCoordinate(18,15);

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
