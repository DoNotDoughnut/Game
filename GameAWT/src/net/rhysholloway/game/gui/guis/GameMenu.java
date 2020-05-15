package net.rhysholloway.game.gui.guis;

import net.rhysholloway.game.Game;
import net.rhysholloway.game.graphics.sprite.Sprite;
import net.rhysholloway.game.gui.GUIPanel;
import net.rhysholloway.game.gui.components.GUIButton;

public class GameMenu extends GUIPanel {

	private static Sprite panelSprite = new Sprite("/assets/gui/game/menu/panel.png");

	static final int options = 2, buttonWidth = 80, buttonHeight = 20, buttonXPos = 4, buttonYPos = 4, panelWidth = 230, panelHeight = 176;

	private GUIButton exitButton;
	public int currentPanel = -1;

	private GUIButton[] buttons = new GUIButton[options];
	public GUIPanel[] panels = new GUIPanel[options];

	public GameMenu() {
		super(Game.canvasWidth / 2 - panelSprite.width / 2, Game.canvasHeight / 2 - panelSprite.height / 2, panelSprite.width, panelSprite.height, panelSprite);

		exitButton = new GUIButton(new Sprite("/assets/gui/game/menu/exit/button.png"), buttonXPos + x, y + buttonYPos + 150) {

			public void onClick() {
				Game.gsm.enable(0);
			}

		};

		add(exitButton);

		panels[0] = new OptionsGui(x, y);

		buttons[0] = new MenuButton(new Sprite("/assets/gui/game/menu/options/button.png"), x, y, 1) {

			public void onClick() {
				if(currentPanel != -1)
					panels[currentPanel].toggle();
				panels[0].toggle();
				currentPanel = 0;
			}

		};
		
		panels[1] = new MenuPanel(new PanelSprite(0x0000FF), x, y);

		buttons[1] = new MenuButton(new ButtonSprite(0xFFF500), x, y, 2) {

			public void onClick() {
				if(currentPanel != -1)
					panels[currentPanel].toggle();
				panels[1].toggle();
				currentPanel = 1;
			}

		};

		for (GUIButton button : buttons)
			add(button);
	}

	@Override
	public void update() {
		super.update();
	}
	
	static class MenuButton extends GUIButton {

		public MenuButton(Sprite sprite, int x, int y, int num) {
			super(sprite, x + GameMenu.buttonXPos, y + GameMenu.buttonYPos + (num - 1) * 20);
		}

	}

	static class ButtonSprite extends Sprite {

		public ButtonSprite(int color) {
			super(color, 80, 20);
		}

	}

	static class MenuPanel extends GUIPanel {

		public MenuPanel(Sprite sprite, int x, int y) {
			super(88 + x, 2 + y, GameMenu.panelWidth, GameMenu.panelHeight, sprite);
		}

	}

	static class PanelSprite extends Sprite {

		public PanelSprite(int color) {
			super(color, GameMenu.panelWidth, GameMenu.panelHeight);
		}

	}

}