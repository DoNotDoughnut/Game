package net.rhys.game.objects.entity.entities;

import net.rhys.game.objects.entity.Hitbox;
import net.rhys.game.objects.entity.Player;
import net.rhys.game.objects.gui.GUIManager;
import net.rhys.game.objects.level.GameLevel;
import net.rhys.gameengine.EEngine;
import net.rhys.gameengine.input.EKeyInput;
import net.rhys.gameengine.input.EMouseInput;
import net.rhys.gameengine.render.ERenderer;
import net.rhys.gameengine.texture.ETexture;
import net.rhys.gameengine.texture.ETextureSheet;


public class PlayerBlue extends Player {
	
	public boolean walking = false, 
				   running = false, 
				   kicking = false, 
				   jumping = false, 
				   falling = true;
	
	public static ETexture playerBlue_idle = new PlayerBlueTexture(0, 3, 30),
						 playerBlue_walk = new PlayerBlueTexture(1, 6, 5),
						 playerBlue_kick = new PlayerBlueTexture(2, 3, 8),
						 playerBlue_hurt = new PlayerBlueTexture(3, 3, 5),
						 playerBlue_run = new PlayerBlueTexture(4, 7, 2);
				  
	
	public PlayerBlue(GameLevel level, EKeyInput keyInput, EMouseInput mouseInput, GUIManager gui,  int x, int y, int pW, int pH, int pWO, int pHO) {
		super(keyInput, mouseInput, gui);
		this.level=level;
		texture = playerBlue_idle;
		speed = 2;
		hitbox = new Hitbox(this, x, y, pW, pH, pWO, pHO);
		spawn();
	}
	
	private float vDelta = 0, gDelta = 0.25f;
	
	public void update() {
		
		int xO = 0, yO = 0;
		
		boolean onGround = hitbox.bottomCollision(0, 1); //Checks if currently on the ground
		
		speed = 2;
		running = false;
		
		if(keyInput.action&&!gui.isAlive(0))
			gui.enable(0);
		
		if(keyInput.sprint&&walking) { // Sprint mechanics
			running = true;
			speed*=2;
		}
		
		if(keyInput.up) { // Jump
			if(onGround&&!(jumping||falling)) { // Start jump
				jumping = true;
				if(running)
					vDelta = -3.25f;
				else
					vDelta = -4.5f;
			}
		}
		
		if(keyInput.down) { // Drop through platform
			
		}
		
		if(keyInput.left) //Move left
			xO-=speed;
		if(keyInput.right) //Move right
			xO+=speed;
		
		//Movement commands below
		
		if(!onGround&&!(jumping||falling)) { //If the player is floating make them fall
			falling = true;
			vDelta = 0;
		}
			
		if(jumping||falling) { //Jumping / Falling math
			
			yO += vDelta;
			if(vDelta<4.5f)
				vDelta += gDelta;
			else vDelta = 4.5f;
			
			if(!onGround&&hitbox.topCollision(xO, yO-1)&&!falling) { // If hit a block while jumping, start falling
				falling = true;
				vDelta = 0;
			}
			
			if((jumping || falling)&&hitbox.bottomCollision(xO, (int) (yO+vDelta))) { // If on ground stop fall
				while(!hitbox.bottomCollision(xO, yO+1)) 
					yO++;
				
				jumping = false;
				falling = false;
			}
			
			
			
		}
		
		walking = (xO != 0); // Stop walking animation if not moving
		
		if(xO!=0||yO!=0) //Move player if its values have changed
			move(xO,yO);
		
	}
	
	public void render(ERenderer gg) {
		if(alive) {
			
			// Kick > Crouch/Sprint > Walk > Idle
		
			if(kicking)
				texture = playerBlue_kick;
			else if(running)
				texture = playerBlue_run;
			else if(walking||jumping)
				texture = playerBlue_walk;
			else 
				texture = playerBlue_idle;
		
			gg.render(hitbox.getTextureX(), hitbox.getTextureY(), this.texture, dir != 0);
			//hitbox.renderHitbox(gg);
		}
	}
}

class PlayerBlueTexture extends ETexture {

	public static ETextureSheet playerBlue = new ETextureSheet(EEngine.resources+"spritesheets/player.png", 7, 5, 32);
	
	public PlayerBlueTexture(int y, int variants, int idleTime) {
		super(playerBlue, 0, y, 32, 32, variants, true, true, idleTime);
	}

}

