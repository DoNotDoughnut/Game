package rhys.game.objects.entity.entities;

import rhys.game.main.Game;
import rhys.game.main.GameRenderer;
import rhys.game.objects.entity.Player;
import rhys.game.objects.sprite.Sprite;
import rhys.game.objects.tile.Tile;

public class PlayerFountain extends Player {

	public void update() {
		
		int xO = 0, yO = 0;

		if(Game.keyInput.up)
			yO-=2;
		if(Game.keyInput.down)
			yO+=2;
		if(Game.keyInput.left)
			xO-=2;
		if(Game.keyInput.right)
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
		
		gg.renderPlayer(hitbox.x, hitbox.y, this, dir != 0);
	}

	
}
