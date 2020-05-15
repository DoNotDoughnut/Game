package net.rhysholloway.game.graphics.sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

	public final int width, height, spriteWidth, spriteHeight;
	public int[] pixels;

	public SpriteSheet(String path, int spriteWidth, int spriteHeight) {
		BufferedImage image = load(path);
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		pixels = new int[this.width * this.height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
		image.flush();
	}

	private BufferedImage load(String path) {
		try {
			return ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public SpriteSheet(String path, int spriteSize) {
		this(path, spriteSize, spriteSize);
	}

}
