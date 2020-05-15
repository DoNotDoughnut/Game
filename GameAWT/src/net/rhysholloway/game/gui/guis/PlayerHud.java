package net.rhysholloway.game.gui.guis;

import net.rhysholloway.game.graphics.GameRenderer;
import net.rhysholloway.game.graphics.sprite.Sprite;
import net.rhysholloway.game.graphics.sprite.SpriteSheet;
import net.rhysholloway.game.gui.GUIManager;
import net.rhysholloway.game.gui.GUIPanel;

public class PlayerHud extends GUIManager {

	private HealthHud healthHud;
	
	public PlayerHud() {
		super();
		healthHud = new HealthHud(4, 4, new Sprite("/assets/gui/game/hud/heartContainer.png"));
		add(healthHud);
	}
	
	public void setHealth(int i) {
		healthHud.health = i;
	}

}

class HealthHud extends GUIPanel {

	int health = 3;
	private static SpriteSheet hearts = new SpriteSheet("/assets/gui/game/hud/hearts.png", 16);
	private static Sprite fullHeart = new Sprite(hearts, 0, 0), emptyHeart = new Sprite(hearts, 1, 0);
	
	public HealthHud(int x, int y, Sprite sprite) {
		super(x, y, sprite.width, sprite.height, sprite);
	}
	
	public void render() {
		super.render();
		
		for(int i = 0; i < 3; i++) {
			if(i < health) {
				GameRenderer.render(7 + 16 * i, 8, fullHeart, false);
			} else
				GameRenderer.render(7 + 16 * i, 8, emptyHeart, false);
		}
	}
	
}