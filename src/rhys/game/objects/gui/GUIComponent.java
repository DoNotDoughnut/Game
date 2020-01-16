package rhys.game.objects.gui;

import rhys.game.main.GameRenderer;
import rhys.game.objects.entity.Entity;
import rhys.game.objects.sprite.Sprite;

public class GUIComponent extends Entity {
	
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
	
	public void update() {
		
	}
	
	public void render(GameRenderer graphics) {
		graphics.render(x+graphics.xOffset, y+graphics.yOffset, sprite, false);
	}

}
