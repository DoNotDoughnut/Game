package net.rhys.game.objects.entity;

import java.util.Random;

import net.rhys.game.objects.level.GameLevel;
import net.rhys.gameengine.render.ERenderer;

public abstract class Entity {
	
	public boolean alive = false;
	protected GameLevel level;
	protected final Random random = new Random();
	
	public Entity() {
		
	}
	
	public void update() {
		
	}
	
	public void render(ERenderer graphics) {
		
	}
	
	public void spawn() {
		//entities.add(this);
		alive = true;
	}
	
	public void despawn() {
		//entities.remove(this);
		alive = false;
	}

	public void init(GameLevel level) {
		this.level=level;
	}
	
	//public static ArrayList<Entity> entities = new ArrayList<>();

	/*public static void updateEntities() {
		for(Entity entity : entities)
			entity.update();
	}*/
}
