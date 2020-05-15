package net.rhysholloway.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeSet;

public class GameKeyListener implements KeyListener {

	private static TreeSet<Integer> pressedKeys = new TreeSet<Integer>();

	public static boolean up() {
		return pressedKeys.contains(KeyEvent.VK_UP) || pressedKeys.contains(KeyEvent.VK_W) || pressedKeys.contains(KeyEvent.VK_SPACE);
	}
	
	public static boolean down () {
		return pressedKeys.contains(KeyEvent.VK_DOWN) || pressedKeys.contains(KeyEvent.VK_S);
	}
	
	public static boolean left() {
		return pressedKeys.contains(KeyEvent.VK_LEFT) || pressedKeys.contains(KeyEvent.VK_A);
	}
	
	public static boolean right() {
		return pressedKeys.contains(KeyEvent.VK_RIGHT) || pressedKeys.contains(KeyEvent.VK_D);
	}
	
	public static boolean menu() {
		return pressedKeys.contains(KeyEvent.VK_ESCAPE);
	}
	
	public static boolean sprint() {
		return pressedKeys.contains(KeyEvent.VK_CONTROL);
	}
	
	public void keyPressed(KeyEvent e) {
		pressedKeys.add(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		pressedKeys.remove(e.getKeyCode());
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public static boolean once(int keyCode) {
		if(pressedKeys.contains(keyCode)) {
			pressedKeys.remove(keyCode);
			return true;
		} else
		return false;
	}
}
