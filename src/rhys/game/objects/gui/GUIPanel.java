package rhys.game.objects.gui;

import java.util.ArrayList;

import rhys.game.main.GameRenderer;
import rhys.game.objects.sprite.Sprite;

public class GUIPanel {
	
	private ArrayList<GUIComponent> components;
	public int x, y, width, height;
	private Sprite sprite;
	
	public GUIPanel(int x, int y, int width, int height, Sprite sprite) {
		components = new ArrayList<>();
		this.x = x;
		this.y = y;
		this.width=width;
		this.height=height;
		this.sprite = sprite;
	}
	
	public void update() {
		for(GUIComponent components : components)
			components.update();
	}
	
	public void render(GameRenderer graphics) {
		graphics.render(x+graphics.xOffset, y+graphics.yOffset, sprite, false);
		for(GUIComponent components : components)
			components.render(graphics);
	}
	
	public void add(GUIComponent component) {
		components.add(component);
		component.xO+=x;
		component.yO+=y;
	}

}
