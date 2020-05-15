package net.rhysholloway.game.world.entity;

import java.util.Random;

public abstract class Entity {
	
	public boolean alive = false;
	protected final Random random = new Random();
	
	public Entity() {
		
	}
	
	public void update() {
		
	}
	
	public void render() {
		
	}
	
	public void spawn() {
		//entities.add(this);
		alive = true;
	}
	
	public void despawn() {
		//entities.remove(this);
		alive = false;
	}
}
