package io.github.birds.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.birds.Block;
import io.github.birds.MyGame;

import java.awt.Rectangle;

public class GameScreen implements Screen {
    private final MyGame game;
    private Texture gameBackground;
    private Texture pauseButton;
    private Texture resumeButton;
    private Texture exitButton;
    private Texture restartButton;
    private Texture winButton;
    private Texture loseButton;
    private boolean isPaused;
    private int level;

    private Block block;

    public GameScreen(final MyGame game, int level) {
        this.game = game;
        this.level = level;

        gameBackground = new Texture("Gamescreen2.png");
        pauseButton = new Texture("Pause@4x.png");
        resumeButton = new Texture("pauseg.png");
        exitButton = new Texture("quit.png");
        restartButton = new Texture("Restart@4x.png");
        winButton = new Texture("win.png");
        loseButton = new Texture("lose.png");

        isPaused = false;
        block = new Block("Block.png", 200, 150, 60, 30);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = game.getBatch();
        batch.begin();

        batch.draw(gameBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (!isPaused) {
            batch.draw(pauseButton, 10, Gdx.graphics.getHeight() - 70, 60, 60);
        } else {
            batch.draw(resumeButton, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 40, 200, 80);
            batch.draw(exitButton, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 60, 200, 80);
            batch.draw(restartButton, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 160, 200, 80);
        }

        batch.draw(winButton, Gdx.graphics.getWidth() - 110, Gdx.graphics.getHeight() - 70, 100, 60);
        batch.draw(loseButton, Gdx.graphics.getWidth() - 110, 10, 100, 60);
        block.render(batch);

        batch.end();

        handleInput();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();

            Rectangle pauseRect = new Rectangle(10, Gdx.graphics.getHeight() - 70, 60, 60);
            Rectangle resumeRect = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 40, 200, 80);
            Rectangle exitRect = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 60, 200, 80);
            Rectangle restartRect = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 160, 200, 80);

            if (!isPaused && pauseRect.contains(x, y)) {
                isPaused = true;
            } else if (isPaused && resumeRect.contains(x, y)) {
                isPaused = false;
            } else if (isPaused && exitRect.contains(x, y)) {
                game.setScreen(new PlayExitScreen(game)); // Switch to PlayExitScreen
                dispose();
            } else if (isPaused && restartRect.contains(x, y)) {
                game.setScreen(new GameScreen(game, level)); // Restart the game
                dispose();
            } else if (new Rectangle(Gdx.graphics.getWidth() - 110, Gdx.graphics.getHeight() - 70, 100, 60).contains(x, y)) {
                game.setScreen(new WinScreen(game));
                dispose();
            } else if (new Rectangle(Gdx.graphics.getWidth() - 110, 10, 100, 60).contains(x, y)) {
                game.setScreen(new LoseScreen(game));
                dispose();
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
        gameBackground.dispose();
        pauseButton.dispose();
        resumeButton.dispose();
        exitButton.dispose();
        restartButton.dispose();
        winButton.dispose();
        loseButton.dispose();
        block.dispose();
    }
}
