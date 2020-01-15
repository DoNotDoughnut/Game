package rhys.game.objects;

import rhys.game.main.GameRenderer;

public class Hitbox {
	
	public int x, y, width, height;
	protected int wOffset, hOffset;
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
		boolean[] col = {false, false, false, false};
		
		col[0] = bottomCollision(xO, yO);
		col[1] = rightCollision(xO, yO);
		col[2] = topCollision(xO, yO);
		col[3] = leftCollision(xO, yO);
		
		return col;
		
	}
	
	public boolean bottomCollision(int xO, int yO) {
		return(owner.level.getTileFromCoordinates((x 		  ) / Tile.size, (y + yO + height) / Tile.size).isSolid() || 
			   owner.level.getTileFromCoordinates((x + width-1) / Tile.size, (y + yO + height) / Tile.size).isSolid());
	}
	
	public boolean rightCollision(int xO, int yO) {
		return(owner.level.getTileFromCoordinates((x + xO + width) / Tile.size, (y 	    	 ) / Tile.size).isSolid() || 
			   owner.level.getTileFromCoordinates((x + xO + width) / Tile.size, (y + height-1) / Tile.size).isSolid() );
	}
	
	public boolean topCollision(int xO, int yO) {
		return(owner.level.getTileFromCoordinates((x	      ) / Tile.size, (y + yO) / Tile.size).isSolid() || 
			   owner.level.getTileFromCoordinates((x + width-1) / Tile.size, (y + yO) / Tile.size).isSolid() );
	}
	
	public boolean leftCollision(int xO, int yO) {
		return(owner.level.getTileFromCoordinates((x + xO) / Tile.size, (y		     ) / Tile.size).isSolid() || 
			   owner.level.getTileFromCoordinates((x + xO) / Tile.size, (y + height-1) / Tile.size).isSolid() );
	}
	
	public boolean collisionAny(int xO, int yO) {
		return bottomCollision(xO, yO) || rightCollision(xO, yO) || leftCollision(xO, yO) || topCollision(xO, yO);
	}

	
	public void render(GameRenderer gg) {
		for (int y = this.y; y < this.y+height; y++) {
			for (int x = this.x; x < this.x+width; x++) {
				
				int xx = x - gg.xOffset, 
					yy = y - gg.yOffset;
				
				if((yy < 0 		|| yy >= gg.height ) ||
				   (xx < -width || xx >= gg.width  ))
					break;
				
					gg.pixels[xx + yy * gg.width] = 0xFF0000;
			}
		}
	}

}
