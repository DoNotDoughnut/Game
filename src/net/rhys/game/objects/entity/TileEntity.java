package net.rhys.game.objects.entity;

import java.util.ArrayList;

import net.rhys.gameengine.input.EKeyInput;
import net.rhys.gameengine.input.EMouseInput;
import net.rhys.gameengine.level.ELevel;
import net.rhys.gameengine.tile.ETile;

public class TileEntity extends Entity {
	
	protected ELevel level;
	protected EKeyInput keyInput;
	protected EMouseInput mouseInput;
	public int x,y;
	
	private static ArrayList<TileEntity> tiles = new ArrayList<>();
	
	public TileEntity(ELevel level, EKeyInput keyInput, EMouseInput mouseInput, int x, int y) {
		this.level=level;
		this.keyInput=keyInput;
		this.mouseInput=mouseInput;
		this.x=x;
		this.y=y;
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
		return((ETile.size * x + ETile.size > (mouseInput.getX() + mouseInput.graphics.xOffset) && ETile.size * x < mouseInput.getX() + mouseInput.graphics.xOffset) && 
			   (ETile.size * y + ETile.size > (mouseInput.getY() + mouseInput.graphics.yOffset) && ETile.size * y < mouseInput.getY() + mouseInput.graphics.yOffset));
	}
	
}
