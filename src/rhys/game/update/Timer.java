package rhys.game.update;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Timer {

    private double lastLoopTime;
    private float timeCount;
    private int fps,
    			fpsCount,
    			ups,
    			upsCount;
        
    public void init() {
        lastLoopTime = getTime();
    }

    /**
     * Returns the time elapsed since <code>glfwInit()</code> in seconds.
     *
     * @return System time in seconds
     */
    public double getTime() {
        return glfwGetTime();
    }

    /**
     * Returns the time that have passed since the last loop.
     *
     * @return Delta time in seconds
     */
    public float getDelta() {
        double time = getTime();
        float delta = (float) (time - lastLoopTime);
        lastLoopTime = time;
        timeCount += delta;
        return delta;
    }

    public void updateFPS() {
        fpsCount++;
    }

    public void updateUPS() {
        upsCount++;
    }

    public void update() {
        if (timeCount > 1f) {
            fps = fpsCount;
            fpsCount = 0;

            ups = upsCount;
            upsCount = 0;

            timeCount -= 1f;
        }
    }

    /**
     * Getter for the FPS.
     *
     * @return Frames per second
     */
    public int getFPS() {
        return fps > 0 ? fps : fpsCount;
    }

    /**
     * Getter for the UPS.
     *
     * @return Updates per second
     */
    public int getUPS() {
        return ups > 0 ? ups : upsCount;
    }

    /**
     * Getter for the last loop time.
     *
     * @return System time of the last loop
     */
    public double getLastLoopTime() {
        return lastLoopTime;
    }

}