package io.github.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Block {
    private Texture texture;
    private float x, y;
    private float width, height;
    private float fallSpeed; // Speed at which the block falls

    public Block(String texturePath, float x, float y, float width, float height, float fallSpeed) {
        this.texture = new Texture(texturePath);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fallSpeed = fallSpeed;
    }

    public void update(float delta) {
        // Update the y-coordinate to make the block fall
        if (y > 0) {
            y -= fallSpeed * delta;
        } else {
            y = 0; // Stop at ground level
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void dispose() {
        texture.dispose();
    }
}
