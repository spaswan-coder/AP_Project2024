package io.github.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Bird extends Image {
    public enum BirdState {
        IDLE,
        AIMING,
        LAUNCHED,
        LANDED
    }

    private Texture texture;
    private BirdState state;

    private float velocityX;
    private float velocityY;
    private static final float GRAVITY = -980f;
    private static final float GROUND_Y = 180f;
    private static final float DAMPING = 0.3f;
    private static final float MIN_VELOCITY = 50f;

    private float slingshotX;
    private float slingshotY;
    private boolean hasBeenLaunched = false;

    public Bird(String texturePath, float x, float y) {
        this.texture = new Texture(Gdx.files.internal(texturePath));
        super.setDrawable(new Image(texture).getDrawable());
        this.setPosition(x, y);
        this.state = BirdState.IDLE;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    public void setSlingshotPosition(float x, float y) {
        this.slingshotX = x;
        this.slingshotY = y;
    }

    public void setState(BirdState state) {
        this.state = state;
    }

    public BirdState getState() {
        return state;
    }

    public void launch(float vx, float vy) {
        this.velocityX = vx;
        this.velocityY = vy;
        this.state = BirdState.LAUNCHED;
        this.hasBeenLaunched = true;
    }

    public void update(float delta) {
        if (state == BirdState.LAUNCHED) {
            velocityY += GRAVITY * delta;

            setX(getX() + velocityX * delta);
            setY(getY() + velocityY * delta);

            if (getY() <= GROUND_Y) {
                setY(GROUND_Y);

                if (Math.abs(velocityY) > MIN_VELOCITY) {
                    velocityY = -velocityY * DAMPING;
                    velocityX *= DAMPING;
                } else {
                    velocityX = 0;
                    velocityY = 0;
                    state = BirdState.LANDED;
                }
            }

            if (Math.abs(velocityX) < MIN_VELOCITY && Math.abs(velocityY) < MIN_VELOCITY && getY() <= GROUND_Y + 5) {
                velocityX = 0;
                velocityY = 0;
                state = BirdState.LANDED;
            }

            if (getX() > Gdx.graphics.getWidth() + 100) {
                state = BirdState.LANDED;
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), 95, 95);
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocity(float vx, float vy) {
        this.velocityX = vx;
        this.velocityY = vy;
    }

    public boolean hasBeenLaunched() {
        return hasBeenLaunched;
    }

    public void resetToSlingshot() {
        setPosition(slingshotX, slingshotY);
        velocityX = 0;
        velocityY = 0;
        state = BirdState.IDLE;
    }

    @Override
    public boolean remove() {
        boolean removed = super.remove();
        if (texture != null) {
            texture.dispose();
        }
        return removed;
    }
}
