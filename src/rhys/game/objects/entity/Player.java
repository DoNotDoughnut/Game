package rhys.game.objects.entity;

public abstract class Player extends Mob {
	
	public int userID;
	
	public Player() {
		super();
	}
	
	public boolean hasCollision() {
		return true;
	}

}
