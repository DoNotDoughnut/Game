package rhys.game.objects.gui.components;

import rhys.game.input.GameMouseListener;
import rhys.game.main.GameRenderer;
import rhys.game.objects.gui.GUIComponent;
import rhys.game.objects.sprite.Sprite;

public class GUIButton extends GUIComponent {

	protected GameMouseListener mouseInput;
	protected GameRenderer graphics;
	private boolean holdingClick = false;//, found = false;
	protected int origX = -1, origY = -1;
	
	public GUIButton(GameMouseListener mouseInput, GameRenderer graphics, Sprite sprite, int x, int y, int width, int height) {
		super(sprite, x, y, width, height);
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
	
	public void spawn() {
		x = startX;
		y = startY;
		alive = true;
	}

}