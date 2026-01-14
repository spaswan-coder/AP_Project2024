package io.github.birds;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Pig {
    private Texture texture;
    private float x, y, width, height;
    private float rotation;
    private int health;
    private boolean destroyed;
    private static final int MAX_HEALTH = 100;
    private static final float DAMAGE_THRESHOLD = 200f;

    public Pig(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = 0;
        this.health = MAX_HEALTH;
        this.destroyed = false;
    }

    public void draw(SpriteBatch batch) {
        if (!destroyed) {
            batch.draw(texture, x, y, width / 2, height / 2, width, height, 1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
        }
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void rotate(float angle) {
        this.rotation += angle;
        if (this.rotation >= 360) this.rotation -= 360;
    }

    public float getRotation() {
        return rotation;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void takeDamage(float impactVelocity) {
        if (destroyed) return;

        if (impactVelocity > DAMAGE_THRESHOLD) {
            int damage = (int) ((impactVelocity - DAMAGE_THRESHOLD) / 10);
            health -= damage;

            if (health <= 0) {
                destroyed = true;
                health = 0;
            }
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public int getHealth() {
        return health;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
