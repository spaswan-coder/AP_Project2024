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
    private Texture slingshot;
    private boolean isPaused;
    private int level;

    private List<Bird> birds;
    private Bird currentBird;
    private int currentBirdIndex = 0;

    private List<Block> blocks;
    private Texture block1Texture, block2Texture;

    private List<Pig> pigs;
    private Texture pigTexture;

    private static final float SLINGSHOT_X = 200f;
    private static final float SLINGSHOT_Y = 240f;
    private static final float MAX_DRAG_DISTANCE = 120f;
    private static final float LAUNCH_MULTIPLIER = 4.5f;

    private boolean isDragging = false;
    private Vector2 dragStart = new Vector2();
    private Vector2 dragCurrent = new Vector2();

    private float timeSinceLastBird = 0;
    private static final float BIRD_SWITCH_DELAY = 2.0f;

    private boolean gameEnded = false;

    public GameScreen(final MyGame game, int level) {
        this.game = game;
        this.level = level;

        gameBackground = new Texture("Background.png");
        pauseButton = new Texture("Pause@4x.png");
        resumeButton = new Texture("pauseg.png");
        exitButton = new Texture("quit.png");
        restartButton = new Texture("Restart@4x.png");
        slingshot = new Texture("Slingshot.png");

        block1Texture = new Texture("blocl1.png");
        block2Texture = new Texture("block2.png");
        pigTexture = new Texture("PIG4.png");

        initializeBirds();
        initializeBlocks();
        initializePigs();

        currentBird = birds.get(0);
        currentBird.setState(Bird.BirdState.IDLE);
    }

    private void initializeBirds() {
        birds = new ArrayList<>();
        birds.add(new Bird("angry1.png", SLINGSHOT_X, SLINGSHOT_Y));
        birds.add(new Bird("angry2.png", SLINGSHOT_X - 100, SLINGSHOT_Y - 10));
        birds.add(new Bird("birds3.png", SLINGSHOT_X - 200, SLINGSHOT_Y - 20));

        for (Bird bird : birds) {
            bird.setSlingshotPosition(SLINGSHOT_X, SLINGSHOT_Y);
        }
    }

    private void initializeBlocks() {
        blocks = new ArrayList<>();
        float blockWidth = 85;
        float blockHeight = 230;
        float startY = Gdx.graphics.getHeight() - 150;

        blocks.add(new Block(block1Texture, 700, startY, blockWidth, blockHeight));
        blocks.add(new Block(block2Texture, 700 + blockWidth + 10, startY));
    }

    private void initializePigs() {
        pigs = new ArrayList<>();
        float pigWidth = 60;
        float pigHeight = 60;
        float baseX = Gdx.graphics.getWidth() - 500;
        float baseY = 3;

        pigs.add(new Pig(pigTexture, baseX + 40, baseY + pigHeight + 360, pigWidth, pigHeight));
        pigs.add(new Pig(pigTexture, baseX + 96, baseY + pigHeight + 228, pigWidth, pigHeight));
        pigs.add(new Pig(pigTexture, baseX - 60, baseY + 2 * (pigHeight + 35), pigWidth, pigHeight));
        pigs.add(new Pig(pigTexture, baseX - 20, baseY + 6 * (pigHeight + 10), pigWidth, pigHeight));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if (!isPaused && !gameEnded) {
            updateGame(delta);
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = game.getBatch();
        batch.begin();

        batch.draw(gameBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(slingshot, 150, 200, 90, 130);

        for (Block block : blocks) {
            block.draw(batch);
        }

        for (Pig pig : pigs) {
            pig.draw(batch);
        }

        for (Bird bird : birds) {
            bird.draw(batch, 1);
        }

        if (!isPaused) {
            batch.draw(pauseButton, 10, Gdx.graphics.getHeight() - 70, 60, 60);
        } else {
            batch.draw(resumeButton, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 40, 200, 80);
            batch.draw(exitButton, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 60, 200, 80);
            batch.draw(restartButton, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 160, 200, 80);
        }

        batch.end();

        if (!isPaused && !gameEnded) {
            handleInput();
        } else {
            handlePauseInput();
        }
    }

    private void updateGame(float delta) {
        for (Bird bird : birds) {
            bird.update(delta);
        }

        for (Block block : blocks) {
            block.update(delta);
        }

        for (Pig pig : pigs) {
            if (!pig.isDestroyed()) {
                pig.rotate(-1);
            }
        }

        checkCollisions();

        if (currentBird != null && currentBird.getState() == Bird.BirdState.LANDED) {
            timeSinceLastBird += delta;

            if (timeSinceLastBird >= BIRD_SWITCH_DELAY) {
                switchToNextBird();
                timeSinceLastBird = 0;
            }
        }

        checkWinLoseConditions();
    }

    private void checkCollisions() {
        for (Bird bird : birds) {
            if (bird.getState() != Bird.BirdState.LAUNCHED) continue;

            Rectangle birdBounds = bird.getBounds();

            for (Pig pig : pigs) {
                if (pig.isDestroyed()) continue;

                if (birdBounds.overlaps(pig.getBounds())) {
                    float impactVelocity = (float) Math.sqrt(
                        bird.getVelocityX() * bird.getVelocityX() +
                        bird.getVelocityY() * bird.getVelocityY()
                    );
                    pig.takeDamage(impactVelocity);

                    bird.setVelocity(bird.getVelocityX() * 0.5f, bird.getVelocityY() * 0.5f);
                }
            }
        }
    }

    private void checkWinLoseConditions() {
        if (gameEnded) return;

        boolean allPigsDead = true;
        for (Pig pig : pigs) {
            if (!pig.isDestroyed()) {
                allPigsDead = false;
                break;
            }
        }

        if (allPigsDead) {
            gameEnded = true;
            game.setScreen(new WinScreen(game));
            dispose();
            return;
        }

        boolean allBirdsUsed = true;
        for (Bird bird : birds) {
            if (!bird.hasBeenLaunched() || bird.getState() != Bird.BirdState.LANDED) {
                allBirdsUsed = false;
                break;
            }
        }

        if (allBirdsUsed && (currentBird == null || currentBird.getState() == Bird.BirdState.LANDED)) {
            gameEnded = true;
            game.setScreen(new LoseScreen(game));
            dispose();
        }
    }

    private void switchToNextBird() {
        currentBirdIndex++;
        if (currentBirdIndex < birds.size()) {
            currentBird = birds.get(currentBirdIndex);
            currentBird.resetToSlingshot();
            currentBird.setState(Bird.BirdState.IDLE);
        } else {
            currentBird = null;
        }
    }

    private void handleInput() {
        if (currentBird == null || currentBird.getState() == Bird.BirdState.LAUNCHED) {
            return;
        }

        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();

            Rectangle pauseRect = new Rectangle(10, Gdx.graphics.getHeight() - 70, 60, 60);
            if (pauseRect.contains(x, y)) {
                isPaused = true;
                return;
            }

            Rectangle birdBounds = currentBird.getBounds();
            if (birdBounds.contains(x, y)) {
                isDragging = true;
                dragStart.set(SLINGSHOT_X, SLINGSHOT_Y);
                currentBird.setState(Bird.BirdState.AIMING);
            }
        }

        if (isDragging && Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();

            dragCurrent.set(x, y);
            Vector2 dragVector = new Vector2(dragStart).sub(dragCurrent);

            if (dragVector.len() > MAX_DRAG_DISTANCE) {
                dragVector.nor().scl(MAX_DRAG_DISTANCE);
            }

            currentBird.setPosition(SLINGSHOT_X - dragVector.x, SLINGSHOT_Y - dragVector.y);
        }

        if (isDragging && !Gdx.input.isTouched()) {
            isDragging = false;

            Vector2 launchVector = new Vector2(dragStart).sub(currentBird.getX(), currentBird.getY());
            launchVector.scl(LAUNCH_MULTIPLIER);

            currentBird.launch(launchVector.x, launchVector.y);
        }
    }

    private void handlePauseInput() {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();

            Rectangle resumeRect = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 40, 200, 80);
            Rectangle exitRect = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 60, 200, 80);
            Rectangle restartRect = new Rectangle(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 160, 200, 80);

            if (resumeRect.contains(x, y)) {
                isPaused = false;
            } else if (exitRect.contains(x, y)) {
                game.setScreen(new PlayExitScreen(game));
                dispose();
            } else if (restartRect.contains(x, y)) {
                game.setScreen(new GameScreen(game, level));
                dispose();
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
        if (gameBackground != null) gameBackground.dispose();
        if (pauseButton != null) pauseButton.dispose();
        if (resumeButton != null) resumeButton.dispose();
        if (exitButton != null) exitButton.dispose();
        if (restartButton != null) restartButton.dispose();
        if (slingshot != null) slingshot.dispose();
        if (pigTexture != null) pigTexture.dispose();
        if (block1Texture != null) block1Texture.dispose();
        if (block2Texture != null) block2Texture.dispose();

        for (Block block : blocks) {
            block.dispose();
        }
    }

    class Block {
        private Texture texture;
        private float x, y;
        private float speed = 500;
        float width = 90, height = 100;

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
            if (y < 195) y = 195;
        }

        public void draw(SpriteBatch batch) {
            batch.draw(texture, x, y, width, height);
        }

        public Rectangle getBounds() {
            return new Rectangle(x, y, width, height);
        }

        public void dispose() {
            if (texture != null) texture.dispose();
        }
    }
}
