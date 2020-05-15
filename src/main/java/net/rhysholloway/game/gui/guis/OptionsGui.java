package net.rhysholloway.game.gui.guis;

import net.rhysholloway.game.graphics.sprite.Sprite;
import net.rhysholloway.game.gui.guis.GameMenu.MenuPanel;

public class OptionsGui extends MenuPanel {

	public OptionsGui(int x, int y) {
		super(new Sprite("/assets/gui/game/menu/options/panel.png"), x, y);
	}

}
