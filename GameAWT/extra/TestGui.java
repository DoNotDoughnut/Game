package rhys.game.objects.gui.guis;

import java.awt.Color;
import java.awt.Font;

import rhys.game.main.GameRenderer;
import rhys.game.objects.gui.GUIPanel;
import rhys.game.objects.gui.components.GUILabel;
import rhys.game.objects.gui.components.GUITitleBar;
import rhys.game.objects.sprite.Sprite;

@Deprecated
public class TestGui extends GUIPanel {
	
	private GUILabel playerXL, playerYL;
	
	public TestGui() {
		super(0, 0, 100, 100, new Sprite("/rhys/game/resources/spritesheets/testgui.png"));

		// Initialize and add components to the panel.

		Font guiFont = new Font("TimesRoman", Font.BOLD, 12);
		Font dialogueFont = new Font("TimesRoman", Font.BOLD, 18);
		
		add(new GUITitleBar(this, 0x3F3F3F, "Test Panel :)"));

		add(playerXL = new GUILabel(5 * GameRenderer.scale, 40 * GameRenderer.scale, guiFont, Color.black, "Player X: "));
		add(playerYL = new GUILabel(5 * GameRenderer.scale, 56 * GameRenderer.scale, guiFont, Color.black, "Player Y: "));

		add(new GUILabel(5 * GameRenderer.scale, 18 * GameRenderer.scale, guiFont, Color.black, "Hello!! I am a"));
		add(new GUILabel(5 * GameRenderer.scale, 28 * GameRenderer.scale, dialogueFont, Color.black, "test GUI label."));
	}

	public void updateCoords(int x, int y) {
		playerXL.text = "Player X: " + x;
		playerYL.text = "Player Y: " + y;
	}
	
}
