package rhys.game.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MouseMotionInput extends GLFWCursorPosCallback {

	private int x, y;
	
	@Override
	public void invoke(long window, double xpos, double ypos) {
		x=(int) xpos;
		y=(int) ypos;
		
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
}
