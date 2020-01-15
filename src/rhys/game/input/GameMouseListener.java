package rhys.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import rhys.game.main.Game;

public class GameMouseListener implements MouseListener, MouseMotionListener {

	private static int mouseX = -1, mouseY = -1, mouseB = -1;
	
	public static int getX() {
		return mouseX/Game.scale;
	}
	
	public static int getY() {
		return mouseY/Game.scale;
	}
	
	public static int getButton() {
		return mouseB;
	}
	
	public static void resetButton() {
		mouseB = -1;
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}

	
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	
	public void mouseClicked(MouseEvent e) {
		
	}

	
	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
	}

	
	public void mouseReleased(MouseEvent e) {
		mouseB = -1;
	}

	
	public void mouseEntered(MouseEvent e) {
		
	}

	
	public void mouseExited(MouseEvent e) {
		
	}

}
