package io.github.birds.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.birds.MyGame;

public class WinScreen implements Screen {
    private final MyGame game;
    private Texture winBackground;
    private Texture backButton;  // Add back button

    public WinScreen(final MyGame game) {
        this.game = game;
        winBackground = new Texture("winscreen.png"); // Add to assets folder
        backButton = new Texture("Back@4x.png");         // Add back button texture to assets
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = game.getBatch();
        batch.begin();
        batch.draw(winBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Draw back button at the bottom-left corner
        batch.draw(backButton, 10, 10, 80, 60);

        batch.end();

        // Handle input for back button
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();

            // Check if back button is clicked
            if (x >= 10 && x <= 110 && y >= 10 && y <= 70) {
                game.setScreen(new GameScreen(game, 1));  // Go back to GameScreen with level 1 (or whichever level)
                System.out.println("Back button clicked on WinScreen");
            }
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
        winBackground.dispose();
        backButton.dispose();  // Dispose of back button texture
    }
}
