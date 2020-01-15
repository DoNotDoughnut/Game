package rhys.game.objects.gui;

import java.util.ArrayList;

import rhys.game.main.GameRenderer;
import rhys.game.objects.entity.Entity;

public class GUIManager extends Entity {
	
	private ArrayList<GUIPanel> panels;
	
	public GUIManager() {
		super();
		panels = new ArrayList<>();
	}
	
	public void update() {
		for(GUIPanel panel : panels)
			panel.update();
	}
	
	public void render(GameRenderer graphics) {
		if(alive)
			for(GUIPanel panel : panels)
				panel.render(graphics);
	}
	
	public void add(GUIPanel panel) {
		panels.add(panel);
	}
}
