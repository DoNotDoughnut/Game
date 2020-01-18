package rhys.game.graphics;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;

import java.awt.Color;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.system.MemoryUtil;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import rhys.game.graphics.text.Font;
import rhys.game.objects.sprite.Sprite;

public class Renderer {

	private VertexArrayObject vao;
	private VertexBufferObject vbo;

	private FloatBuffer vertices;
	private int numVertices;
	private boolean drawing;

	private Font font;
	private Font debugFont;

	public int xOffset, yOffset;
	public final int width, height;

	public Renderer(int width, int height) {
		this.width=width;
		this.height=height;
	}

	/** Initializes the renderer. */
	public void init() {

		/* Enable blending */
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		/* Create fonts */
		try {
			font = new Font(new FileInputStream("resources/Inconsolata.ttf"), 16);
		} catch (FontFormatException | IOException ex) {
			Logger.getLogger(Renderer.class.getName()).log(Level.CONFIG, null, ex);
			font = new Font();
		}
		debugFont = new Font(12, false);
	}

	/**
	 * Clears the drawing area.
	 */
	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	/**
	 * Begin rendering.
	 */
	public void begin() {
		if (drawing) {
			throw new IllegalStateException("Renderer is already drawing!");
		}
		drawing = true;
		numVertices = 0;
	}

	/**
	 * End rendering.
	 */
	public void end() {
		if (!drawing) {
			throw new IllegalStateException("Renderer isn't drawing!");
		}
		drawing = false;
		flush();
	}

	/**
	 * Flushes the data to the GPU to let it get rendered.
	 */
	public void flush() {
		if (numVertices > 0) {
			vertices.flip();

			if (vao != null) {
				vao.bind();
			} else {
				vbo.bind(GL_ARRAY_BUFFER);
			}

			/* Upload the new vertex data */
			vbo.bind(GL_ARRAY_BUFFER);
			vbo.uploadSubData(GL_ARRAY_BUFFER, 0, vertices);

			/* Draw batch */
			glDrawArrays(GL_TRIANGLES, 0, numVertices);

			/* Clear vertex data for next batch */
			vertices.clear();
			numVertices = 0;
		}
	}

	/**
	 * Calculates total width of a text.
	 *
	 * @param text The text
	 *
	 * @return Total width of the text
	 */
	public int getTextWidth(CharSequence text) {
		return font.getWidth(text);
	}

	/**
	 * Calculates total height of a text.
	 *
	 * @param text The text
	 *
	 * @return Total width of the text
	 */
	public int getTextHeight(CharSequence text) {
		return font.getHeight(text);
	}

	/**
	 * Calculates total width of a debug text.
	 *
	 * @param text The text
	 *
	 * @return Total width of the text
	 */
	public int getDebugTextWidth(CharSequence text) {
		return debugFont.getWidth(text);
	}

	/**
	 * Calculates total height of a debug text.
	 *
	 * @param text The text
	 *
	 * @return Total width of the text
	 */
	public int getDebugTextHeight(CharSequence text) {
		return debugFont.getHeight(text);
	}

	/**
	 * Draw text at the specified position.
	 *
	 * @param text Text to draw
	 * @param x    X coordinate of the text position
	 * @param y    Y coordinate of the text position
	 */
	public void drawText(CharSequence text, float x, float y) {
		font.drawText(this, text, x, y);
	}

	/**
	 * Draw debug text at the specified position.
	 *
	 * @param text Text to draw
	 * @param x    X coordinate of the text position
	 * @param y    Y coordinate of the text position
	 */
	public void drawDebugText(CharSequence text, float x, float y) {
		debugFont.drawText(this, text, x, y);
	}

	/**
	 * Draw text at the specified position and color.
	 *
	 * @param text Text to draw
	 * @param x    X coordinate of the text position
	 * @param y    Y coordinate of the text position
	 * @param c    Color to use
	 */
	public void drawText(CharSequence text, float x, float y, Color c) {
		font.drawText(this, text, x, y, c);
	}

	/**
	 * Draw debug text at the specified position and color.
	 *
	 * @param text Text to draw
	 * @param x    X coordinate of the text position
	 * @param y    Y coordinate of the text position
	 * @param c    Color to use
	 */
	public void drawDebugText(CharSequence text, float x, float y, Color c) {
		debugFont.drawText(this, text, x, y, c);
	}

	/**
	 * Draws the currently bound texture on specified coordinates.
	 *
	 * @param texture Used for getting width and height of the texture
	 * @param x       X position of the texture
	 * @param y       Y position of the texture
	 */
	public void render(Sprite texture, int x, int y) {
		drawTexture(texture, x, y, Color.WHITE);
	}

