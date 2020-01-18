package rhys.game.objects.tile;

import rhys.game.main.GameRenderer;
import rhys.game.objects.sprite.Sprite;
import rhys.game.objects.tile.tiles.BasicSolidTile;

public class Tile {

	public int x, y;
	public Sprite sprite;
	
	public static Tile boundTile = new BasicSolidTile(Sprite.boundSprite);
	public static Tile voidTile = new Tile(Sprite.voidSprite);
	
	public static final int size = 16;
	
	public Tile(Sprite sprite) {
		this.sprite=sprite;
	}
	
	//Change to cycle through ones on screen only
	
	public void render(int x, int y, GameRenderer graphics) {
		graphics.render(x << 4, y << 4, this.sprite, false);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public void onClick() {
		
	}
	
	public void onInteract() {
		
	}
}
