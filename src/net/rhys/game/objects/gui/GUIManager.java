package net.rhys.game.objects.gui;

import java.util.ArrayList;

import net.rhys.gameengine.render.ERenderer;

public class GUIManager {
	
	private ArrayList<GUIPanel> panels;
	
	public GUIManager() {
		panels = new ArrayList<>();
	}
	
	public void update() {
		for(GUIPanel panel : panels)
			if(panel.alive)
				panel.update();
	}
	
	public void render(ERenderer graphics) {
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
