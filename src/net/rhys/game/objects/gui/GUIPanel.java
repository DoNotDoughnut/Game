package net.rhys.game.objects.gui;

import java.util.ArrayList;

import net.rhys.game.objects.entity.Entity;
import net.rhys.gameengine.render.ERenderer;
import net.rhys.gameengine.texture.ETexture;

public class GUIPanel extends Entity {
	
	public ArrayList<GUIComponent> components;
	public int x, y, width, height;
	public final int startX, startY;
	public String name;
	private ETexture texture;
	
	public GUIPanel(int x, int y, int width, int height, ETexture texture, String name) {
		components = new ArrayList<>();
		this.startX = x;
		this.startY = y;
		this.x = x;
		this.y = y;
		this.width=width;
		this.height=height;
		this.texture = texture;
		this.name = name;
	}
	
	public void update() {
		for(GUIComponent component : components)
			component.update();
	}
	
	public void render(ERenderer graphics) {
		graphics.render(x+graphics.xOffset, y+graphics.yOffset, texture, false);
		for(GUIComponent components : components)
			components.render(graphics);
	}
	
	public void spawn() {
		alive = true;
		for(GUIComponent component : components)
			component.spawn();
	}
	
	public void despawn() {
		alive = false;
		for(GUIComponent component : components)
			component.despawn();
	}
	
	public void updateList(ArrayList<GUIComponent> list) {
		components=list;
	}

	public void add(GUIComponent component) {
		components.add(component);
	}
	
	public void move(int xOffset, int yOffset) {
		x += xOffset;
		y += yOffset;
		for(GUIComponent component : components)
			component.move(xOffset, yOffset);
	}
	
	public void resetPos() {
		x = startX;
		y = startY;
		for(GUIComponent component : components)
			component.resetPos();
	}
	
}
