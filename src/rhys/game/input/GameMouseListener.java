package rhys.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import rhys.game.main.Game;
import rhys.game.main.GameRenderer;

public class GameMouseListener implements MouseListener, MouseMotionListener {
	
	public GameRenderer graphics;
	
	public int clickX = -1, clickY = -1, releaseX = -1, releaseY = -1, mouseX = -1, mouseY = -1;
	public boolean clicking = false;
	
	public GameMouseListener(GameRenderer graphics) {
		this.graphics=graphics;
	}
	
	public int getX() {
		return mouseX/Game.scale;
	}
	
	public int getY() {
		return mouseY/Game.scale;
	}
	
	public void mouseDragged(MouseEvent e) { //Pretty much "while(clicking)"
		
	}

	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	
	public void mouseClicked(MouseEvent e) {
		
	}

	
	public void mousePressed(MouseEvent e) {
		clickX = e.getX();
		clickY = e.getY();
		clicking = true;
	}

	
	public void mouseReleased(MouseEvent e) {
		releaseX = e.getX();
		releaseY = e.getY();
		clicking = false;
	}

	
	public void mouseEntered(MouseEvent e) {
		
	}

	
	public void mouseExited(MouseEvent e) {
		
	}

}
