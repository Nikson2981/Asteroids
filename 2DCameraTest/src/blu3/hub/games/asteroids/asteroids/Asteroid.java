package blu3.hub.games.asteroids.asteroids;

import blu3.hub.games.asteroids.player.Bullet;
import blu3.hub.renderer.Texture;

public abstract class Asteroid {
    private Texture texture;
    private double rot;

    public abstract void move();

    public abstract int getX();

    public abstract int getY();

    public abstract int getSize();

    public int getRotation() {
        return (int) (rot += 0.1);
    }

    public boolean shot(Bullet bullet) {
        return (bullet.getX() > getX() - getSize() / 2 && bullet.getX() < getX() + getSize() / 2) && (bullet.getY() > getY() - getSize() / 2 && bullet.getY() < getY() + getSize() / 2);
    }

    public void setRot(int rot) {
        this.rot = rot;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public abstract int mX();

    public abstract int mY();

}
