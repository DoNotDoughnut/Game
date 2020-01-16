package rhys.game.objects.gui.components;

import java.awt.Color;

import rhys.game.input.GameMouseListener;
import rhys.game.main.GameRenderer;
import rhys.game.objects.entity.entities.GameText;
import rhys.game.objects.gui.GUIComponent;
import rhys.game.objects.gui.GUIPanel;
import rhys.game.objects.sprite.Sprite;
import rhys.game.objects.sprite.SpriteSheet;

public class GUITitleBar extends GUIComponent {

	protected GUILabel windowLabel;
	protected GUIButton moveButton, closeButton;
	private GUIPanel panel;
	private GameRenderer graphics;
	
	protected static int barHeight = 8, buttonSize = 6;
	
	private SpriteSheet buttons = new SpriteSheet("/rhys/game/resources/spritesheets/buttons.png", 2, 1, 6);
	private Sprite closeSprite = new Sprite(buttons, 1, 0, 6, 1);
	private Sprite moveSprite = new Sprite(buttons, 0, 0, 6, 1);
	
	public GUITitleBar(GUIPanel panel, GameMouseListener mouseInput, GameRenderer graphics, int color) {
		super(new Sprite(color, panel.width, barHeight), 0, 0, panel.width, barHeight);
		this.panel=panel;
		this.graphics=graphics;
		windowLabel = new GUILabel(x+2, y+13, GameText.guiFont, Color.black, panel.name, true);
		moveButton = new MoveButton(this, mouseInput, graphics, moveSprite, x, width, y);
		closeButton = new CloseButton(panel, mouseInput, graphics, closeSprite, x, width, y);
	}
	
	protected void move(int newX, int newY) {
		
		panel.x += newX;
		panel.y += newY;
		
		windowLabel.x += newX*graphics.scale;
		windowLabel.y += newY*graphics.scale;
		
		closeButton.x += newX;
		closeButton.y += newY;
		
		for(GUIComponent component : panel.components) {
			component.x += newX;
			component.y += newY;
		}
		
		panel.updateList(panel.components);
	}
	
	
	public void update() {
		windowLabel.update();
		moveButton.update();
		closeButton.update();
	}

	public void render(GameRenderer graphics) {
		
		super.render(graphics);//Render background
		
		//Text is rendered separately
		
		//Render buttons
		
		moveButton.render(graphics);
		closeButton.render(graphics);
	}
	
	public void spawn() {
		alive = true;
		x = startX;
		y = startY;
		windowLabel.spawn();
		moveButton.spawn();
		closeButton.spawn();
	}
	
	public void despawn() {
		alive = false;
		windowLabel.despawn();
		moveButton.despawn();
		closeButton.despawn();
	}
	
}

class CloseButton extends GUIButton {
	
	private GUIPanel panel;
	
	public CloseButton(GUIPanel panel, GameMouseListener mouseInput, GameRenderer graphics, Sprite sprite, int x, int width, int y) {
		super(mouseInput, graphics, sprite, (x+width)-1*(2+GUITitleBar.buttonSize), y+1, 6, 6);
		this.panel = panel;
	}
	
	public void update() {
		if(clicked()) {
			panel.despawn();
		}
	}
}

class MoveButton extends GUIButton {

	private int origX = -1, origY = -1, newX, newY;
	private boolean inAction = false;
	private GUITitleBar titleBar;

	public MoveButton(GUITitleBar titleBar, GameMouseListener mouseInput, GameRenderer graphics, Sprite sprite, int x, int width, int y) {
		super(mouseInput, graphics, sprite, (x+width)-2*(2+GUITitleBar.buttonSize), y+1, 6, 6);
		this.titleBar=titleBar;

	}

	public void update() {
		if(inAction&&mouseInput.clicking) {

			newX = mouseInput.getX() - origX;
			newY = mouseInput.getY() - origY;
			
			titleBar.move(newX, newY);
			
			x += newX;
			y += newY;
			
			origX = newX;
			origY = newY;
			
			inAction = false;
		}
		if(!inAction&&clicked()) {
			//if(origX == -1) {
				origX = mouseInput.getX();//titleBar.x;
				origY = mouseInput.getY(); //titleBar.y;
			//}
			mouseInput.clicking = false;
			
			inAction = true;
		}
	}
	
}

