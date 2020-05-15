package net.rhysholloway.game.world.entity;

import static net.rhysholloway.game.world.entity.util.Direction.*;

import net.rhysholloway.game.graphics.sprite.Sprite;
import net.rhysholloway.game.world.entity.util.Direction;
import net.rhysholloway.game.world.entity.util.HitboxSpecs;
import net.rhysholloway.game.world.level.GameLevel;

public abstract class Mob extends Entity {

	public Mob(GameLevel level, Sprite sprite, HitboxSpecs hb, int speed, boolean hostile, boolean collision) {
		super();
		this.level = level;
		this.sprite = sprite;
		this.hitbox = new Hitbox(this, hb.x, hb.y, hb.width, hb.height, hb.wOffset, hb.hOffset);
		this.speed = this.defSpeed = speed;
		this.hostile = hostile;
		this.collision = collision;
	}
	
	protected final int defSpeed;
	public final boolean hostile;
	
	public int health;
	protected float speed, vDelta = 0, gDelta = 0.25f;
	protected boolean onGround;
	protected int xO, yO;
	protected GameLevel level;
	public Sprite sprite;
	public Hitbox hitbox;
	protected Direction horizontalDir = RIGHT, verticalDir = DOWN;

	public boolean walking = false, jumping = false, falling = true, running = false, kicking = false, collision;

	public void update() {
		xO = 0;
		yO = 0;
		onGround = hitbox.bottomCollision(0, 1); // Checks if currently on the ground
		speed = defSpeed;
	}

	protected void physics() {
		// Movement commands below

		if (!onGround && !(jumping || falling)) { // If the player is floating make them fall
			falling = true;
			vDelta = 0;
		}

		if (jumping || falling) { // Jumping / Falling math

			yO += vDelta;
			if (vDelta < 4.5f)
				vDelta += gDelta;
			else
				vDelta = 4.5f;

			if (!onGround && hitbox.topCollision(xO, yO - 1) && !falling) { // If hit a block while jumping, start falling
				falling = true;
				vDelta = 0;
			}

			if ((jumping || falling) && hitbox.bottomCollision(xO, (int) (yO + vDelta))) { // If on ground stop fall
				while (!hitbox.bottomCollision(xO, yO + 1))
					yO++;

				jumping = false;
				falling = false;
			}

		}
		
		updateSprite();
		
		walking = (xO != 0); // Stop walking animation if not moving

		if (xO != 0 || yO != 0) // Move player if its values have changed
			move(xO, yO);

	}

	public void jump() {
		if (onGround && !(jumping || falling)) { // Start jump
			jumping = true;
			if (running)
				vDelta = -3.25f;
			else
				vDelta = -4.5f;
		}
	}
	
	public void damage() {
		health--;
	}
	
	public void heal() {
		health++;
	}
	
	public void kill() {
		
	}
	
	public void move(int xO, int yO) {
		
		if ((xO != 0 && yO != 0)) {
			move(xO, 0);
			move(0, yO);
			return;
		}

		if (xO > 0)
			horizontalDir = RIGHT; // Right
		if (xO < 0)
			horizontalDir = LEFT; // Left
		if(yO > 0)
			verticalDir = UP;
		if(yO < 0)
			verticalDir = DOWN;
		
		updateSprite();
		
		if (collision && !hitbox.collisionAny(xO, yO)) {
			
			// Checks if the mob isn't able to move to the left/right because its pixels per
			// move are too high, and if they are it moves them the max distance

			if (hitbox.leftCollision((int) (xO - speed), yO))
				while (!hitbox.leftCollision(xO - 1, yO))
					xO--;

			if (hitbox.rightCollision((int) (xO + speed), yO))
				while (!hitbox.rightCollision(xO + 1, yO))
					xO++;

			hitbox.x += xO;
			hitbox.y += yO;
		}
	}

	protected abstract void updateSprite();

}
