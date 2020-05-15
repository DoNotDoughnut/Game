package net.rhysholloway.game.graphics;

import java.util.Arrays;

import net.rhysholloway.game.Game;
import net.rhysholloway.game.graphics.sprite.Sprite;

public class GameRenderer {

	public static int[] pixels = new int[Game.canvasWidth * Game.canvasHeight];
	
	public static void render(int xp, int yp, Sprite sprite, boolean flip) {
		for (int y = 0; y < sprite.height; y++) {
			for (int x = 0; x < sprite.width; x++) {
				
				int xx = x + (xp), 
					yy = y + (yp),
					xFlip = x;
				
				if(flip) xFlip = (sprite.width - 1) - x;
				
				if((yy < 0 || yy >= Game.canvasHeight)||(xx < -sprite.width || xx >= Game.canvasWidth))
					break;
				
				if(xx<0) 
					xx = 0;
				
				if(sprite.pixels[xFlip+y*sprite.width]!=0xffff00ff) 
					pixels[xx + yy * Game.canvasWidth] = sprite.pixels[xFlip + y * sprite.width];
			}
		}
	}
	
	public static void clear() {
		Arrays.fill(pixels, 0);
	}
	
}
