package io.github.birds.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.birds.MyGame;

public class HomeScreen implements Screen {
    private final MyGame game;

    public HomeScreen(final MyGame game) {
        this.game = game;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear the screen

        SpriteBatch batch = game.getBatch();
        batch.begin();

        // Draw the background image
        batch.draw(game.getHomeScreenBackground(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.end();

        // Check if the screen is touched or clicked
        if (Gdx.input.justTouched()) {
            game.setScreen(new PlayExitScreen(game)); // Move to the screen with play and exit buttons
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
