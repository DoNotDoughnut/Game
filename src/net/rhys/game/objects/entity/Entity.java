package net.rhys.game.objects.entity;

import java.util.Random;

import net.rhys.gameengine.level.ELevel;
import net.rhys.gameengine.rendering.ERenderer;

public abstract class Entity {
	
	public boolean alive = false;
	protected ELevel level;
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

	public void init(ELevel level) {
		this.level=level;
	}
	
	//public static ArrayList<Entity> entities = new ArrayList<>();

	/*public static void updateEntities() {
		for(Entity entity : entities)
			entity.update();
	}*/
}
