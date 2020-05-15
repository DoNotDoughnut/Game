package net.rhysholloway.game.world.level;

import net.rhysholloway.game.Game;
import net.rhysholloway.game.world.entity.entities.ScreenFocus;
import net.rhysholloway.game.world.tile.Tile;
import net.rhysholloway.game.world.tile.TileCoordinate;

public abstract class GameLevel {

	public int width, height;
	protected Tile[] tiles; //Tiles, stored in a color map
	public ScreenFocus focus;
	public TileCoordinate spawnpoint;

	public GameLevel(ScreenFocus focus) {
		this.focus = focus;
	}
	
	public abstract void create();
	
	public abstract void update();
	
	public abstract void render();
	
	public abstract void dispose();
	
	protected void drawLevel() {
		int x0 = focus.getX() >> 4,
				x1 = (focus.getX() + Game.canvasWidth + Tile.size) >> 4,
				y0 = focus.getY() >> 4,
				y1 = (focus.getY() + Game.canvasHeight + Tile.size) >> 4;		
				for(int y = y0; y < y1; y++)
					for(int x = x0; x < x1; x++)
						getTileFromCoordinates(x,y).render((x << 4) - focus.getX(), (y << 4) - focus.getY());
	}

	public Tile getTileFromCoordinates(int x, int y) {
		if (x < 0 || y < 0 || y >= height || x >= width)
			return Tile.boundTile;
		return tiles[x + y * width];
	}

}
