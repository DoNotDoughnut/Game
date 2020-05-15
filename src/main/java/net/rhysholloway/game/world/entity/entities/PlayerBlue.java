package net.rhysholloway.game.world.entity.entities;

import net.rhysholloway.game.graphics.GameRenderer;
import net.rhysholloway.game.graphics.sprite.AnimatedSprite;
import net.rhysholloway.game.graphics.sprite.SpriteSheet;
import net.rhysholloway.game.input.GameKeyListener;
import net.rhysholloway.game.world.entity.Hitbox;
import net.rhysholloway.game.world.entity.Player;
import net.rhysholloway.game.world.entity.util.Direction;
import net.rhysholloway.game.world.level.GameLevel;

public class PlayerBlue extends Player {

	protected AnimatedSprite currentSprite;

	public static AnimatedSprite 
	idle_side = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/idle_side.png", 32), 30),
	walk_side = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/walk.png", 32), 5),
	kick_side = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/kick.png", 32), 8),
	hurt_side = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/hurt.png", 32), 5), 
	run_side = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/run.png", 32), 2),

			idle_front = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/idle_front.png", 32), 30), walk_front = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/walk.png", 32), 5), kick_front = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/kick.png", 32), 8), hurt_front = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/hurt.png", 32), 5), run_front = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/run.png", 32), 2),

			idle_back = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/idle_front.png", 32), 30), walk_back = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/walk.png", 32), 5), kick_back = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/kick.png", 32), 8), hurt_back = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/hurt.png", 32), 5), run_back = new AnimatedSprite(new SpriteSheet("/assets/entities/player/blue/run.png", 32), 2);

	private static int pWidth = 7, pHeight = 15, pWidthOffset = 13, pHeightOffset = 10;

	public PlayerBlue(GameLevel level, int x, int y) {
		super(level, idle_side.get(), Hitbox.create(x, y - (idle_side.get().height - pHeightOffset - pHeight), pWidth, pHeight, pWidthOffset, pHeightOffset), 2);
		spawn();
		currentSprite = idle_side;
	}

	private boolean showHitbox = false;

	public void update() {
		if (alive) {

			super.update();

			input();

			physics();

			currentSprite.update();

		}
	}

	protected void input() {
		running = false;

		if (GameKeyListener.sprint() && walking) { // Sprint mechanics
			running = true;
			speed *= 2;
		}

		if (GameKeyListener.up()) { // Jump
			jump();
		}

		if (GameKeyListener.down()) { // Drop through platform

		}

		if (GameKeyListener.left()) { // Move left
			xO -= speed;
		}
		if (GameKeyListener.right()) {// Move right
				xO += speed;
		}

	}

	public void render() {
		if (alive) {

			// Kick > Crouch/Sprint > Walk > Idle

			sprite = currentSprite.get();

			GameRenderer.render(hitbox.getSpriteX() - level.focus.getX(), hitbox.getSpriteY() - level.focus.getY(), sprite, horizontalDir != Direction.RIGHT);
			if (showHitbox)
				hitbox.renderHitbox();

			super.render();
		}
	}

	@Override
	protected void updateSprite() {
		if (kicking)
			currentSprite = kick_side;
		else if (running)
			currentSprite = run_side;
		else if (walking || jumping)
			currentSprite = walk_side;
		else
			currentSprite = idle_side;

	}

}