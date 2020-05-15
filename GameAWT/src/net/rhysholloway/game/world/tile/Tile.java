package net.rhysholloway.game.world.tile;

import java.util.TreeMap;

import net.rhysholloway.game.graphics.GameRenderer;
import net.rhysholloway.game.graphics.sprite.Sprite;

public class Tile {

	public Sprite sprite;
	public final boolean solid;

	public static Tile boundTile = new Tile(Sprite.boundSprite, true, "bounds");
	public static Tile voidTile = new Tile(Sprite.voidSprite, false, "void");

	public static final int size = 16;

	public static TreeMap<String, Tile> tileSet;

	public Tile(Sprite sprite, boolean solid) {
		this.sprite = sprite;
		this.solid = solid;
	}

	public Tile(Sprite sprite, boolean solid, String name) {
		this(sprite, solid);
		if(tileSet==null)
			tileSet = new TreeMap<String, Tile>();
		if(tileSet.isEmpty()||!tileSet.containsKey(name))
		tileSet.put(name, this);
	}

	// Change to cycle through ones on screen only

	public void render(int x, int y) {
		GameRenderer.render(x, y, this.sprite, false);
	}

	public void onClick() {

	}

	public void onInteract() {

	}
}
