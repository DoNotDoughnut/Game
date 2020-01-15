package rhys.game.objects;

import java.util.Random;
import rhys.game.main.GameRenderer;

public abstract class Entity {
	
	public int x, y;
	private boolean removed = false;
	protected GameLevel level;
	protected final Random random = new Random();
	
	public void update() {
		
	}
	
	public void render(GameRenderer gg) {
		
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}

	public void init(GameLevel level) {
		this.level=level;
	}
}
