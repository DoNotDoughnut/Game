package net.rhys.game.objects.gui.components;

import net.rhys.game.objects.gui.GUIComponent;
import net.rhys.gameengine.EEngine;
import net.rhys.gameengine.rendering.texture.ETexture;

public class GUIButton extends GUIComponent {

	protected EEngine engine;
	protected boolean holdingClick = false;//, found = false;
	protected int origX = -1, origY = -1;
	
	public GUIButton(EEngine engine, ETexture texture, int x, int y, int width, int height) {
		super(texture, x, y, width, height);
		this.engine = engine;
	}
	
	public void update() {
		
	}
	
	public void onClick() {
		
	}

	public boolean clicked() {
		if(!holdingClick&&engine.mouseInput.clicking) {
			holdingClick = true;
			
			if(x + width >= engine.mouseInput.getX() && 
					   x         <= engine.mouseInput.getX() && 
					   y + width >= engine.mouseInput.getY() && 
					   y         <= engine.mouseInput.getY() ) {
					return true;
			}
			
		} else if(!engine.mouseInput.clicking)
			holdingClick = false;
		return false;
	}
}
