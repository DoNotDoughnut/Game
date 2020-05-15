package rhys.game.objects.entity.entities;

/*

@Deprecated
public class PlayerFountain extends Player {

	public PlayerFountain() {
		super();
	}

	public void update() {
		
		int xO = 0, yO = 0;

		if(GameKeyListener.up())
			yO-=2;
		if(GameKeyListener.down())
			yO+=2;
		if(GameKeyListener.left())
			xO-=2;
		if(GameKeyListener.right())
			xO+=2;
		
		//Movement commands below
		
		if(xO!=0||yO!=0)
			move(xO,yO);
	}

	public boolean collision(int xO, int yO) {
		for(int c = 0; c < 4; c++) {
			int xt = ((hitbox.x + xO) + c % 2 * Sprite.defaultSize + 7) / Tile.size;
			int yt = ((hitbox.y + yO) + c / 2 * Sprite.defaultSize + 8) / Tile.size;
			if(level.getTileFromCoordinates(xt,yt).isSolid())
				return true;
		}
		return false;
	}
	
	public void render(GameRenderer gg) {
		
		// Kick > Crouch/Sprint > Walk > Idle
		
		GameRenderer.render(hitbox.x - level.focus.x, hitbox.y - level.focus.y, this.sprite, dir != 0);
	}

	
}

*/