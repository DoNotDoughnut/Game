package net.rhys.game.objects.entity;

import java.util.ArrayList;

import net.rhys.game.objects.level.GameLevel;
import net.rhys.game.objects.tile.Tile;
import net.rhys.game.objects.tile.TileCoordinate;
import net.rhys.gameengine.input.EKeyInput;
import net.rhys.gameengine.input.EMouseInput;

public class TileEntity extends Entity {
	
	protected GameLevel level;
	protected EKeyInput keyInput;
	protected EMouseInput mouseInput;
	protected TileCoordinate tilePos;
	
	private static ArrayList<TileEntity> tiles = new ArrayList<>();
	
	public TileEntity(GameLevel level, EKeyInput keyInput, EMouseInput mouseInput, TileCoordinate tilePos) {
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
