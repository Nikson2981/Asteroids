package blu3.hub.games.asteroids.asteroids;

import blu3.hub.Main;

public class AsteroidMedium extends Asteroid {

    private int x, y;
    private final int motionX, motionY;

    public AsteroidMedium(int startX, int startY, int motionX, int motionY) {
        this.x = startX;
        this.y = startY;
        this.motionX = motionX;
        this.motionY = motionY;
    }

    @Override
    public int getSize() {
        return 32;
    }

    public void move() {
        x += motionX;
        y += motionY;
        checkWrap();
    }

    public void checkWrap() {
        if (x < 0) x = Main.WIDTH;
        if (x > Main.WIDTH) x = 0;
        if (y < 0) y = Main.HEIGHT;
        if (y > Main.HEIGHT) y = 0;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int mX() {
        return motionX;
    }

    @Override
    public int mY() {
        return motionY;
    }
}