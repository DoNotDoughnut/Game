package rhys.game.objects.gui;

import java.util.ArrayList;

import rhys.game.main.GameRenderer;

public class GUIManager {
	
	public ArrayList<GUIPanel> panels;
	
	public GUIManager() {
		panels = new ArrayList<>();
	}
	
	public void update() {
		for(GUIPanel panel : panels)
			if(panel.alive)
				panel.update();
	}
	
	public void render(GameRenderer graphics) {
		for(GUIPanel panel : panels)
			if(panel.alive)
				panel.render(graphics);
	}
	
	public void add(GUIPanel panel) {
		panels.add(panel);
	}
	
	public void enable(int i) {
		panels.get(i).spawn();
	}
	
	public void disable(int i) {
		panels.get(i).despawn();
	}
	
	public boolean isAlive(int i) {
		return panels.get(i).alive;
	}
	
}
