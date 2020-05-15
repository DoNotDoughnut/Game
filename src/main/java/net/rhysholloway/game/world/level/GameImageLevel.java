package net.rhysholloway.game.world.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import net.rhysholloway.game.world.entity.entities.ScreenFocus;
import net.rhysholloway.game.world.tile.Tile;

public abstract class GameImageLevel extends GameLevel {

	private final HashMap<Integer, Tile> colorMap;
	
	public GameImageLevel(String path, HashMap<Integer, Tile> colorMap) {
		super(new ScreenFocus());
		this.colorMap = colorMap;
		try {
			BufferedImage image = ImageIO.read(getClass().getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			int[] colors = image.getRGB(0,0,width,height,null,0,width);
			
			tiles = new Tile[width*height];
			for(int i = 0; i < colors.length; i++) {
				tiles[i] = getTileFromColor(colors[i]);
			}
			
			image.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load level's image file");
		}
	}

	public Tile getTileFromColor(int color) {
		if (colorMap.get(color) != null)
			return colorMap.get(color);
		else
			return Tile.voidTile;
	}

}
