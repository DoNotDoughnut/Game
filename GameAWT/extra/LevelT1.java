package rhys.game.objects.level.levels;

import rhys.game.objects.level.GameImageLevel;
import rhys.game.objects.sprite.Sprite;
import rhys.game.objects.sprite.SpriteSheet;
import rhys.game.objects.tile.Tile;
import rhys.game.objects.tile.TileCoordinate;
import rhys.game.objects.tile.tiles.BasicSolidTile;
import rhys.game.objects.tile.tiles.ConsoleTile;
import rhys.game.objects.tile.tiles.FountainTile;
import rhys.game.objects.tile.tiles.WaterTile;

@Deprecated
public class LevelT1 extends GameImageLevel {
	
	public static Sprite grassSprite = new LevelT1Sprites(0,0);
	public static Sprite pathSprite = new LevelT1Sprites(1,0);
	
	public static Tile consoleTile = new ConsoleTile(new Sprite(0x202020));
	
	public static Sprite stoneWallFrontSprite = new LevelT1Sprites(2,0);
	public static Sprite stoneWallLeftSprite = new LevelT1Sprites(2,1);
	public static Sprite stoneWallRightSprite = new LevelT1Sprites(2,2);
	public static Sprite stoneWallLeftCornerSprite = new LevelT1Sprites(2,3);
	public static Sprite stoneWallRightCornerSprite = new LevelT1Sprites(2,4);
	
	public static Sprite waterSprite = new LevelT1Sprites(3,0);
	public static Sprite fountainSprite = new LevelT1Sprites(4,0);
	public static Sprite wallLeftTopSprite = new LevelT1Sprites(5,0);
	public static Sprite wallTopSprite = new LevelT1Sprites(6,0);
	public static Sprite wallRightTopSprite = new LevelT1Sprites(7,0);
	public static Sprite wallLeftSprite = new LevelT1Sprites(5,1);
	public static Sprite floorSprite = new LevelT1Sprites(6,1);
	public static Sprite wallRightSprite = new LevelT1Sprites(7,1);
	public static Sprite wallLeftBottomSprite = new LevelT1Sprites(5,2);
	public static Sprite wallBottomSprite = new LevelT1Sprites(7,2);
	public static Sprite wallRightBottomSprite = new LevelT1Sprites(7,2);
	
	public static Sprite playerFront_0 = new LevelT1Sprites(12,1);
	public static Sprite playerFront_1 = new LevelT1Sprites(12,0);
	public static Sprite playerFront_2 = new LevelT1Sprites(12,2);
	
	public static Sprite playerBack = new LevelT1Sprites(13,0);
	public static Sprite playerLeft = new LevelT1Sprites(14,0);
	public static Sprite playerRight = new LevelT1Sprites(15,0);
	
	public static Tile grassTile = new Tile(grassSprite);
	public static Tile pathTile = new Tile(pathSprite);
	
	public static Tile stoneWallFrontTile = new BasicSolidTile(stoneWallFrontSprite);
	public static Tile stoneWallLeftTile = new BasicSolidTile(stoneWallLeftSprite);
	public static Tile stoneWallRightTile = new BasicSolidTile(stoneWallRightSprite);
	public static Tile stoneWallLeftCornerTile = new BasicSolidTile(stoneWallLeftCornerSprite);
	public static Tile stoneWallRightCornerTile = new BasicSolidTile(stoneWallRightCornerSprite);
	
	public static Tile waterTile = new WaterTile(waterSprite);
	public static Tile fountainTile = new FountainTile(fountainSprite);
	
	public static Tile wallLeftTopTile = new BasicSolidTile(wallLeftTopSprite);
	public static Tile wallTopTile = new BasicSolidTile(wallTopSprite);
	public static Tile wallRightTopTile = new BasicSolidTile(wallRightTopSprite);
	
	public static Tile wallLeftTile = new BasicSolidTile(wallLeftSprite);
	public static Tile floorTile = new BasicSolidTile(floorSprite);
	public static Tile wallRightTile = new BasicSolidTile(wallRightSprite);
	
	public static Tile wallLeftBottomTile = new BasicSolidTile(wallLeftBottomSprite);
	public static Tile wallBottomTile = new BasicSolidTile(wallBottomSprite);
	public static Tile wallRightBottomTile = new BasicSolidTile(wallRightBottomSprite);
	
	public LevelT1() {
		super();
	}
	
	public Tile getTileFromColor(int color) {
		if(color==0xffffffff) return Tile.voidTile;
		if(color==0xff00ff00) return grassTile;
		if(color==0xff000000) return pathTile;
		
		if(color==0xff404041) return stoneWallFrontTile;
		if(color==0xff404042) return stoneWallLeftTile;
		if(color==0xff404043) return stoneWallRightTile;
		if(color==0xff404044) return stoneWallLeftCornerTile;
		if(color==0xff404045) return stoneWallRightCornerTile;
		
		if(color==0xff0000ff) return waterTile;
		if(color==0xff1010ff) return fountainTile;
		if(color==0xff202020) return consoleTile;
		
		if(color==0xff000001) return wallLeftTopTile;
		if(color==0xff000002) return wallTopTile;
		if(color==0xff000003) return wallRightTopTile;
		if(color==0xff000004) return wallLeftTile;
		if(color==0xff000005) return floorTile;
		if(color==0xff000006) return wallRightTile;
		if(color==0xff000007) return wallLeftBottomTile;
		if(color==0xff000008) return wallBottomTile;
		if(color==0xff000009) return wallRightBottomTile;
		return Tile.voidTile;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() {
		spawnpoint = new TileCoordinate(12,33);
		loadLevel("/rhys/game/resources/levels/fountainLevel.png");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}

@Deprecated
class LevelT1Sprites extends Sprite {
	
	private static SpriteSheet fountainSprites = new SpriteSheet("/rhys/game/resources/spritesheets/fountain_sprites.png", Sprite.defaultSize);
	
	public LevelT1Sprites(int x, int y) {
		super(fountainSprites, x, y);
	}

}
