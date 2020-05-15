package net.rhysholloway.game.world.level.levels;

import net.rhysholloway.game.graphics.sprite.Sprite;
import net.rhysholloway.game.input.GameKeyListener;
import net.rhysholloway.game.world.entity.entities.PlayerBlue;
import net.rhysholloway.game.world.entity.entities.ScreenFocus;
import net.rhysholloway.game.world.entity.util.Direction;
import net.rhysholloway.game.world.level.GameJSONLevel;
import net.rhysholloway.game.world.tile.Tile;
import net.rhysholloway.game.world.tile.TileCoordinate;

public class JSONLevelTest extends GameJSONLevel {

	PlayerBlue player;
	
	public JSONLevelTest() {
		super("/assets/levels/jsonlevel/level.json");
		spawnpoint = new TileCoordinate(3,3);
	}

	@Override
	public void create() {
		player = new PlayerBlue(this, spawnpoint.xpixel, spawnpoint.ypixel) {
			
			@Override
			public void update() {
				if (alive) {

					xO = 0;
					yO = 0;
					speed = defSpeed;
					playerHud.update();

					input();

					walking = (xO != 0 || yO != 0); // Stop walking animation if not moving

					if (xO != 0 || yO != 0) // Move player if its values have changed
						move(xO, yO);

					if (kicking)
						currentSprite = kick_side;
					else if (running)
						currentSprite = run_side;
					else if (walking||jumping)
						currentSprite = walk_side;
					else
						currentSprite = idle_side;

					currentSprite.update();

				}
			}
			
			protected void input() {
				running = false;

				if (GameKeyListener.sprint() && walking) { // Sprint mechanics
					running = true;
					speed *= 2;
				}

				if (GameKeyListener.up()) { // Jump
					yO-=speed;
				}

				if (GameKeyListener.down()) { // Drop through platform
					yO+=speed;
				}

				if (GameKeyListener.left()) // Move left
					xO -= speed;
				if (GameKeyListener.right()) // Move right
					xO += speed;
				
			}
			
			protected void updateSprite() {
				if(xO == 0 && yO != 0) {
					if(verticalDir == Direction.UP) {
							if (kicking)
								currentSprite = kick_back;
							else if (running)
								currentSprite = run_back;
							else if (walking||jumping)
								currentSprite = walk_back;
							else
								currentSprite = idle_back;
					} else {
						if (kicking)
							currentSprite = kick_front;
						else if (running)
							currentSprite = run_front;
						else if (walking||jumping)
							currentSprite = walk_front;
						else
							currentSprite = idle_front;
					}
				}
			}
			
		};
		focus = new ScreenFocus(player);
	}
	
	@Override
	public void update() {
		player.update();
	}

	@Override
	public void render() {
		super.drawLevel();
		player.render();
	}

	@Override
	public void dispose() {
		
	}
	
	public Tile getTileFromCoordinates(int x, int y) {
		if (x < 0 || y < 0 || y >= height || x >= width)
			return Tile.boundTile;
		return tiles[x + y * width];
	}

	public static Tile dirt = new Tile(new Sprite("/assets/levels/jsonlevel/textures/dirt.png"), true, "dirt");
	public static Tile grass = new Tile(new Sprite("/assets/levels/jsonlevel/textures/grass.png"), true, "grass");
	public static Tile grasstop = new Tile(new Sprite("/assets/levels/jsonlevel/textures/grasstop.png"), false, "grasstop");
	public static Tile grasstop1 = new Tile(new Sprite("/assets/levels/jsonlevel/textures/grasstop1.png"), false, "grasstop1");
	public static Tile trunk1 = new Tile(new Sprite("/assets/levels/jsonlevel/textures/trunk1.png"), false, "trunk1");
	public static Tile trunk2 = new Tile(new Sprite("/assets/levels/jsonlevel/textures/trunk2.png"), false, "trunk2");
	public static Tile stone = new Tile(new Sprite("/assets/levels/jsonlevel/textures/stone.png"), true, "stone");
	public static Tile stone_pillar = new Tile(new Sprite("/assets/levels/jsonlevel/textures/stone_pillar.png"), false, "stone_pillar");
	public static Tile stone_cap = new Tile(new Sprite("/assets/levels/jsonlevel/textures/stone_cap.png"), false, "stone_cap");
	
}
