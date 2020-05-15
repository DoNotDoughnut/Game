package net.rhysholloway.game.world.tile;

import net.rhysholloway.game.world.level.GameLevel;

public class TileCoordinate {
	
	
	public int x, y, xpixel, ypixel;

	public TileCoordinate(int x, int y) {
		this.xpixel=x * Tile.size;
		this.ypixel=y * Tile.size;
	}
	
	public Tile getTile(GameLevel level) {
		return level.getTileFromCoordinates(xpixel, ypixel);
	}
	
	public void setX(int x) {
		this.x = x;
		xpixel = x * Tile.size;
	}
	
	public void setY(int y) {
		this.y = y;
		ypixel = y * Tile.size;
	}
	
	
}
