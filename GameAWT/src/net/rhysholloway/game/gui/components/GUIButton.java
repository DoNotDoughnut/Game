package net.rhysholloway.game.gui.components;

import net.rhysholloway.game.graphics.GameRenderer;
import net.rhysholloway.game.graphics.sprite.Sprite;
import net.rhysholloway.game.gui.GUIComponent;
import net.rhysholloway.game.input.GameMouseListener;

public class GUIButton extends GUIComponent {

	private boolean holdingClick = false;// , found = false;
	protected int origX = -1, origY = -1;

	public GUIButton(Sprite sprite, int x, int y) {
		super(sprite, x, y, sprite.width, sprite.height);
	}

	public void update() {
		if (alive) {
			if (clicked())
				onClick();
		}
	}

	public void render() {
		if (alive) {
			GameRenderer.render(x, y, sprite, false);
		}
	}

	public void onClick() {

	}

	public boolean clicked() {

		if (!holdingClick && GameMouseListener.clicking) {
			
			holdingClick = true;

			if (	x + width >= GameMouseListener.getX() && x <= GameMouseListener.getX() && 
					y + height >= GameMouseListener.getY() && y <= GameMouseListener.getY())
				return true;

		} else if (!GameMouseListener.clicking)
			holdingClick = false;
		return false;
	}

	public void spawn() {
		x = startX;
		y = startY;
		alive = true;
	}

}
