package rhys.game.objects;

public abstract class Mob extends Entity {

	public Sprite sprite;
	public Hitbox hitbox;
	protected int dir = 0;
	protected boolean moving = false;
	
	public void move(int xO, int yO) {
		
		if(xO != 0 && yO != 0) {
			move(xO, 0);
			move(0, yO);
			return;
		}
		
		if(xO>0)dir=0; //Right
		if(xO<0)dir=1; //Left
		//if(yO>0)dir=2; //Up
		//if(yO<0)dir=0; //Down
		if(hasCollision()&&!hitbox.collisionAny(xO, yO)) {
			hitbox.x += xO;
			hitbox.y += yO;
		}
	}
	
	public void update() {}
	
	public boolean hasCollision() {
		return false;
	}
	
	public void render() {}
	
}
