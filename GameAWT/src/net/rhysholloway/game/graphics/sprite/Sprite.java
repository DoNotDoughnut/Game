package net.rhysholloway.game.graphics.sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Sprite {

	public final int width, height;
	public int[] pixels;

	public static final int defaultSize = 16;

	public static Sprite boundSprite = new Sprite(0x45283C);
	public static Sprite voidSprite = new Sprite(0x000000);

	public Sprite(String path) {
		this(loadImage(path));

	}

	private static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(Sprite.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Sprite(BufferedImage image) {
		width = image.getWidth();
		height = image.getHeight();
		pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
		image.flush();
	}

	public Sprite(SpriteSheet sheet, int x, int y) {
		this.width = sheet.spriteWidth;
		this.height = sheet.spriteHeight;
		pixels = new int[width * height];
		load(sheet, x, y);
	}

	private void load(SpriteSheet sheet, int inX, int inY) {
		// System.out.println(sheet.path+" ### "+width+", "+height);
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				pixels[x + y * width] = sheet.pixels[(x + inX * width) + (y + inY * height) * sheet.width];
	}

	public Sprite(int color, int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		Arrays.fill(pixels, color);
	}

	public Sprite(int color) {
		this(color, defaultSize, defaultSize);
	}

}
