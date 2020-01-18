package net.rhys.game.objects.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.rhys.game.objects.tile.Tile;
import net.rhys.game.objects.tile.TileCoordinate;
import net.rhys.gameengine.EEngine;
import net.rhys.gameengine.render.ERenderer;


public class GameLevel {

	public int width, height;
	protected int[] tiles; //Tiles, stored in a color map
	protected String path;
	public final TileCoordinate spawnPoint;

	public GameLevel(TileCoordinate spawnPoint, EEngine engine) {
		this.spawnPoint = spawnPoint;
		loadLevel();
		generateLevel(engine);
	}
	
	public GameLevel(String path, TileCoordinate spawnPoint, EEngine engine) {
		this.path=path;
		this.spawnPoint = spawnPoint;
		loadLevel();
		generateLevel(engine);
		
	}
	
	protected void loadLevel() {
		try {
			BufferedImage image = ImageIO.read(GameLevel.class.getResource(EEngine.resources+"levels/"+path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width*height];
			image.getRGB(0,0,width,height,tiles,0,width);			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load level's image file");
		}
	}

	protected void generateLevel(EEngine engine) {

	}
	
	public void update() {

	}
	
	public void renderTiles(int xScroll, int yScroll, ERenderer graphics) {
		graphics.xOffset = xScroll;
		graphics.yOffset = yScroll;
		int x0 = xScroll >> 4,
			x1 = (xScroll + graphics.width + Tile.size) >> 4,
			y0 = yScroll >> 4,
			y1 = (yScroll + graphics.height + Tile.size) >> 4;		
			for(int y = y0; y < y1; y++)
				for(int x = x0; x < x1; x++)
					getTileFromCoordinates(x,y).render(x, y, graphics);
	}
	
	public Tile getTileFromCoordinates(int x, int y) {
		if(x < 0 || y < 0 || y >= height || x >= width) return Tile.boundTile;
		return getTileFromColor(tiles[x+y*width]);
	}
	
	public Tile getTileFromColor(int color) {
		return Tile.voidTile;
	}

	public void init(EEngine engine) {
		
	}

	public void render(ERenderer graphics) {
		
	}
}
