package net.rhys.game.objects.entity;

import net.rhys.gameengine.render.ERenderer;

public class Hitbox {
	
	public int x, y, width, height;
	private int wOffset, hOffset; // To map sprite on
	private Mob owner;
	
	public static boolean print = false;
	
	public Hitbox(Mob owner, int x, int y, int width, int height, int wOffset, int hOffset) {
		this.owner=owner;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.wOffset=wOffset;
		this.hOffset=hOffset;
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
		return(owner.level.getTileFromCoordinates((x 		  ) >> 4, (y + yO + height) >> 4).isSolid() || 
			   owner.level.getTileFromCoordinates((x + width)  >> 4, (y + yO + height) >> 4).isSolid());
	}
	
	public boolean rightCollision(int xO, int yO) {
		return(owner.level.getTileFromCoordinates((x + width + xO) >> 4, (y 	    	 ) >> 4).isSolid() || 
			   owner.level.getTileFromCoordinates((x + width + xO) >> 4, (y + height) >> 4).isSolid() );
	}
	
	public boolean topCollision(int xO, int yO) {
		return(owner.level.getTileFromCoordinates((x	      ) >> 4, (y + yO) >> 4).isSolid() || 
			   owner.level.getTileFromCoordinates((x + width) >> 4, (y + yO) >> 4).isSolid() );
	}
	
	public boolean leftCollision(int xO, int yO) {
		return(owner.level.getTileFromCoordinates((x + xO) >> 4, (y		     ) >> 4).isSolid() || 
			   owner.level.getTileFromCoordinates((x + xO) >> 4, (y + height) >> 4).isSolid() );
	}
	
	public boolean collisionAny(int xO, int yO) {
		return bottomCollision(xO, yO) || rightCollision(xO, yO) || leftCollision(xO, yO) || topCollision(xO, yO);
	}

	
	public void renderHitbox(ERenderer gg) {
		for (int y = this.y; y <= this.y+height; y++) {
			for (int x = this.x; x <= this.x+width; x++) {
				
				int xx = x - gg.xOffset, 
					yy = y - gg.yOffset;
				
				if((yy < 0 		|| yy >= gg.height ) ||
				   (xx < -width || xx >= gg.width  ))
					break;
				
					gg.pixels[xx + yy * gg.width] = 0xFF0000;
			}
		}
	}

	public int getTextureX() {
		return x-wOffset+1;
	}
	
	public int getTextureY() {
		return y-hOffset+1;
	}

}
