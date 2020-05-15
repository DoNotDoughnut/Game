package net.rhysholloway.game.world.entity;

import net.rhysholloway.game.Game;
import net.rhysholloway.game.graphics.GameRenderer;
import net.rhysholloway.game.world.entity.util.HitboxSpecs;
import net.rhysholloway.game.world.tile.Tile;

public class Hitbox {

	public int x, y, width, height;
	private int wOffset, hOffset; // To map sprite on
	private Mob owner;

	public static boolean print = false;

	public Hitbox(Mob owner, int x, int y, int width, int height, int wOffset, int hOffset) {
		this.owner = owner;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.wOffset = wOffset;
		this.hOffset = hOffset;
	}

	// 0 = down, 1 = right, 2 = up, 3 = left

	public boolean[] sideCollision(int xO, int yO) { // tests if about to collide on each side
		boolean[] sides = new boolean[4];

		sides[0] = bottomCollision(xO, yO);
		sides[1] = rightCollision(xO, yO);
		sides[2] = topCollision(xO, yO);
		sides[3] = leftCollision(xO, yO);

		return sides;

	}

	public boolean bottomCollision(int xO, int yO) {
		return (owner.level.getTileFromCoordinates((x) / Tile.size, (y + yO + height) / Tile.size).solid || owner.level.getTileFromCoordinates((x + width) / Tile.size, (y + yO + height) / Tile.size).solid);
	}

	public boolean rightCollision(int xO, int yO) {
		return (owner.level.getTileFromCoordinates((x + width + xO) / Tile.size, (y) / Tile.size).solid || owner.level.getTileFromCoordinates((x + width + xO) / Tile.size, (y + height) / Tile.size).solid);
	}

	public boolean topCollision(int xO, int yO) {
		return (owner.level.getTileFromCoordinates((x) / Tile.size, (y + yO) / Tile.size).solid || owner.level.getTileFromCoordinates((x + width) / Tile.size, (y + yO) / Tile.size).solid);
	}

	public boolean leftCollision(int xO, int yO) {
		return (owner.level.getTileFromCoordinates((x + xO) / Tile.size, (y) / Tile.size).solid || owner.level.getTileFromCoordinates((x + xO) / Tile.size, (y + height) / Tile.size).solid);
	}

	public boolean collisionAny(int xO, int yO) {
		return bottomCollision(xO, yO) || rightCollision(xO, yO) || leftCollision(xO, yO) || topCollision(xO, yO);
	}

	public void renderHitbox() {
		for (int y = this.y; y <= this.y + height; y++) {
			for (int x = this.x; x <= this.x + width; x++) {

				int xx = x - owner.level.focus.getX(), yy = y - owner.level.focus.getY();

				if ((yy < 0 || yy >= Game.canvasHeight) || (xx < -width || xx >= Game.canvasWidth))
					break;

				GameRenderer.pixels[xx + yy * Game.canvasWidth] = 0xFF0000;
			}
		}
	}

	public int getSpriteX() {
		return x - wOffset + 1;
	}

	public int getSpriteY() {
		return y - hOffset + 1;
	}

	public static HitboxSpecs create(int x, int y, int pWidth, int pHeight, int pWidthOffset, int pHeightOffset) {
		return new HitboxSpecs(x, y, pWidth, pHeight, pWidthOffset, pHeightOffset);
	}

}
