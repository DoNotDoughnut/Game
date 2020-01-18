package net.rhys.game.objects.level.levels;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.rhys.game.objects.level.GameLevel;
import net.rhys.game.objects.tile.Tile;
import net.rhys.game.objects.tile.TileCoordinate;
import net.rhys.game.objects.tile.tiles.BasicSolidTile;
import net.rhys.gameengine.texture.ETexture;
import net.rhys.gameengine.texture.ETextureSheet;

public class PlatformLevel extends GameLevel {
	
	public static GameLevel level = new PlatformLevel();
	
	public PlatformLevel() {
		super("/net/rhys/game/resources/levels/platformLevel.png", new TileCoordinate(3,12));
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(PlatformLevel.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width*height];
			image.getRGB(0,0,width,height,tiles,0,width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Tile getTileFromColor(int color) {
		if(color==0xffB8D9E2) return skyTile;
		//if(color==0xFFFF0000) return Tile.spawnTile;
		//if(color==0xff202020) return Tile.consoleTile;	
		if(color==0xff00ff01) return grassTopLeftTile;
		if(color==0xff00ff02) return grassTopMidTile;
		if(color==0xff00ff03) return grassTopRightTile;
		if(color==0xff00ff04) return grassMidLeftTile;
		if(color==0xff00ff05) return grassMiddleTile;
		if(color==0xff00ff06) return grassMidRightTile;
		if(color==0xff00ff07) return grassBotLeftTile;
		if(color==0xff00ff08) return grassBotMidTile;
		if(color==0xff00ff09) return grassBotRightTile;
		if(color==0xff00ff0A) return grass1x1Tile;
		if(color==0xff00ff0B) return grassTop1xVTile;
		if(color==0xff00ff0C) return grassMid1xVTile;
		if(color==0xff00ff0D) return grassBot1xVTile;
		if(color==0xff00ff0E) return grassLeft1xHTile;
		if(color==0xff00ff0F) return grassMid1xHTile;
		if(color==0xff00ff10) return grassRight1xHTile;
		if(color==0xff00ff11) return grassInterTopLeftTile;
		if(color==0xff00ff12) return grassInterTopMidTile;
		if(color==0xff00ff13) return grassInterTopRightTile;
		if(color==0xff00ff14) return grassInterBotLeftTile;
		if(color==0xff00ff15) return grassInterBotMidTile;
		if(color==0xff00ff16) return grassInterBotRightTile;
		return Tile.voidTile;
	}
	
	public static ETexture skyTexture = new ETexture(0xB8D9E2);
	public static ETexture grassTopTextures = new Level1Texture(0, 0, 3);
	public static ETexture grassMidTextures = new Level1Texture(0,1,3);
	public static ETexture grassBotTextures = new Level1Texture(0,2,3);
	public static ETexture grass1xVTextures = new Level1Texture(3,0,3);
	public static ETexture grass1xHTextures = new Level1Texture(3,1,3);
	public static ETexture grassInterTopTexture = new Level1Texture(3,2,3);
	public static ETexture grassInterBotTexture = new Level1Texture(6,0,3);
	public static ETexture grass1x1Texture = new Level1Texture(9,0,1);
	
	public static Tile skyTile = new Tile(skyTexture);
	
	public static Tile grassTopLeftTile = new BasicSolidTile(grassTopTextures);
	public static Tile grassTopMidTile = new BasicSolidTile(grassTopTextures.getVariant(2));
	public static Tile grassTopRightTile = new BasicSolidTile(grassTopTextures.getVariant(3));
	
	public static Tile grassMidLeftTile = new BasicSolidTile(grassMidTextures);
	public static Tile grassMiddleTile = new BasicSolidTile(grassMidTextures.getVariant(2));
	public static Tile grassMidRightTile = new BasicSolidTile(grassMidTextures.getVariant(3));
	
	public static Tile grassBotLeftTile = new BasicSolidTile(grassBotTextures);
	public static Tile grassBotMidTile = new BasicSolidTile(grassBotTextures.getVariant(2));
	public static Tile grassBotRightTile = new BasicSolidTile(grassBotTextures.getVariant(3));
	
	public static Tile grassTop1xVTile = new BasicSolidTile(grass1xVTextures);
	public static Tile grassMid1xVTile = new BasicSolidTile(grass1xVTextures.getVariant(2));
	public static Tile grassBot1xVTile = new BasicSolidTile(grass1xVTextures.getVariant(3));
	
	public static Tile grassLeft1xHTile = new BasicSolidTile(grass1xHTextures);
	public static Tile grassMid1xHTile = new BasicSolidTile(grass1xHTextures.getVariant(2));
	public static Tile grassRight1xHTile = new BasicSolidTile(grass1xHTextures.getVariant(3));
	
	public static Tile grassInterTopLeftTile = new BasicSolidTile(grassInterTopTexture);
	public static Tile grassInterTopMidTile = new BasicSolidTile(grassInterTopTexture.getVariant(2));
	public static Tile grassInterTopRightTile = new BasicSolidTile(grassInterTopTexture.getVariant(3));
	
	public static Tile grassInterBotLeftTile = new BasicSolidTile(grassInterBotTexture);
	public static Tile grassInterBotMidTile = new BasicSolidTile(grassInterBotTexture.getVariant(2));
	public static Tile grassInterBotRightTile = new BasicSolidTile(grassInterBotTexture.getVariant(3));
	
	public static Tile grass1x1Tile = new BasicSolidTile(grass1x1Texture);
	
}

class Level1Texture extends ETexture {
	
	private static ETextureSheet level1Textures = new ETextureSheet("/net/rhys/game/resources/spritesheets/level1Sheet.png", 10, 3, ETexture.defaultSize);
	
	public Level1Texture(int x, int y, int variants) {
		super(level1Textures, x, y, variants);
	}

}
