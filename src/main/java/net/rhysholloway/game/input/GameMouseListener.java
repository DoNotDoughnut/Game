package net.rhysholloway.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import net.rhysholloway.game.Game;

public class GameMouseListener implements MouseListener, MouseMotionListener {

	public static int clickX = -1, clickY = -1, releaseX = -1, releaseY = -1, mouseX = -1, mouseY = -1;
	public static boolean clicking = false;

	public static int getX() {
		return mouseX;
	}

	public static int getY() {
		return mouseY;
	}

	public void mouseDragged(MouseEvent e) { // Pretty much "while(clicking)"

	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX() / Game.scale;
		mouseY = e.getY() / Game.scale;
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		clickX = e.getX() / Game.scale;
		clickY = e.getY() / Game.scale;
		clicking = true;
	}

	public void mouseReleased(MouseEvent e) {
		releaseX = e.getX() / Game.scale;
		releaseY = e.getY() / Game.scale;
		clicking = false;
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

}
