package rhys.game.objects.entity.entities;

import rhys.game.graphics.Window;
import rhys.game.objects.entity.Entity;
import rhys.game.objects.entity.Mob;

public class ScreenFocus extends Entity {

	private Mob focus;
	public int x, y;
	
	public ScreenFocus(Mob focus) {
		super();
		this.focus = focus;
		spawn();
	}
	
	public void update() {
		
	}
	
	public void render(Window window) {
		if(alive) {
			x = focus.hitbox.x + (focus.hitbox.width /2) - (window.width /2);
			y = focus.hitbox.y + (focus.hitbox.height/2) - (window.height/2);
		}
	}	
	
}
