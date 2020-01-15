package rhys.game.objects.tile;

import java.util.ArrayList;

import rhys.game.input.GameMouseListener;
import rhys.game.main.Game;
import rhys.game.main.GameRenderer;
import rhys.game.objects.sprite.Sprite;
import rhys.game.objects.tile.tiles.BoundTile;
import rhys.game.objects.tile.tiles.ConsoleTile;
import rhys.game.objects.tile.tiles.SpawnTile;
import rhys.game.objects.tile.tiles.VoidTile;

public class Tile {

	public int x, y;
	public Sprite sprite;
	
	public static ArrayList<TileCoordinate> tileUpdateList = new ArrayList<>();
	
	public static Tile boundTile = new BoundTile(Sprite.boundSprite);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public static Tile spawnTile = new SpawnTile(Sprite.spawnSprite);
	
	public static Tile consoleTile = new ConsoleTile(Sprite.consoleSprite);
	public static TileCoordinate console1Coords = new TileCoordinate(consoleTile, 6,15);
	public static TileCoordinate console2Coords = new TileCoordinate(consoleTile, 18,15);

	
	public static final int size = 16;
	
	public Tile(Sprite sprite) {
		this.sprite=sprite;
	}
	
	//Change to cycle through ones on screen only
	
	public static void update() {
		if(GameMouseListener.getButton()==1) {
			GameMouseListener.resetButton();
			for (int i = 0; i < tileUpdateList.size(); i++)
				if(clicked(tileUpdateList.get(i)))
					tileUpdateList.get(i).tile.onClick();
		}		
	}
	
	public void render(int x, int y, GameRenderer gg) {
		gg.renderTile(x << 4, y << 4, this, false);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public void onClick() {
		
	}
	
	public void onInteract() {
		
	}
	
	public static boolean clicked(TileCoordinate tile) {
			return((tile.x + Tile.size > GameMouseListener.getX() + Game.graphics.xOffset && tile.x < GameMouseListener.getX() + Game.graphics.xOffset) && (tile.y + Tile.size > GameMouseListener.getY() + Game.graphics.yOffset && tile.y < GameMouseListener.getY() + Game.graphics.yOffset));
	}
}
