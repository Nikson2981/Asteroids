package blu3.hub.games.asteroids.player;

import blu3.hub.Main;

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
        mX = Math.sin(Math.toRadians(Player.rot)) * 20;
        mY = -Math.cos(Math.toRadians(Player.rot)) * 20;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }
}


