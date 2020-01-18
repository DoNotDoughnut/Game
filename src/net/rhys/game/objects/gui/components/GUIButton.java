package net.rhys.game.objects.gui.components;

import net.rhys.game.objects.gui.GUIComponent;
import net.rhys.gameengine.input.EMouseInput;
import net.rhys.gameengine.render.ERenderer;
import net.rhys.gameengine.texture.ETexture;

public class GUIButton extends GUIComponent {

	protected EMouseInput mouseInput;
	protected ERenderer graphics;
	private boolean holdingClick = false;//, found = false;
	protected int origX = -1, origY = -1;
	
	public GUIButton(EMouseInput mouseInput, ERenderer graphics, ETexture texture, int x, int y, int width, int height) {
		super(texture, x, y, width, height);
		this.graphics=graphics;
		this.mouseInput=mouseInput;
	}
	
	public void update() {
		
	}
	
	public void onClick() {
		
	}

	public boolean clicked() {
		if(!holdingClick&&mouseInput.clicking) {
			holdingClick = true;
			
			//System.out.println("click");
			
			//System.out.println("Button coordinates: X: "+x+" - "+(x+width)+", Y: "+y+" - "+(y+width));
			//System.out.println("Mouse coordiantes: X: "+mouseInput.getX()+", Y: "+mouseInput.getY());
			
			if(x + width >= mouseInput.getX() && 
			   x         <= mouseInput.getX() && 
			   y + width >= mouseInput.getY() && 
			   y         <= mouseInput.getY() ) {
				//System.out.println("true");
				return true;
			}
		} else if(!mouseInput.clicking)
			holdingClick = false;
		return false;
	}
}
