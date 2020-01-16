package rhys.game.objects.entity;

import java.util.ArrayList;

import rhys.game.input.GameKeyListener;
import rhys.game.input.GameMouseListener;
import rhys.game.objects.level.GameLevel;
import rhys.game.objects.tile.Tile;
import rhys.game.objects.tile.TileCoordinate;

public class TileEntity extends Entity {
	
	protected GameLevel level;
	protected GameKeyListener keyInput;
	protected GameMouseListener mouseInput;
	protected TileCoordinate tilePos;
	
	private static ArrayList<TileEntity> tiles = new ArrayList<>();
	
	public TileEntity(GameLevel level, GameKeyListener keyInput, GameMouseListener mouseInput, TileCoordinate tilePos) {
		this.level=level;
		this.keyInput=keyInput;
		this.mouseInput=mouseInput;
		this.tilePos=tilePos;
	}

	public void update() {
		for(TileEntity tile : tiles)
			tile.update();
	}
	
	public void spawn() {
		tiles.add(this);
		alive = true;
	}
	
	public void despawn() {
		tiles.remove(this);
		alive = false;
	}
	
	public boolean clicked() {
		return((tilePos.x + Tile.size > (mouseInput.getX() + mouseInput.graphics.xOffset) && tilePos.x < mouseInput.getX() + mouseInput.graphics.xOffset) && 
			   (tilePos.y + Tile.size > (mouseInput.getY() + mouseInput.graphics.yOffset) && tilePos.y < mouseInput.getY() + mouseInput.graphics.yOffset));
	}
	
}
