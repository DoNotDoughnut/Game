package net.rhysholloway.game.graphics.sprite;

public class AnimatedSprite {

	private int delay, tick = 0;
	private Sprite[] sprites;

	public AnimatedSprite(Sprite[] sprites, int delay) {
		this.sprites = sprites;
		this.delay = delay;
	}

	public AnimatedSprite(SpriteSheet sheet, int delay) {
		this(createSprites(sheet), delay);
	}

	private static Sprite[] createSprites(SpriteSheet sheet) {
		Sprite[] sprites = new Sprite[sheet.width / sheet.spriteWidth];
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new Sprite(sheet, i, 0);
		}
		return sprites;
	}

	public void resetTick() {
		tick = 0;
	}
	
	public void update() {
		if (sprites.length * delay < tick)
			tick = 0;
		else
			tick++;
		//System.out.println(tick);
	}

	public Sprite get() {
		if (sprites.length * delay > tick)
			return sprites[tick / delay];
		else return sprites[sprites.length - 1];
	}

}
