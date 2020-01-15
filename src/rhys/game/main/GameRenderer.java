package rhys.game.main;

import rhys.game.objects.Mob;
import rhys.game.objects.Sprite;
import rhys.game.objects.Tile;

public class GameRenderer {

	public int[] pixels;
	public int width, height, xOffset, yOffset;
	
	public void render(int xp, int yp, Sprite sprite) {
		for (int y = 0; y < sprite.size; y++) {
			for (int x = 0; x < sprite.size; x++) {
				int xx = x + (xp - xOffset), yy = y + (yp - yOffset);
				if((yy < 0 || yy >= height)||(xx < -sprite.size || xx >= width))break;
				if(xx<0) xx = 0;
				pixels[xx + yy * width] = sprite.pixels[x + y * sprite.size];
			}
		}
	}
	
	public void renderMob(int xp, int yp, Mob mob, boolean flip) {
		for (int y = 0; y < mob.sprite.size; y++) {
			for (int x = 0; x < mob.sprite.size; x++) {
				
				int xx = x + (xp - xOffset), 
					yy = y + (yp - yOffset), 
					xFlip = x;
				
				if(flip) xFlip = (mob.sprite.size - 1) - x;
				
				
				if((yy < 0 			  || yy >= height ) ||
				   (xx < -mob.sprite.size || xx >= width  ))
					break;
				
				if(xx<0) 
					xx = 0;
				
				if(mob.sprite.pixels[xFlip+y*mob.sprite.size]!=0xffff00ff) 
					pixels[xx + yy * width] = mob.sprite.pixels[xFlip + y * mob.sprite.size];
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset=xOffset;
		this.yOffset=yOffset;
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
	
	public void renderTile(int xp, int yp, Tile tile) {
		render(xp,yp,tile.sprite);
	}
}
