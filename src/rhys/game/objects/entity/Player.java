package rhys.game.objects.entity;

import rhys.game.input.GameKeyListener;
import rhys.game.input.GameMouseListener;
import rhys.game.objects.gui.GUIManager;

public abstract class Player extends Mob {
	
	public int userID;
	protected GameKeyListener keyInput;
	protected GameMouseListener mouseInput;
	protected GUIManager gui;
	
	public Player(GameKeyListener keyInput, GameMouseListener mouseInput, GUIManager gui) {
		super();
		this.mouseInput = mouseInput;
		this.keyInput = keyInput;
		this.gui = gui;
	}
	
	public boolean hasCollision() {
		return true;
	}

}
