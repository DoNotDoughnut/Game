package net.rhys.game.objects.entity;

import net.rhys.game.objects.gui.GUIManager;
import net.rhys.gameengine.input.EKeyInput;
import net.rhys.gameengine.input.EMouseInput;

public abstract class Player extends Mob {
	
	public int userID;
	protected EKeyInput keyInput;
	protected EMouseInput mouseInput;
	protected GUIManager gui;
	
	public Player(EKeyInput keyInput, EMouseInput mouseInput, GUIManager gui) {
		super();
		this.mouseInput = mouseInput;
		this.keyInput = keyInput;
		this.gui = gui;
	}
	
	public boolean hasCollision() {
		return true;
	}

}
