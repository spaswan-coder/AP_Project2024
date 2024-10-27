package io.github.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Block {
    private Texture texture;
    private float x, y;
    private float width, height;

    public Block(String texturePath, float x, float y, float width, float height) {
        this.texture = new Texture(texturePath);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void dispose() {
        texture.dispose();
    }

    public boolean collides(Rectangle rectangle) {
        return false;
    }

    public void render(SpriteBatch batch) {
    }
}
