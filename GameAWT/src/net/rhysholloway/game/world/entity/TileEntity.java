package net.rhysholloway.game.world.entity;

import java.util.ArrayList;

import net.rhysholloway.game.input.GameKeyListener;
import net.rhysholloway.game.input.GameMouseListener;
import net.rhysholloway.game.world.level.GameLevel;
import net.rhysholloway.game.world.tile.Tile;
import net.rhysholloway.game.world.tile.TileCoordinate;

public abstract class TileEntity extends Entity {
	
	protected GameLevel level;
	protected TileCoordinate tilePos;
	
	private static ArrayList<TileEntity> tiles = new ArrayList<>();
	
	public TileEntity(GameLevel level, GameKeyListener keyInput, GameMouseListener mouseInput, TileCoordinate tilePos) {
		this.level=level;
		this.tilePos=tilePos;
	}

	public static void updateActiveTiles() {
		for(TileEntity tile : tiles)
			tile.update();
	}
	
	public abstract void activate();
	
	public void spawn() {
		tiles.add(this);
		alive = true;
	}
	
	public void despawn() {
		tiles.remove(this);
		alive = false;
	}
	
	public boolean clicked() {
		return((tilePos.x + Tile.size > (GameMouseListener.getX() + level.focus.getX()) && tilePos.x < GameMouseListener.getX() + level.focus.getX()) && 
			   (tilePos.y + Tile.size > (GameMouseListener.getY() + level.focus.getY()) && tilePos.y < GameMouseListener.getY() + level.focus.getY()));
	}
	
}
