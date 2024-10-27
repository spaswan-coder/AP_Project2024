package io.github.birds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.birds.Screen.HomeScreen;

public class MyGame extends Game {
    private SpriteBatch batch;
    private Texture homeScreenBackground;

    @Override
    public void create() {
        batch = new SpriteBatch();
        homeScreenBackground = new Texture("Homescreen.png"); // Add this file to the assets folder

        // Start with the HomeScreen (displays only the background image initially)
        setScreen(new HomeScreen(this));
    }

    @Override
    public void render() {
        super.render(); // Delegates rendering to the active screen
    }

    @Override
    public void dispose() {
        batch.dispose();
        homeScreenBackground.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Texture getHomeScreenBackground() {
        return homeScreenBackground;
    }
}
