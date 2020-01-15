package rhys.game.objects.entity;

import java.util.Random;

import rhys.game.main.GameRenderer;
import rhys.game.objects.level.GameLevel;

public abstract class Entity {
	
	public boolean alive = false;
	protected GameLevel level;
	protected final Random random = new Random();
	//public static ArrayList<Entity> aliveEntities = new ArrayList<>();
	
	public Entity() {
		//entities.add(this);
	}
	
	public void update() {
		
	}
	
	public void render(GameRenderer graphics) {
		
	}
	
	public void spawn() {
		//aliveEntities.add(this);
		alive = true;
	}
	
	public void despawn() {
		//aliveEntities.remove(this);
		alive = false;
	}

	public void init(GameLevel level) {
		this.level=level;
	}

	/*public static void updateEntities() {
		for(Entity entity : aliveEntities)
			entity.update();
	}*/
}
