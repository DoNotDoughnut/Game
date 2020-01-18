package net.rhys.game.objects.gui.components;

import java.awt.Font;

import net.rhys.game.objects.gui.GUIComponent;
import net.rhys.game.objects.gui.GUIPanel;
import net.rhys.gameengine.EEngine;
import net.rhys.gameengine.input.EMouseInput;
import net.rhys.gameengine.render.ERenderer;
import net.rhys.gameengine.texture.ETexture;
import net.rhys.gameengine.texture.ETextureSheet;

public class GUITitleBar extends GUIComponent {

	private GUILabel windowLabel;
	private GUIButton moveButton, closeButton;
	private GUIPanel panel;
	
	protected static int barHeight = 9, buttonSize = 6;
	
	private ETextureSheet buttons = new ETextureSheet(EEngine.resources+"spritesheets/buttons.png", 2, 1, 6);
	private ETexture closeTexture = new ETexture(buttons, 1, 0, 6, 1);
	private ETexture moveTexture = new ETexture(buttons, 0, 0, 6, 1);
	
	public GUITitleBar(GUIPanel panel, Font font, int color, EMouseInput mouseInput, ERenderer graphics) {
		super(new ETexture(color, panel.width, barHeight), 0, 0, panel.width, barHeight);
		this.panel=panel;
		windowLabel = new GUILabel(x+2, y+7, graphics.scale, font, 0xFFFFFF, panel.name);
		moveButton = new MoveButton(this, mouseInput, graphics, moveTexture, x, width, y);
		closeButton = new CloseButton(this, mouseInput, graphics, closeTexture, x, width, y);
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

	public void render(ERenderer graphics) {
		super.render(graphics);
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
	
	public CloseButton(GUITitleBar titleBar, EMouseInput mouseInput, ERenderer graphics, ETexture sprite, int x, int width, int y) {
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

	public MoveButton(GUITitleBar titleBar, EMouseInput mouseInput, ERenderer graphics, ETexture sprite, int x, int width, int y) {
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

