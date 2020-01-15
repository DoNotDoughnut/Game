package rhys.game.objects.level.levels;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import rhys.game.objects.level.GameLevel;
import rhys.game.objects.sprite.Sprite;
import rhys.game.objects.sprite.SpriteSheet;
import rhys.game.objects.tile.Tile;
import rhys.game.objects.tile.TileCoordinate;
import rhys.game.objects.tile.tiles.BasicSolidTile;

public class PlatformLevel extends GameLevel {
	
	public PlatformLevel(String path, TileCoordinate spawnPoint) {
		super(path, spawnPoint);
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
	
	public static Sprite skySprite = new Sprite(0xB8D9E2);
	public static Sprite grassTopSprites = new Level1Sprite(0, 0, 3);
	public static Sprite grassMidSprites = new Level1Sprite(0,1,3);
	public static Sprite grassBotSprites = new Level1Sprite(0,2,3);
	public static Sprite grass1xVSprites = new Level1Sprite(3,0,3);
	public static Sprite grass1xHSprites = new Level1Sprite(3,1,3);
	public static Sprite grassInterTopSprite = new Level1Sprite(3,2,3);
	public static Sprite grassInterBotSprite = new Level1Sprite(6,0,3);
	public static Sprite grass1x1Sprite = new Level1Sprite(9,0,1);
	
	public static Tile skyTile = new Tile(skySprite);
	
	public static Tile grassTopLeftTile = new BasicSolidTile(grassTopSprites);
	public static Tile grassTopMidTile = new BasicSolidTile(grassTopSprites.getVariant(2));
	public static Tile grassTopRightTile = new BasicSolidTile(grassTopSprites.getVariant(3));
	
	public static Tile grassMidLeftTile = new BasicSolidTile(grassMidSprites);
	public static Tile grassMiddleTile = new BasicSolidTile(grassMidSprites.getVariant(2));
	public static Tile grassMidRightTile = new BasicSolidTile(grassMidSprites.getVariant(3));
	
	public static Tile grassBotLeftTile = new BasicSolidTile(grassBotSprites);
	public static Tile grassBotMidTile = new BasicSolidTile(grassBotSprites.getVariant(2));
	public static Tile grassBotRightTile = new BasicSolidTile(grassBotSprites.getVariant(3));
	
	public static Tile grassTop1xVTile = new BasicSolidTile(grass1xVSprites);
	public static Tile grassMid1xVTile = new BasicSolidTile(grass1xVSprites.getVariant(2));
	public static Tile grassBot1xVTile = new BasicSolidTile(grass1xVSprites.getVariant(3));
	
	public static Tile grassLeft1xHTile = new BasicSolidTile(grass1xHSprites);
	public static Tile grassMid1xHTile = new BasicSolidTile(grass1xHSprites.getVariant(2));
	public static Tile grassRight1xHTile = new BasicSolidTile(grass1xHSprites.getVariant(3));
	
	public static Tile grassInterTopLeftTile = new BasicSolidTile(grassInterTopSprite);
	public static Tile grassInterTopMidTile = new BasicSolidTile(grassInterTopSprite.getVariant(2));
	public static Tile grassInterTopRightTile = new BasicSolidTile(grassInterTopSprite.getVariant(3));
	
	public static Tile grassInterBotLeftTile = new BasicSolidTile(grassInterBotSprite);
	public static Tile grassInterBotMidTile = new BasicSolidTile(grassInterBotSprite.getVariant(2));
	public static Tile grassInterBotRightTile = new BasicSolidTile(grassInterBotSprite.getVariant(3));
	
	public static Tile grass1x1Tile = new BasicSolidTile(grass1x1Sprite);
	
}

class Level1Sprite extends Sprite {
	
	//private static SpriteSheet platformSprites = new SpriteSheet("/rhys/game/resources/spritesheets/platform_grass_spritesheet.png", 25, Sprite.defaultSize);
	private static SpriteSheet level1Sprites = new SpriteSheet("/rhys/game/resources/spritesheets/level1Sheet.png", 10, 3, Sprite.defaultSize);
	
	public Level1Sprite(int x, int y, int variants) {
		super(level1Sprites, x, y, variants);
	}

}
