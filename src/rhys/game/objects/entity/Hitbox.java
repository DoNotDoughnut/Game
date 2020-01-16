package rhys.game.objects.entity;

import rhys.game.main.GameRenderer;
import rhys.game.objects.tile.Tile;

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
		return(owner.level.getTileFromCoordinates((x 		  ) / Tile.size, (y + yO + height) / Tile.size).isSolid() || 
			   owner.level.getTileFromCoordinates((x + width) / Tile.size, (y + yO + height) / Tile.size).isSolid());
	}
	
	public boolean rightCollision(int xO, int yO) {
		return(owner.level.getTileFromCoordinates((x + width + xO) / Tile.size, (y 	    	 ) / Tile.size).isSolid() || 
			   owner.level.getTileFromCoordinates((x + width + xO) / Tile.size, (y + height) / Tile.size).isSolid() );
	}
	
	public boolean topCollision(int xO, int yO) {
		return(owner.level.getTileFromCoordinates((x	      ) / Tile.size, (y + yO) / Tile.size).isSolid() || 
			   owner.level.getTileFromCoordinates((x + width) / Tile.size, (y + yO) / Tile.size).isSolid() );
	}
	
	public boolean leftCollision(int xO, int yO) {
		return(owner.level.getTileFromCoordinates((x + xO) / Tile.size, (y		     ) / Tile.size).isSolid() || 
			   owner.level.getTileFromCoordinates((x + xO) / Tile.size, (y + height) / Tile.size).isSolid() );
	}
	
	public boolean collisionAny(int xO, int yO) {
		return bottomCollision(xO, yO) || rightCollision(xO, yO) || leftCollision(xO, yO) || topCollision(xO, yO);
	}

	
	public void renderHitbox(GameRenderer gg) {
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

	public int getSpriteX() {
		return x-wOffset+1;
	}
	
	public int getSpriteY() {
		return y-hOffset+1;
	}

}
