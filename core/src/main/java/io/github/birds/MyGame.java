package io.github.birds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World; // Import World from Box2D

public class MyGame extends Game {
    private SpriteBatch batch;
    private Texture homeScreenBackground;
    private World world; // Add a World object for physics

    @Override
    public void create() {
        batch = new SpriteBatch();
        homeScreenBackground = new Texture("Homescreen.png");

        // Initialize the physics world
        world = new World(new com.badlogic.gdx.math.Vector2(0, -9.8f), true); // Gravity set to Earth-like gravity

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
        world.dispose(); // Dispose the world when done
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Texture getHomeScreenBackground() {
        return homeScreenBackground;
    }

    // Provide access to the world
    public World getWorld() {
        return world;
    }
}
