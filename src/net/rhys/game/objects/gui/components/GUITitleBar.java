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

	private GUILabel windowLabel;
	private GUIButton moveButton, closeButton;
	private GUIPanel panel;
	
	protected static int barHeight = 8, buttonSize = 6;
	
	private SpriteSheet buttons = new SpriteSheet("/rhys/game/resources/spritesheets/buttons.png", 2, 1, 6);
	private Sprite closeSprite = new Sprite(buttons, 1, 0, 6, 1);
	private Sprite moveSprite = new Sprite(buttons, 0, 0, 6, 1);
	
	public GUITitleBar(GUIPanel panel, GameMouseListener mouseInput, GameRenderer graphics, int color) {
		super(new Sprite(color, panel.width, barHeight), 0, 0, panel.width, barHeight);
		this.panel=panel;
		windowLabel = new GUILabel(x+2, y+13, GameText.guiFont, Color.white, panel.name);
		moveButton = new MoveButton(this, mouseInput, graphics, moveSprite, x, width, y);
		closeButton = new CloseButton(this, mouseInput, graphics, closeSprite, x, width, y);
	}
	
	protected void closePanel() {
		panel.despawn();
	}
	
	protected void movePanelPos(int newX, int newY) {
		panel.move(newX, newY);
		
		windowLabel.move(newX, newY);
		moveButton.move(newX, newY);
		closeButton.move(newX, newY);
		
	}
	
	protected void resetPanelPos() {
		panel.resetPos();
		
		windowLabel.resetPos();
		moveButton.resetPos();
		closeButton.resetPos();
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
	
	private GUITitleBar titleBar;
	
	public CloseButton(GUITitleBar titleBar, GameMouseListener mouseInput, GameRenderer graphics, Sprite sprite, int x, int width, int y) {
		super(mouseInput, graphics, sprite, (x+width)-1*(2+GUITitleBar.buttonSize), y+1, 6, 6);
		this.titleBar = titleBar;
	}
	
	public void update() {
		if(clicked()) {
			titleBar.closePanel();
		}
	}
}

class MoveButton extends GUIButton {

	private int origX = -1, origY = -1;
	private boolean inAction = false;
	private GUITitleBar titleBar;

	public MoveButton(GUITitleBar titleBar, GameMouseListener mouseInput, GameRenderer graphics, Sprite sprite, int x, int width, int y) {
		super(mouseInput, graphics, sprite, (x+width)-2*(2+GUITitleBar.buttonSize), y+1, 6, 6);
		this.titleBar=titleBar;

	}

	public void update() {
		if(inAction&&clicked()) { //Double click button action
			
			titleBar.resetPanelPos();
			
			setCoords(0, 0, false);
			
		}
		
		if(inAction&&mouseInput.clicking) {//Click somewhere other than button action
			
			int newX = mouseInput.getX() - origX;
			int newY = mouseInput.getY() - origY;
			
			titleBar.movePanelPos(newX, newY);
			
			setCoords(newX, newY, false);
			
		}
		
		if(!inAction&&clicked()) //First click of button
			setCoords(mouseInput.getX(), mouseInput.getY(), true);
	}
	
	private void setCoords(int newX, int newY, boolean inAction) {
		origX=newX;
		origY=newY;
		this.inAction=inAction;
		mouseInput.clicking = false;
	}
		
	
}

