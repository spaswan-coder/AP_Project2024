package io.github.birds;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Inner Pig class
public class Pig {
    private Texture texture;
    private float x, y, width, height;
    private float rotation; // Rotation angle in degrees

    public Pig(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = 0; // Default rotation
    }

    // Method to draw the pig with rotation
    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width / 2, height / 2, width, height, 1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    // Set the rotation angle
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    // Increment the rotation angle (for smooth rotation)
    public void rotate(float angle) {
        this.rotation += angle;
        if (this.rotation >= 360) this.rotation -= 360; // Keep the angle within [0, 360]
    }

    // Getters and setters (if needed)
    public float getRotation() {
        return rotation;
    }
}
