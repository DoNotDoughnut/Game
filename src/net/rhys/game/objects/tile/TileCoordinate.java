package net.rhys.game.objects.tile;

import net.rhys.game.objects.level.GameLevel;

public class TileCoordinate {
	
	
	public int x, y, xp, yp;

	public TileCoordinate(int x, int y) {
		this.xp=x * Tile.size;
		this.yp=y * Tile.size;
	}
	
	public Tile getTile(GameLevel level) {
		return level.getTileFromCoordinates(xp, yp);
	}
	
	public void setX(int x) {
		this.x = x;
		xp = x * Tile.size;
	}
	
	public void setY(int y) {
		this.y = y;
		yp = y * Tile.size;
	}
	
	
}
