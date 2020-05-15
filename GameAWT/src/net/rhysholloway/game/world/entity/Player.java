package net.rhysholloway.game.world.entity;

import net.rhysholloway.game.graphics.sprite.Sprite;
import net.rhysholloway.game.gui.guis.PlayerHud;
import net.rhysholloway.game.world.entity.util.HitboxSpecs;
import net.rhysholloway.game.world.level.GameLevel;

public abstract class Player extends Mob {
	
	//public int userID;
	protected PlayerHud playerHud;
	
	public Player(GameLevel level, Sprite sprite, HitboxSpecs hitbox, int speed) {
		super(level, sprite, hitbox, speed, false, true);
		playerHud = new PlayerHud();
		playerHud.enable(0);
	}
	
	public void damage() {
		super.damage();
		playerHud.setHealth(health);
	}
	
	public void update() {
		super.update();
		playerHud.update();
	}
	
	public void render() {
		playerHud.render();
	}
	
}
