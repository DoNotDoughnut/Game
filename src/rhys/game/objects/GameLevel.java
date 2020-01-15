package rhys.game.objects;

import rhys.game.main.GameRenderer;

public class GameLevel {

	public int width, height;
	protected int[] tiles;
	public TileCoordinate spawnPoint;

	public GameLevel(String path, TileCoordinate spawnPoint) {
		loadLevel(path);
		this.spawnPoint = spawnPoint;
		generateLevel();
	}
	
	protected void loadLevel(String path) {

	}

	protected void generateLevel() {

	}
	
	public void update() {
		
	}
	
	public void render(int xScroll, int yScroll, GameRenderer gg) {
		gg.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4,
			x1 = (xScroll + gg.width + Tile.size) >> 4,
			y0 = yScroll >> 4,
			y1 = (yScroll + gg.height + Tile.size) >> 4;		
			for(int y = y0; y < y1; y++)
				for(int x = x0; x < x1; x++)
					getTileFromCoordinates(x,y).render(x, y, gg);
	}
	
	public Tile getTileFromCoordinates(int x, int y) {
		if(x < 0 || y < 0 || y >= height || x >= width) return Tile.boundTile;
		return getTileFromColor(tiles[x+y*width]);
	}
	
	public Tile getTileFromColor(int color) {
		return Tile.voidTile;
	}
	

}
