//package io.github.birds;

package io.github.birds.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.birds.MyGame;

public class PlayExitScreen implements Screen {
    private final MyGame game;
    private Texture playButton;
    private Texture exitButton;
    private BitmapFont titleFont;

    public PlayExitScreen(final MyGame game) {
        this.game = game;
        playButton = new Texture("playbutton1.png");
        exitButton = new Texture("exit (2).png");
        titleFont = new BitmapFont();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        // Clear the screen before rendering
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Start rendering with the batch
        SpriteBatch batch = game.getBatch();
        batch.begin();


        batch.draw(game.getHomeScreenBackground(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Draw the play button
        batch.draw(playButton, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2, 200, 80);

        // Draw the exit button
        batch.draw(exitButton, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 120, 200, 80);

        // End the batch rendering
        batch.end();

        // Handle button clicks
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX(); // X-coordinate of the touch
            int y = Gdx.graphics.getHeight() - Gdx.input.getY(); // Y-axis is inverted in LibGDX

            // Check if Play button is clicked
            if (x >= Gdx.graphics.getWidth() / 2 - 100 && x <= Gdx.graphics.getWidth() / 2 + 100 &&
                y >= Gdx.graphics.getHeight() / 2 && y <= Gdx.graphics.getHeight() / 2 + 80) {
                // Navigate to LevelScreen when Play button is clicked
                game.setScreen(new io.github.birds.Screen.LevelScreen(game));
                System.out.println("Play button clicked");
            }

            // Check if Exit button is clicked
            if (x >= Gdx.graphics.getWidth() / 2 - 100 && x <= Gdx.graphics.getWidth() / 2 + 100 &&
                y >= Gdx.graphics.getHeight() / 2 - 120 && y <= Gdx.graphics.getHeight() / 2 - 40) {
                Gdx.app.exit(); // Exit the game
                System.out.println("Exit button clicked");
            }
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
        // Dispose of assets to avoid memory leaks
        playButton.dispose();
        exitButton.dispose();
        titleFont.dispose();
    }
}