	/**
	 * Draws the currently bound texture on specified coordinates and with specified
	 * color.
	 *
	 * @param texture Used for getting width and height of the texture
	 * @param x       X position of the texture
	 * @param y       Y position of the texture
	 * @param c       The color to use
	 */
	public void drawTexture(Sprite texture, float x, float y, Color c) {
		/* Vertex positions */
		float x1 = x;
		float y1 = y;
		float x2 = x1 + texture.width;
		float y2 = y1 + texture.height;

		/* Texture coordinates */
		float s1 = 0f;
		float t1 = 0f;
		float s2 = 1f;
		float t2 = 1f;

		drawTextureRegion(x1, y1, x2, y2, s1, t1, s2, t2, c);
	}

	/**
	 * Draws a texture region with the currently bound texture on specified
	 * coordinates.
	 *
	 * @param texture   Used for getting width and height of the texture
	 * @param x         X position of the texture
	 * @param y         Y position of the texture
	 * @param regX      X position of the texture region
	 * @param regY      Y position of the texture region
	 * @param regWidth  Width of the texture region
	 * @param regHeight Height of the texture region
	 */
	public void drawTextureRegion(Sprite texture, float x, float y, float regX, float regY, float regWidth,
			float regHeight) {
		drawTextureRegion(texture, x, y, regX, regY, regWidth, regHeight, Color.WHITE);
	}

	/**
	 * Draws a texture region with the currently bound texture on specified
	 * coordinates.
	 *
	 * @param texture   Used for getting width and height of the texture
	 * @param x         X position of the texture
	 * @param y         Y position of the texture
	 * @param regX      X position of the texture region
	 * @param regY      Y position of the texture region
	 * @param regWidth  Width of the texture region
	 * @param regHeight Height of the texture region
	 * @param c         The color to use
	 */
	public void drawTextureRegion(Sprite texture, float x, float y, float regX, float regY, float regWidth,
			float regHeight, Color c) {
		/* Vertex positions */
		float x1 = x;
		float y1 = y;
		float x2 = x + regWidth;
		float y2 = y + regHeight;

		/* Texture coordinates */
		float s1 = regX / texture.width;
		float t1 = regY / texture.height;
		float s2 = (regX + regWidth) / texture.width;
		float t2 = (regY + regHeight) / texture.height;

		drawTextureRegion(x1, y1, x2, y2, s1, t1, s2, t2, c);
	}

	/**
	 * Draws a texture region with the currently bound texture on specified
	 * coordinates.
	 *
	 * @param x1 Bottom left x position
	 * @param y1 Bottom left y position
	 * @param x2 Top right x position
	 * @param y2 Top right y position
	 * @param s1 Bottom left s coordinate
	 * @param t1 Bottom left t coordinate
	 * @param s2 Top right s coordinate
	 * @param t2 Top right t coordinate
	 */
	public void drawTextureRegion(float x1, float y1, float x2, float y2, float s1, float t1, float s2, float t2) {
		drawTextureRegion(x1, y1, x2, y2, s1, t1, s2, t2, Color.WHITE);
	}

	/**
	 * Draws a texture region with the currently bound texture on specified
	 * coordinates.
	 *
	 * @param x1 Bottom left x position
	 * @param y1 Bottom left y position
	 * @param x2 Top right x position
	 * @param y2 Top right y position
	 * @param s1 Bottom left s coordinate
	 * @param t1 Bottom left t coordinate
	 * @param s2 Top right s coordinate
	 * @param t2 Top right t coordinate
	 * @param c  The color to use
	 */
	public void drawTextureRegion(float x1, float y1, float x2, float y2, float s1, float t1, float s2, float t2,
			Color c) {
		if (vertices.remaining() < 7 * 6) {
			/* We need more space in the buffer, so flush it */
			flush();
		}

		float r = c.getRed();
		float g = c.getGreen();
		float b = c.getBlue();
		float a = c.getAlpha();

		vertices.put(x1).put(y1).put(r).put(g).put(b).put(a).put(s1).put(t1);
		vertices.put(x1).put(y2).put(r).put(g).put(b).put(a).put(s1).put(t2);
		vertices.put(x2).put(y2).put(r).put(g).put(b).put(a).put(s2).put(t2);

		vertices.put(x1).put(y1).put(r).put(g).put(b).put(a).put(s1).put(t1);
		vertices.put(x2).put(y2).put(r).put(g).put(b).put(a).put(s2).put(t2);
		vertices.put(x2).put(y1).put(r).put(g).put(b).put(a).put(s2).put(t1);

		numVertices += 6;
	}
	
	public Texture getTexture(String name) {
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("rhys/game/resources/sprites/"+name+"png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Dispose renderer and clean up its used data.
	 */
	public void dispose() {
		MemoryUtil.memFree(vertices);

		if (vao != null) {
			vao.delete();
		}
		vbo.delete();

		font.dispose();
		debugFont.dispose();
	}

	public void render(int i, int j, Sprite sprite, boolean b) {
		
	}

}