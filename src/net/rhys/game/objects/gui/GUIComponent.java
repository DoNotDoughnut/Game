package net.rhys.game.objects.gui;

import net.rhys.game.objects.entity.Entity;
import net.rhys.gameengine.rendering.ERenderer;
import net.rhys.gameengine.rendering.texture.ETexture;

public class GUIComponent extends Entity {
	
	public int x, y, width, height;
	public final int startX, startY;
	protected ETexture texture;
	
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
	
	public GUIComponent(ETexture texture, int x, int y, int width, int height) {
		this(x, y, width, height);
		this.texture = texture;
	}
	
	public void update() {
		
	}
	
	public void render(ERenderer graphics) {
		graphics.render(x+graphics.xOffset, y+graphics.yOffset, texture, false);
	}
	
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
