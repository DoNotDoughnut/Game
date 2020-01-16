package rhys.game.main;

import java.util.Arrays;
import rhys.game.objects.sprite.Sprite;

public class GameRenderer {

	public int[] pixels;
	public int xOffset, yOffset;
	public final int width, height, scale;
	
	public void render(int xp, int yp, Sprite sprite, boolean flip) {
		for (int y = 0; y < sprite.height; y++) {
			for (int x = 0; x < sprite.width; x++) {
				
				int xx = x + (xp - xOffset), 
					yy = y + (yp - yOffset),
					xFlip = x;
				
				if(flip) xFlip = (sprite.width - 1) - x;
				
				if((yy < 0 || yy >= height)||(xx < -sprite.width || xx >= width))
					break;
				
				if(xx<0) 
					xx = 0;
				
				if(sprite.pixels[xFlip+y*sprite.width]!=0xffff00ff) 
					pixels[xx + yy * width] = sprite.pixels[xFlip + y * sprite.width];
			}
		}
	}
	
	public void clear() {
		Arrays.fill(pixels, 0);
	}
	
	public GameRenderer(int width,int height, int scale) {
		this.width=width;
		this.height=height;
		this.scale=scale;
		pixels = new int[width*height];
	}
}
