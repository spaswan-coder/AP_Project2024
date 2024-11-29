package io.github.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Bird extends Image {
    private Texture texture;

    public Bird(String texturePath, float x, float y) {
        this.texture = new Texture(Gdx.files.internal(texturePath));
        super.setDrawable(new Image(texture).getDrawable());  // Use the texture for Image's drawable
        this.setPosition(x, y);  // Ensure birds are positioned on creation

    }
    public void setPosition(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    // Getter for the texture
    public Texture getTexture() {
        return texture;
    }

    // You can add a method to directly set the position later
    public void setBirdPosition(float x, float y) {
        this.setPosition(x, y);
    }


    @Override
    public boolean remove() {
        boolean removed = super.remove();  // Call the superclass remove() method, which returns a boolean
        if (texture != null) {
            texture.dispose();  // Dispose texture when removing the object
        }
        return removed;  // Return the result from the superclass remove()
    }
}
