package net.rhys.game.objects.entity.entities;

import net.rhys.game.objects.entity.Entity;
import net.rhys.game.objects.entity.Mob;
import net.rhys.gameengine.render.ERenderer;

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
	
	public void render(ERenderer graphics) {
		if(alive) {
			x = focus.hitbox.x + (focus.hitbox.width /2) - (graphics.width /2);
			y = focus.hitbox.y + (focus.hitbox.height/2) - (graphics.height/2);
		}
	}	
	
}
