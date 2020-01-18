package net.rhys.game.objects.tile;

import net.rhys.game.objects.tile.tiles.BasicSolidTile;
import net.rhys.game.objects.tile.tiles.TileTextures;
import net.rhys.gameengine.render.ERenderer;
import net.rhys.gameengine.texture.ETexture;

public class Tile {

	public int x, y;
	public ETexture texture;
	
	public static Tile boundTile = new BasicSolidTile(TileTextures.boundTexture);
	public static Tile voidTile = new Tile(TileTextures.voidTexture);
	
	public static final int size = 16;
	
	public Tile(ETexture texture) {
		this.texture=texture;
	}
	
	//Change to cycle through ones on screen only
	
	public void render(int x, int y, ERenderer graphics) {
		graphics.render(x << 4, y << 4, this.texture, false);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public void onClick() {
		
	}
	
	public void onInteract() {
		
	}
}
