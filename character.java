package io.github.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Bird extends Image {
    public Bird(String texturePath, float x, float y) {
        super(new Texture(Gdx.files.internal(texturePath)));
        this.setPosition(x, y);
        //birds added
        
    }
}

