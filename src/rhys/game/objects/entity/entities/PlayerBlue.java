package rhys.game.objects.entities;

import java.util.Arrays;

import rhys.game.main.Game;
import rhys.game.main.GameRenderer;
import rhys.game.objects.GameLevel;
import rhys.game.objects.Hitbox;
import rhys.game.objects.Mob;
import rhys.game.objects.Sprite;
import rhys.game.objects.SpriteSheet;
import rhys.game.objects.Tile;

@SuppressWarnings(value = { "unused" })
public class PlayerBlue extends Player {
	
	public boolean walking = false, 
				   running = false, 
				   kicking = false, 
				   jumping = false, 
				   falling = true;
	
	public static Sprite playerBlue_idle = new PlayerBlueSprite(0, 3, 30),
						 playerBlue_walk = new PlayerBlueSprite(1, 6, 5),
						 playerBlue_kick = new PlayerBlueSprite(2, 3, 8),
						 playerBlue_run = new PlayerBlueSprite(3, 7, 2);
				  
	
	public PlayerBlue(GameLevel level, int x, int y) {
		this.level=level;
		this.x=x;
		this.y=y;
		this.sprite=playerBlue_idle;
		hitbox = new Hitbox(this, x, y, 8, 15, 12, 10);
	}
	
	private float vDelta = 0, 
				  rbDelta, 
				  rbDegDelta = 2.5f, 
				  gDelta = 0.25f;
	
	public void update() {
		
		int xO = 0, yO = 0;
		
		//if(timeSinceAnim<idleWait) //Animation wait timer
		//	timeSinceAnim++;
		
		if(Game.keyInput.up) {
			if(hitbox.bottomCollision(xO, yO)&&!(jumping||falling)) { // Start jump
				jumping = true;
				vDelta = -4.5f;
				rbDelta = vDelta;
			}
		}
		//if(Game.keyInput.down)
		if(Game.keyInput.left) //Move left
			xO--;
		if(Game.keyInput.right) //Move right
			xO++;
		
		//Movement commands below
		
		if(!hitbox.bottomCollision(xO, yO)&&!(jumping||falling)) //If the player is floating make them fall
			startFall();
			
		if(jumping||falling) { //Jumping/Falling
			if(hitbox.bottomCollision(xO, yO)) { // Stop fall
				jumping = false;
				falling = false;
			}
			yO += vDelta;
			vDelta += gDelta;
			
			if(hitbox.topCollision(xO, yO)&&!falling) //Hit a block while jumping
				startFall();
			
		}
		
		
		if(hitbox.bottomCollision(0, yO+4)&&!hitbox.bottomCollision(xO, yO)) {
			while(!hitbox.bottomCollision(0, yO))
				yO++;
		}
		
		walking = (xO!=0); // Stop walking animation if not moving
		
		if(xO!=0||yO!=0)//Move player if its values have changed
			move(xO,yO);
		
	}
	
	private void startFall() {
			falling = true;
			vDelta = 0;
			rbDelta = vDelta;
	}

	
	
	//private static int timeSinceAnim = 0, idleWait = 60;
	
	public void render(GameRenderer gg) {
		
		// Kick > Crouch/Sprint > Walk > Idle
		
		/*if(kicking) {
			
			timeSinceAnim = 0;
			sprite = playerBlue_kick;
			
		} else if(running) {
			
			timeSinceAnim = 0;
			sprite = playerBlue_run;
			
		} else if(walking&&!(jumping||falling)) {
			
			timeSinceAnim = 0;
			sprite = playerBlue_walk;
			
		} else if(timeSinceAnim==idleWait) {
		
			sprite = playerBlue_idle;
			
		} else {
			
			playerBlue_idle.setVariant(1);
			sprite = playerBlue_idle;
			
		}*/
		
		//gg.renderMob(x, y, this, dir != 0);
		hitbox.render(gg);
	}
}

class PlayerBlueSprite extends Sprite {

	public PlayerBlueSprite(int y, int variants, int idleTime) {
		super(SpriteSheet.playerBlue, 0, y, variants, 32, true, true, idleTime);
	}

}

