package rhys.game.objects.gui;

import rhys.game.main.GameRenderer;
import rhys.game.objects.sprite.Sprite;

public class GUIComponent {
	
	public int x, y, width, height;
	protected int xO, yO;
	private Sprite sprite;
	
	public GUIComponent(int x, int y) {
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
		graphics.render(x+xO, y+yO, sprite, false);
	}
	
	public int getTrueX() {
		return x+xO;
	}
	
	public int getTrueY() {
		return y+yO;
	}

}
