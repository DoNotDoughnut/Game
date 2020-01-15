package rhys.game.main;

import rhys.game.objects.entity.Player;
import rhys.game.objects.sprite.Sprite;
import rhys.game.objects.tile.Tile;

public class GameRenderer {

	public int[] pixels;
	public int width, height, xOffset, yOffset;
	
	public void render(int xp, int yp, Sprite sprite, boolean flip) {
		for (int y = 0; y < sprite.size; y++) {
			for (int x = 0; x < sprite.size; x++) {
				
				int xx = x + (xp - xOffset), 
					yy = y + (yp - yOffset),
					xFlip = x;
				
				if(flip) xFlip = (sprite.size - 1) - x;
				
				if((yy < 0 || yy >= height)||(xx < -sprite.size || xx >= width))
					break;
				
				if(xx<0) 
					xx = 0;
				
				if(sprite.pixels[xFlip+y*sprite.size]!=0xffff00ff) 
					pixels[xx + yy * width] = sprite.pixels[xFlip + y * sprite.size];
			}
		}
	}
	
	public void renderPlayer(int xp, int yp, Player player, boolean flip) {
		render(xp, yp, player.sprite, flip);
	}
	
	public GameRenderer(int width,int height) {
		this.width=width;
		this.height=height;
		pixels = new int[width*height];
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}
	
	public void renderTile(int xp, int yp, Tile tile, boolean flip) {
		render(xp,yp,tile.sprite, flip);
	}
}
