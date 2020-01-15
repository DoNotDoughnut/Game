package rhys.game.objects.tile;

public class TileCoordinate {
	
	public final int x,y;
	public Tile tile;
	
	public TileCoordinate(Tile tile, int x, int y) {
		this.tile=tile;
		this.x=x * Tile.size;
		this.y=y * Tile.size;
		Tile.tileUpdateList.add(this);
		
	}

	public TileCoordinate(int x, int y) {
		this.x=x * Tile.size;
		this.y=y * Tile.size;
	}
	
	
}
