package rhys.game.objects.entity;

import rhys.game.objects.sprite.Sprite;

public abstract class Mob extends Entity {

	public Mob() {
		super();
	}

	public float speed, slow;
	public boolean movementLock = false;
	public Sprite sprite;
	public Hitbox hitbox;
	protected int dir = 0;
	protected boolean moving = false;
	
	public void move(int xO, int yO) {
		
		if((xO != 0 && yO != 0)&&!movementLock) {
			move(xO, 0);
			move(0, yO);
			return;
		}
		
		if(xO>0)dir=0; //Right
		if(xO<0)dir=1; //Left
		if(hasCollision()&&!hitbox.collisionAny(xO, yO)) {
			
			//Checks if the mob isn't able to move to the left/right because its pixels per move are too high, and if they are it moves them the max distance
			
			if(hitbox.leftCollision((int) (xO-speed), yO))
				while(!hitbox.leftCollision(xO-1, yO)) 
					xO--;
			
			if(hitbox.rightCollision((int) (xO+speed), yO))
				while(!hitbox.rightCollision(xO+1, yO))
					xO++;
			
			hitbox.x += xO;
			hitbox.y += yO;
		}
	}
	
	public boolean hasCollision() {
		return false;
	}
	
}
