package net.rhysholloway.game.world.entity.entities;

import net.rhysholloway.game.Game;
import net.rhysholloway.game.world.entity.Entity;
import net.rhysholloway.game.world.entity.Mob;

public class ScreenFocus extends Entity {

	private Mob focus;
	private int x, y;
	
	public ScreenFocus() {
		this.x = 0;
		this.y = 0;
	}

	public ScreenFocus(Mob focus) {
		this.focus = focus;
		spawn();
	}
	
	public void update() {
		if(alive) {
			x = focus.hitbox.x + (focus.hitbox.width /2) - (Game.canvasWidth / 2);
			y = focus.hitbox.y + (focus.hitbox.height/2) - (Game.canvasHeight / 2);
		}
	}	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
