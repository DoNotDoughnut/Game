package rhys.game.input;

import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyInput extends GLFWKeyCallback {

	public boolean up = false, down = false, left = false, right = false, action = false, sprint = false;
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		
		
	}

}
