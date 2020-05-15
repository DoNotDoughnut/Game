package net.rhysholloway.game.gui;

import java.util.ArrayList;

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
	
	public void render() {
		for(GUIPanel panel : panels)
			if(panel.alive)
				panel.render();
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
