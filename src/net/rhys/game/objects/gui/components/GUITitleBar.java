package net.rhys.game.objects.gui.components;

import java.awt.Font;

import net.rhys.game.objects.gui.GUIComponent;
import net.rhys.game.objects.gui.GUIPanel;
import net.rhys.gameengine.EEngine;
import net.rhys.gameengine.rendering.ERenderer;
import net.rhys.gameengine.rendering.texture.ETexture;
import net.rhys.gameengine.rendering.texture.ETextureSheet;

public class GUITitleBar extends GUIComponent {

	private GUILabel windowLabel;
	private GUIButton moveButton, closeButton;
	private GUIPanel panel;
	
	protected static int barHeight = 9, buttonSize = 6;
	
	private ETextureSheet buttons = new ETextureSheet(EEngine.resources+"spritesheets/buttons.png", 6, 6);
	private ETexture closeTexture = new ETexture(buttons, 1, 0);
	private ETexture moveTexture = new ETexture(buttons);
	
	public GUITitleBar(GUIPanel panel, Font font, int color, EEngine engine) {
		super(new ETexture(color, panel.width, barHeight), panel.x, panel.y, panel.width, barHeight);
		this.panel=panel;
		windowLabel = new GUILabel(panel.x+2, panel.y+7, engine.graphics.scale, font, 0xFFFFFF, panel.name);
		moveButton = new MoveButton(moveTexture, panel.x, panel.y, width, engine, this);
		closeButton = new CloseButton(closeTexture, panel.x, panel.y, width, engine, this);
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
	
	public CloseButton(ETexture texture, int x, int y, int size, EEngine engine, GUITitleBar titleBar) {
		super(engine, texture, (x+size)-1*(2+GUITitleBar.buttonSize), y+1, 6, 6);
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

	public MoveButton(ETexture texture, int x, int y, int size, EEngine engine, GUITitleBar titleBar) {
		super(engine, texture, (x+size)-2*(2+GUITitleBar.buttonSize), y+1, 6, 6);
		this.titleBar=titleBar;
	}

	public void update() {
		if(inAction&&clicked()) { //Double click button action
			
			titleBar.resetPanelPos();
			
			setCoords(0, 0, false);
			
		}
		
		if(inAction&&engine.mouseInput.clicking) {//Click somewhere other than button action
			
			int newX = engine.mouseInput.getX() - origX;
			int newY = engine.mouseInput.getY() - origY;
			
			titleBar.movePanelPos(newX, newY);
			
			setCoords(newX, newY, false);
			
		}
		
		if(!inAction&&clicked()) //First click of button
			setCoords(engine.mouseInput.getX(), engine.mouseInput.getY(), true);
	}
	
	private void setCoords(int newX, int newY, boolean inAction) {
		origX=newX;
		origY=newY;
		this.inAction=inAction;
		engine.mouseInput.clicking = false;
	}
		
	
}

