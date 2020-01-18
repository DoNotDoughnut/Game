package rhys.game.objects.sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureSheet {

	public final int spriteWidth, spriteHeight;
	public int width, height;
	public BufferedImage image;
	private String path;
	
	public TextureSheet(String path, int spriteWidth, int spriteHeight) {
		this.path=path;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		try {
			image = ImageIO.read(SpriteSheet.class.getResource(path)); //get image
			width = image.getWidth();
			height = image.getHeight();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
