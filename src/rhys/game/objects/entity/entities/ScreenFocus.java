package rhys.game.objects.entity.entities;

import rhys.game.main.GameRenderer;
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
	
	public void render(GameRenderer graphics) {
		if(alive) {
			x = focus.hitbox.x + (focus.hitbox.width /2) - (graphics.width /2);
			y = focus.hitbox.y + (focus.hitbox.height/2) - (graphics.height/2);
		}
	}	
	
}
