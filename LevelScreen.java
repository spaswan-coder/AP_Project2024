package io.github.birds.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.birds.MyGame;

public class LevelScreen implements Screen {
    private final MyGame game;
    private Texture level1Button;
    private Texture level2Button;
    private Texture level3Button;
    private Texture levelBack;  // Background texture for the level screen

    public LevelScreen(final MyGame game) {
        this.game = game;
        level1Button = new Texture("Level-1@4x.png");
        level2Button = new Texture("Level-2@4x.png");
        level3Button = new Texture("Level-Lock@4x.png");
        levelBack = new Texture("levelback.png"); // Ensure this is in the assets folder
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SpriteBatch batch = game.getBatch();
        batch.begin();

        // Draw the background
        batch.draw(levelBack, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Define button sizes
        float buttonWidth = 150;
        float buttonHeight = 80;

        // Define the Y-coordinate (common for all buttons)
        float yPosition = Gdx.graphics.getHeight() / 2 - buttonHeight / 2;

        // Calculate X-coordinates for buttons, centering them horizontally with spacing
        float spacing = 50; // Space between buttons
        float totalWidth = buttonWidth * 3 + spacing * 2;
        float startX = (Gdx.graphics.getWidth() - totalWidth) / 2;

        // Draw the buttons in a horizontal line
        batch.draw(level1Button, startX, yPosition, buttonWidth, buttonHeight);
        batch.draw(level2Button, startX + buttonWidth + spacing, yPosition, buttonWidth, buttonHeight);
        batch.draw(level3Button, startX + 2 * (buttonWidth + spacing), yPosition, buttonWidth, buttonHeight);

        batch.end();

        // Handle button clicks
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY(); // Y-axis is inverted in LibGDX

            // Check if Level 1 button is clicked
            if (x >= startX && x <= startX + buttonWidth &&
                y >= yPosition && y <= yPosition + buttonHeight) {
                game.setScreen(new GameScreen(game, 1)); // Navigate to GameScreen for level 1
                System.out.println("Level 1 button clicked");
            }

            // Check if Level 2 button is clicked
            if (x >= startX + buttonWidth + spacing && x <= startX + 2 * buttonWidth + spacing &&
                y >= yPosition && y <= yPosition + buttonHeight) {
                game.setScreen(new GameScreen(game, 2)); // Navigate to GameScreen for level 2
                System.out.println("Level 2 button clicked");
            }

            // Check if Level 3 button is clicked
            if (x >= startX + 2 * (buttonWidth + spacing) && x <= startX + 3 * buttonWidth + 2 * spacing &&
                y >= yPosition && y <= yPosition + buttonHeight) {
               // System.out.println("Level 3 button clicked");
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
        level1Button.dispose();
        level2Button.dispose();
        level3Button.dispose();
        levelBack.dispose();
    }
}
