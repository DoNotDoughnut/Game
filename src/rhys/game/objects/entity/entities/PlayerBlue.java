package rhys.game.objects.entity.entities;


import rhys.game.graphics.Renderer;
import rhys.game.input.KeyInput;
import rhys.game.input.MouseMotionInput;
import rhys.game.objects.entity.Hitbox;
import rhys.game.objects.entity.Player;
import rhys.game.objects.gui.GUIManager;
import rhys.game.objects.level.GameLevel;
import rhys.game.objects.sprite.SpriteSheet;
import rhys.game.objects.sprite.Sprite;


public class PlayerBlue extends Player {
	
	public boolean walking = false, 
				   running = false, 
				   kicking = false, 
				   jumping = false, 
				   falling = true;
	
	public static Sprite playerBlue_idle = new PlayerBlueSprite(0, 3, 30),
						 playerBlue_walk = new PlayerBlueSprite(1, 6, 5),
						 playerBlue_kick = new PlayerBlueSprite(2, 3, 8),
						 playerBlue_hurt = new PlayerBlueSprite(3, 3, 5),
						 playerBlue_run = new PlayerBlueSprite(4, 7, 2);
				  
	
	public PlayerBlue(GameLevel level, KeyInput keyInput, MouseMotionInput mouseInput, GUIManager gui,  int x, int y, int pW, int pH, int pWO, int pHO) {
		super(keyInput, mouseInput, gui);
		this.level=level;
		sprite = playerBlue_idle;
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
			gui.panels.get(0).spawn();
		
		if(keyInput.sprint&&walking) { // Sprint mechanics
			running = true;
			speed*=2;
		}
		
		if(keyInput.up) { // Jump
			if(onGround&&!(jumping||falling)) { // Start jump
				jumping = true;
				vDelta = running ? -3.25f : -4.5f;
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
	
	public void render(Renderer graphics) {
		if(alive) {
			
			// Kick > Crouch/Sprint > Walk > Idle
		
			if(kicking)
				sprite = playerBlue_kick;
			else if(running)
				sprite = playerBlue_run;
			else if(walking||jumping)
				sprite = playerBlue_walk;
			else 
				sprite = playerBlue_idle;
		
			graphics.render(sprite, hitbox.getSpriteX(), hitbox.getSpriteY());//, this.sprite, dir != 0);
			//hitbox.renderHitbox(gg);
		}
	}
}

class PlayerBlueSprite extends Sprite {

	public static SpriteSheet playerBlue = new SpriteSheet("/rhys/game/resources/spritesheets/players/playerBlue.png", 32);
	
	public PlayerBlueSprite(int y, int variants, int idleTime) {
		super(playerBlue, 0, y, 32, 32);
	}

}

