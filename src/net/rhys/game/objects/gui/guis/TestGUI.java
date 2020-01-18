package net.rhys.game.objects.gui.guis;

import net.rhys.game.objects.entity.Player;
import net.rhys.game.objects.gui.GUIPanel;
import net.rhys.game.objects.gui.components.GUILabel;
import net.rhys.game.objects.gui.components.GUITitleBar;
import net.rhys.gameengine.EEngine;
import net.rhys.gameengine.render.EFonts;
import net.rhys.gameengine.texture.ETexture;
import net.rhys.gameengine.texture.ETextureSheet;

public class TestGUI extends GUIPanel {

	private GUILabel hello1, hello2, playerXL, playerYL;
	private GUITitleBar titleBar;
	private Player player;

	public TestGUI(EEngine engine, Player player) {
		super(0, 0, 100, 100, new ETexture(new ETextureSheet(EEngine.resources + "spritesheets/testgui.png", 1, 1, 100), 0, 0, 100, 1), "Test Panel :)");
		
		this.player=player;
		
		titleBar = new GUITitleBar(this, EFonts.guiFont, 0x3F3F3F, engine.mouseInput, engine.graphics);
		hello1 = new GUILabel(5, 18, engine.graphics.scale, EFonts.guiFont, 0x000000, "Hello!! I am a");
		hello2 = new GUILabel(5, 28, engine.graphics.scale, EFonts.dialogueFont, 0x000000, "test GUI label.");
		playerXL = new GUILabel(5, 40, engine.graphics.scale, EFonts.guiFont, 0x000000, "Player X: ");
		playerYL = new GUILabel(5, 56, engine.graphics.scale, EFonts.guiFont, 0x000000, "Player Y: ");
		
		
		add(titleBar);

		add(hello1);
		add(hello2);

		add(playerXL);
		add(playerYL);
	}

	@Override
	public void update() {
		super.update();
		playerXL.text = "Player X: " + player.hitbox.x;
		playerYL.text = "Player Y: " + player.hitbox.y;
	}

}
