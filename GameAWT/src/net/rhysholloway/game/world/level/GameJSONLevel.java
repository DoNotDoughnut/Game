package net.rhysholloway.game.world.level;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.rhysholloway.game.world.entity.entities.ScreenFocus;
import net.rhysholloway.game.world.tile.Tile;

public abstract class GameJSONLevel extends GameLevel {
	
	public GameJSONLevel(String path) {
		super(new ScreenFocus());
		JSONObject json = load(path);
		width = json.getInt("width");
		height = json.getInt("height");
		
		//tileKey = new HashMap<Integer, Tile>();
		JSONArray arr = json.getJSONArray("map");
		
		if(arr.length()!=width*height)
			throw new ArrayIndexOutOfBoundsException("The JSON map array is a different length than specified");
		
		tiles = new Tile[width * height];
		
		for(int i = 0; i < arr.length(); i++) {
			tiles[i] = Tile.tileSet.get(arr.getString(i));
		}
		
	}
	
	private static JSONObject load(String path) {
		try {
			return new JSONObject(new String(Files.readAllBytes(Paths.get(GameJSONLevel.class.getResource(path).toURI()))));
		} catch (IOException | JSONException | URISyntaxException e) {
			throw new NullPointerException("Game configuration could not be read!" + e.getMessage());
		}
	}

}
