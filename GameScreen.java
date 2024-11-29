package io.github.birds.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import io.github.birds.Bird;
import io.github.birds.MyGame;
import io.github.birds.Pig;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
    private final MyGame game;
    private Texture gameBackground;
    private Texture pauseButton;
    private Texture resumeButton;
    private Texture exitButton;
    private Texture restartButton;
    private Texture winButton;
    private Texture loseButton;
    private Texture slingshot;
    private boolean isPaused;
    private int level;

    private Bird bird1, bird2, bird3; // Birds to be placed on the screen
    private Texture angry1, angry2, angry3, background; // Textures for the birds

    private List<Block> blocks; // List to hold the blocks
    private Texture block1Texture, block2Texture; // Textures for the blocks

    private Pig pig1, pig2, pig3, pig4; // Pigs to be placed on the blocks
    private Texture pigTexture; // Texture for the pig

    private Bird selectedBird = null;
    private Vector2 touchOffset = new Vector2();

    public GameScreen(final MyGame game, int level) {
        this.game = game;
        this.level = level;

        // Load textures
        gameBackground = new Texture("Background.png");
        pauseButton = new Texture("Pause@4x.png");
        resumeButton = new Texture("pauseg.png");
        exitButton = new Texture("quit.png");
        restartButton = new Texture("Restart@4x.png");
        winButton = new Texture("win.png");
        loseButton = new Texture("lose.png");
        slingshot = new Texture("Slingshot.png");

        // Load the bird textures
        angry1 = new Texture("angry1.png");
        angry2 = new Texture("angry2.png");
        angry3 = new Texture("birds3.png");
        background = new Texture("Background.png"); // Background text

        // Place birds at the center of the screen
        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;

        bird1 = new Bird("angry1.png", centerX - 150, centerY);  // First bird at left of center
        bird2 = new Bird("angry2.png", centerX, centerY);        // Second bird at center
        bird3 = new Bird("birds3.png", centerX + 150, centerY);  // Third bird at right of center


        // Load the block textures
        block1Texture = new Texture("blocl1.png");
        block2Texture = new Texture("block2.png");

        // Initialize blocks
        float blockWidth = 85;  // Custom width for block1
        float blockHeight = 230; // Custom height for block1
        float startY = Gdx.graphics.getHeight() - 150;



        blocks = new ArrayList<>();
        blocks.add(new Block(block1Texture, 700, startY, blockWidth, blockHeight)); // Adjusted size for block1
        blocks.add(new Block(block2Texture, 700 + blockWidth + 10, startY));       // Default size for block2

        // Load pig texture
        pigTexture = new Texture("PIG4.png"); // Your pig texture path

        // Initialize pigs and set their positions (right side of the ground corner)
        float pigWidth = 60;  // Pig size
        float pigHeight = 60; // Pig size
        float baseX = Gdx.graphics.getWidth() - 500; // Right-side corner
        float baseY = 3; // Ground level height offset

// Arrange pigs vertically at the right side
        pig1 = new Pig(pigTexture, baseX+40, baseY+pigHeight+360, pigWidth, pigHeight); // Bottom-most pig
        pig2 = new Pig(pigTexture, baseX+96, baseY + pigHeight + 228, pigWidth, pigHeight); // Second pig
        pig3 = new Pig(pigTexture, baseX-60, baseY + 2 * (pigHeight + 35), pigWidth, pigHeight); // Third pig
        pig4 = new Pig(pigTexture, baseX-20, baseY + 6 * (pigHeight + 10), pigWidth, pigHeight); // Top-most pig
        isPaused = false;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = game.getBatch();
        batch.begin();

        batch.draw(gameBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Define the size for all birds
        float birdWidth = 95;
        float birdHeight = 95;

        // Define positions for the birds
        float bird3X = -20;
        float bird3Y = 180;

        float bird2X = -90 + birdWidth + 15;
        float bird2Y = 180;

        float bird1X = -150 + 2 * (birdWidth + 15);
        float bird1Y = 180;



        // Draw the birds at their respective positions and scale them to the same size
        batch.draw(angry1, bird1X, bird1Y, birdWidth, birdHeight);
        batch.draw(angry2, bird2X, bird2Y, birdWidth, birdHeight);
        batch.draw(angry3, bird3X, bird3Y, birdWidth, birdHeight);


        // Render birds using the Bird class
        bird1.draw(batch, 1);
        bird2.draw(batch, 1);
        bird3.draw(batch, 1);

        // Update and draw blocks
        for (Block block : blocks) {
            block.update(delta); // Make the blocks fall
            block.draw(batch);
        }
        // Rotate pigs dynamically (add rotation logic here)
        pig1.rotate(-1);  // Rotate pig1 by 1 degree per frame
        pig2.rotate(-2);  // Rotate pig2 by 2 degrees per frame
        pig3.rotate(-1.5f); // Rotate pig3 by 1.5 degrees per frame
        pig4.rotate(-1);  // Rotate pig4 by 3 degrees per frame
        // Draw the pigs
        pig1.draw(batch);
        pig2.draw(batch);
        pig3.draw(batch);
        pig4.draw(batch);

        // Draw the slingshot
        batch.draw(slingshot, 150, 200, 90, 130);

        if (!isPaused) {
            batch.draw(pauseButton, 10, Gdx.graphics.getHeight() - 70, 60, 60);
        } else {
            batch.draw(resumeButton, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 40, 200, 80);
            batch.draw(exitButton, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 60, 200, 80);
            batch.draw(restartButton, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 160, 200, 80);
        }

        batch.draw(winButton, Gdx.graphics.getWidth() - 110, Gdx.graphics.getHeight() - 70, 100, 60);
        batch.draw(loseButton, Gdx.graphics.getWidth() - 110, 10, 100, 60);



        batch.end();

        handleInput();

    }



    private void handleInput() {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();


            // Check for bird selection first
            Rectangle bird1Rect = new Rectangle(-150 + 2 * (95 + 15), 180, 95, 95);
            Rectangle bird2Rect = new Rectangle(-90 + 95 + 15, 180, 95, 95);
            Rectangle bird3Rect = new Rectangle(-20, 180, 95, 95);

            if (bird1Rect.contains(x, y)) {
                selectedBird = bird1;
                touchOffset.set(x - bird1Rect.x, y - bird1Rect.y);
            } else if (bird2Rect.contains(x, y)) {
                selectedBird = bird2;
                touchOffset.set(x - bird2Rect.x, y - bird2Rect.y);
            } else if (bird3Rect.contains(x, y)) {
                selectedBird = bird3;
                touchOffset.set(x - bird3Rect.x, y - bird3Rect.y);
            }

            Rectangle pauseRect = new Rectangle(10, Gdx.graphics.getHeight() - 70, 60, 60);
            Rectangle resumeRect = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 40, 200, 80);
            Rectangle exitRect = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 60, 200, 80);
            Rectangle restartRect = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 160, 200, 80);

            if (!isPaused && pauseRect.contains(x, y)) {
                isPaused = true;
            } else if (isPaused && resumeRect.contains(x, y)) {
                isPaused = false;
            } else if (isPaused && exitRect.contains(x, y)) {
                game.setScreen(new PlayExitScreen(game));
                dispose();
            } else if (isPaused && restartRect.contains(x, y)) {
                game.setScreen(new GameScreen(game, level));
                dispose();
            } else if (new Rectangle(Gdx.graphics.getWidth() - 110, Gdx.graphics.getHeight() - 70, 100, 60).contains(x, y)) {
                game.setScreen(new WinScreen(game));
                dispose();
            } else if (new Rectangle(Gdx.graphics.getWidth() - 110, 10, 100, 60).contains(x, y)) {
                game.setScreen(new LoseScreen(game));
                dispose();
            }
        }
        // Bird dragging
        if (Gdx.input.isTouched() && selectedBird != null) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();

            // Update bird position based on touch
            float newX = x - touchOffset.x;
            float newY = y - touchOffset.y;

            // Optional: Add boundaries to restrict bird movement
            newX = Math.max(0, Math.min(newX, Gdx.graphics.getWidth() - 95));
            newY = Math.max(0, Math.min(newY, Gdx.graphics.getHeight() - 95));

            selectedBird.setPosition(newX, newY);
        }

        // Reset bird selection when touch is released
        if (!Gdx.input.isTouched()) {
            selectedBird = null;
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
        if (gameBackground != null) gameBackground.dispose();
        if (pauseButton != null) pauseButton.dispose();
        if (resumeButton != null) resumeButton.dispose();
        if (exitButton != null) exitButton.dispose();
        if (restartButton != null) restartButton.dispose();
        if (winButton != null) winButton.dispose();
        if (loseButton != null) loseButton.dispose();
        if (slingshot != null) slingshot.dispose();

        if (angry1 != null) angry1.dispose();
        if (angry2 != null) angry2.dispose();
        if (angry3 != null) angry3.dispose();
        if (background != null) background.dispose();
        pigTexture.dispose();

        if (block1Texture != null) block1Texture.dispose();
        if (block2Texture != null) block2Texture.dispose();
        for (Block block : blocks) {
            block.dispose();

//            // Dispose pigs
//            pig1.dispose();
//            pig2.dispose();
//            pig3.dispose();
//            pig4.dispose();
        }
        }
    }

    // Inner Block class
    class Block {
        private Texture texture;
        private float x, y;
        private float speed = 500; // Falling speed
        float width = 90, height = 100; // Default block size, now public for easy adjustment

        public Block(Texture texture, float x, float y) {
            this.texture = texture;
            this.x = x;
            this.y = y;
        }

        public Block(Texture texture, float x, float y, float width, float height) {
            this(texture, x, y);
            this.width = width;
            this.height = height;
        }

        public void update(float delta) {
            y -= speed * delta;
            if (y < 195) y = 195; // Stop falling at the ground
        }

        public void draw(SpriteBatch batch) {
            batch.draw(texture, x, y, width, height);
        }

        public void dispose() {
            if (texture != null) texture.dispose();
        }
    }

