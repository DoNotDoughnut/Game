package rhys.game.objects.entities;

import rhys.game.objects.Mob;

public abstract class Player extends Mob {
	
	public int userID;
	
	public boolean hasCollision() {
		return true;
	}

}
