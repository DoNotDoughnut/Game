package net.rhysholloway.game.gui;

import net.rhysholloway.game.graphics.sprite.Sprite;
import net.rhysholloway.game.world.entity.Entity;

public abstract class GUIComponent extends Entity {
	
	public int x, y, width, height;
	public final int startX, startY;
	protected Sprite sprite;
	
	public GUIComponent(int x, int y) {
		this.startX = x;
		this.startY = y;
		this.x=x;
		this.y=y;
	}
	
	public GUIComponent(int x, int y, int width, int height) {
		this(x,y);
		this.width=width;
		this.height=height;
	}
	
	public GUIComponent(Sprite sprite, int x, int y, int width, int height) {
		this(x, y, width, height);
		this.sprite = sprite;
	}
	
	public abstract void update();
	
	public abstract void render();
	
	public void spawn() {
		alive = true;
	}
	
	public void despawn() {
		alive = false;
	}
	
	public void move(int xOffset, int yOffset) {
		x += xOffset;
		y += yOffset;
	}

	public void resetPos() {
		x = startX;
		y = startY;
	}

}
