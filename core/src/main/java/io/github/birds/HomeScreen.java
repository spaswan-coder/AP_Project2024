package io.github.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HomeScreen implements Screen {
    private final MyGame game;
    private Texture background;

    public HomeScreen(final MyGame game) {
        this.game = game;
        background = new Texture("Homescreen.png"); // Ensure this file is in the assets folder
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SpriteBatch batch = game.getBatch();
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Handle click input to navigate to PlayExitScreen
        if (Gdx.input.justTouched()) {
            game.setScreen(new io.github.birds.Screen.PlayExitScreen(game));
            System.out.println("Navigating to PlayExitScreen");
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        background.dispose();
    }
}
