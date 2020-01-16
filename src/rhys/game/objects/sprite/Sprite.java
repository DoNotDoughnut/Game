package rhys.game.objects.sprite;

import java.util.ArrayList;
import java.util.Arrays;

public class Sprite {

	public final int width, height, totalSprites;
	private int x,y, idleTime;
	public int[] pixels;
	private SpriteSheet sheet;
	private boolean defaultScan, animated;
	
	public static final int defaultSize = 16;
	
	private static ArrayList<Sprite> animatedSprites = new ArrayList<>();
	
	public static Sprite boundSprite = new Sprite(0x45283C);
	public static Sprite voidSprite = new Sprite(0x000000);
	public static Sprite spawnSprite = new Sprite(0xFF0000);
	public static Sprite consoleSprite = new Sprite(0x202020);
	
	private static int tick = 0;
	
	public static void update() {
		
		if(tick < 65536)
			tick++;
		else
			tick = 0;
		
		for(Sprite spr : animatedSprites) {
			
			//Animation hooks here
			
			spr.setVariant(tick / spr.idleTime  % spr.totalSprites + 1);
		}
	}
	
	public Sprite(SpriteSheet sheet, int x, int y,  int width, int height, int totalSprites, boolean defaultScan, boolean animated, int idleTime) {
		this.sheet=sheet;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=width;
		this.totalSprites=totalSprites;
		pixels = new int[width*height];
		this.defaultScan=defaultScan;
		this.animated=animated;
		if(this.animated)
			animatedSprites.add(this);
		this.idleTime=idleTime;
		
		load(x, y);
	}
	
	private void load(int inX, int inY) {
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				pixels[x+y*width] = sheet.pixels[(x+inX*width)+(y+inY*height)*sheet.width];
	}
	
	public Sprite(SpriteSheet sheet, int x, int y, int size, int totalSprites) {
		this(sheet, x, y, size, size, totalSprites, true, false, 0);
	}
	
	public Sprite(SpriteSheet sheet, int x, int y, int totalSprites) {
		this(sheet, x, y, defaultSize, defaultSize, totalSprites, true, false, 0);
	}
	
	public Sprite(int color, int width, int height) {
		this.totalSprites=1;
		this.width=width;
		this.height=height;
		pixels = new int[width*height];
		Arrays.fill(pixels, color);
	}
	
	public Sprite(int color) {
		this(color, defaultSize, defaultSize);
	}
	
	public void setVariant(int variant, boolean onX) {
		if(variant>totalSprites) throw new NullPointerException("This sprite does not have this many variations! "+variant+" variations were entered and "+totalSprites+" is the total available.");
		if(onX)
			load(x+(variant-1),y);
		else
			load(x,y+(variant-1));
	}
	
	public void setVariant(int variant) {
		setVariant(variant, defaultScan);
	}
	
	public boolean scansOnX() {
		return defaultScan;
	}
	
	public Sprite getVariant(int variant) {
		if(defaultScan)
			return new Sprite(sheet, x+(variant-1), y, width, height, 1, defaultScan, animated, idleTime);
		else
			return new Sprite(sheet, x, y+(variant-1), width, height, 1, defaultScan, animated, idleTime);
	}

}
