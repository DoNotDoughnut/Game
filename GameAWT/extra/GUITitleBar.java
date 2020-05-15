package rhys.game.objects.gui.components;

import java.awt.Color;
import java.awt.Font;

import rhys.game.input.GameMouseListener;
import rhys.game.main.GameRenderer;
import rhys.game.objects.gui.GUIComponent;
import rhys.game.objects.gui.GUIPanel;
import rhys.game.objects.sprite.Sprite;
import rhys.game.objects.sprite.SpriteSheet;

@Deprecated
public class GUITitleBar extends GUIComponent {

	private GUILabel windowLabel;
	private GUIButton moveButton, closeButton;
	private GUIPanel panel;
	
	protected static int barHeight = 8, buttonSize = 6;
	
	private SpriteSheet buttons = new SpriteSheet("/rhys/game/resources/spritesheets/buttons.png", 6);
	private Sprite closeSprite = new Sprite(buttons, 1, 0);
	private Sprite moveSprite = new Sprite(buttons, 0, 0);
	
	public GUITitleBar(GUIPanel panel, int color, String name) {
		super(new Sprite(color, panel.width, barHeight), 0, 0, panel.width, barHeight);
		this.panel=panel;
		windowLabel = new GUILabel(x+2, y+13, new Font("TimesRoman", Font.PLAIN, 12), Color.white, name);
		moveButton = new MoveButton(this, moveSprite, x, width, y);
		closeButton = new CloseButton(this, closeSprite, x, width, y);
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

	public void render() {
		
		GameRenderer.render(x,y, sprite, false); //Render background
		
		//Text is rendered separately
		
		//Render buttons
		
		moveButton.render();
		closeButton.render();
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
	
	public CloseButton(GUITitleBar titleBar, Sprite sprite, int x, int width, int y) {
		super(sprite, (x+width)-1*(2+GUITitleBar.buttonSize), y+1);
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

	public MoveButton(GUITitleBar titleBar, Sprite sprite, int x, int width, int y) {
		super(sprite, (x+width)-2*(2+GUITitleBar.buttonSize), y+1);
		this.titleBar=titleBar;

	}

	public void update() {
		if(inAction&&clicked()) { //Double click button action
			
			titleBar.resetPanelPos();
			
			setCoords(0, 0, false);
			
		}
		
		if(inAction&&GameMouseListener.clicking) {//Click somewhere other than button action
			
			int newX = GameMouseListener.getX() - origX;
			int newY = GameMouseListener.getY() - origY;
			
			titleBar.movePanelPos(newX, newY);
			
			setCoords(newX, newY, false);
			
		}
		
		if(!inAction&&clicked()) //First click of button
			setCoords(GameMouseListener.getX(), GameMouseListener.getY(), true);
	}
	
	private void setCoords(int newX, int newY, boolean inAction) {
		origX=newX;
		origY=newY;
		this.inAction=inAction;
		GameMouseListener.clicking = false;
	}
		
	
}

