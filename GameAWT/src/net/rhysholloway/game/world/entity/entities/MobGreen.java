package net.rhysholloway.game.world.entity.entities;

import net.rhysholloway.game.graphics.GameRenderer;
import net.rhysholloway.game.graphics.sprite.AnimatedSprite;
import net.rhysholloway.game.graphics.sprite.SpriteSheet;
import net.rhysholloway.game.world.entity.Hitbox;
import net.rhysholloway.game.world.entity.Mob;
import net.rhysholloway.game.world.entity.util.Direction;
import net.rhysholloway.game.world.level.GameLevel;

public class MobGreen extends Mob {
	
	public static AnimatedSprite 
	idle = new AnimatedSprite(new SpriteSheet("/assets/entities/player/green/idle.png", 24, 17), 30), 
	walk = new AnimatedSprite(new SpriteSheet("/assets/entities/player/green/walk.png", 24, 17), 10), 
	kick = new AnimatedSprite(new SpriteSheet("/assets/entities/player/green/kick.png", 24, 18), 8), 
	hurt = new AnimatedSprite(new SpriteSheet("/assets/entities/player/green/hurt.png", 24, 18), 5), 
	run = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/run.png", 24, 15), 2);

	private boolean showHitbox = false;

	public MobGreen(GameLevel level, int x, int y, boolean hostile) {
		super(level, idle.get(), Hitbox.create(x, y, 8, 15, 8, 2), 1, hostile, true);
		spawn();
	}

	private int movement = 1;

	public void update() {
		if (alive) {

			super.update();

			if (hitbox.rightCollision(xO + 2, yO) || hitbox.leftCollision(xO - 2, yO))
				movement = -movement;

			xO -= movement;

			physics();

		}

	}

	public void render() {
		if (alive) {

			// Kick > Crouch/Sprint > Walk > Idle

			if (kicking)
				sprite = kick.get();
			else if (running)
				sprite = run.get();
			else if (walking || jumping)
				sprite = walk.get();
			else
				sprite = idle.get();

			GameRenderer.render(hitbox.getSpriteX() - level.focus.getX(), hitbox.getSpriteY() - level.focus.getY(), this.sprite, horizontalDir != Direction.RIGHT);
			if (showHitbox)
				hitbox.renderHitbox();
		}
	}

	@Override
	protected void updateSprite() {
		if (kicking)
			kick.update();
		else if (running)
			run.update();
		else if (walking || jumping)
			walk.update();
		else
			idle.update();
		
	}

}
