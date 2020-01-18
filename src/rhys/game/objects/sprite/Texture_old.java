package rhys.game.objects.sprite;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import static org.lwjgl.stb.STBImage.*;

public class Texture_old {

	private int id, width, height;

	public Texture_old(String path) {

		ByteBuffer image;
		int width, height;
		try (MemoryStack stack = MemoryStack.stackPush()) {
			
			/* Prepare image buffers */
			
			IntBuffer w = stack.mallocInt(1);
			IntBuffer h = stack.mallocInt(1);
			IntBuffer comp = stack.mallocInt(1);

			/* Load image */
			stbi_set_flip_vertically_on_load(true);
			image = stbi_load(path, w, h, comp, 4);
			if (image == null) {
				throw new RuntimeException(
						"Failed to load a texture file!" + System.lineSeparator() + stbi_failure_reason());
			}

			/* Get width and height of image */
			width = w.get();
			height = h.get();
		}

		id = glGenTextures();

		setWidth(width);
		setHeight(height);

		bind();

		setParameter(GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
		setParameter(GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);
		setParameter(GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		setParameter(GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
		
	}

	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

	/**
	 * Sets a parameter of the texture.
	 *
	 * @param name  Name of the parameter
	 * @param value Value to set
	 */

	public void setParameter(int name, int value) {
		glTexParameteri(GL_TEXTURE_2D, name, value);
	}

	/**
	 * Delete the texture.
	 */
	public void delete() {
		glDeleteTextures(id);
	}

	/**
	 * Gets the texture width.
	 *
	 * @return Texture width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the texture width.
	 *
	 * @param width The width to set
	 */
	public void setWidth(int width) {
		if (width > 0) {
			this.width = width;
		}
	}

	/**
	 * Gets the texture height.
	 *
	 * @return Texture height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the texture height.
	 *
	 * @param height The height to set
	 */
	public void setHeight(int height) {
		if (height > 0) {
			this.height = height;
		}
	}

}
