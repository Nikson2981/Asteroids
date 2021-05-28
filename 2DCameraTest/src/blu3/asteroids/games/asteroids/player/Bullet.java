package blu3.asteroids.games.asteroids.player;

import blu3.asteroids.Main;

public class Bullet {

    public double mX = 0, mY = 0, x = 0, y = 0;

    public void update() {
        x += mX;
        y += mY;
    }

    public boolean offScreen() {
        return x < 0 || x > Main.WIDTH || y < 0 || y > Main.HEIGHT;
    }

    public void center() {
        x = Player.getX();
        y = Player.getY();
    }

    public void calculateAngle() {
        mX = Math.sin(Math.toRadians(Player.rot)) * 20; // * 20? why? must mean something clearly because it works
        mY = -Math.cos(Math.toRadians(Player.rot)) * 20; // but i have no memory of ever doing that
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }
}


