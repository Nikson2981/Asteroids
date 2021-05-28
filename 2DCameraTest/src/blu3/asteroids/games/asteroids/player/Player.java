package blu3.asteroids.games.asteroids.player;

import blu3.asteroids.Main;
import blu3.asteroids.games.asteroids.asteroids.Asteroid;

public class Player { // all of this code is awful

    public static double x = Main.WIDTH / 2.0, y = Main.HEIGHT / 2.0, rot, mX, mY;
    public static int I_FRAMES = 80;
    public static boolean alive = true, thrust;

    public static void reset() {
        x = Main.WIDTH / 2.0;
        y = Main.HEIGHT / 2.0;
        rot = 0;
        I_FRAMES = 80;
        alive = true;
        mX = 0;
        mY = 0;
    }

    public static void updateMoveSpeed(boolean thrust) {
        Player.thrust = thrust;
    }

    public static void updatePosition() {
        if (!alive) return;
        move();
        checkWrap();
    }

    public static void move() {
        if (!alive) return;
        double acceleration = 0.35;
        double velocityDecay = 0.98;
        double angle = Math.toRadians(rot - 90);
        if (angle > (2 * Math.PI))
            angle -= (2 * Math.PI);
        else if (angle < 0)
            angle += (2 * Math.PI);
        if (thrust) {
            mX += acceleration * Math.cos(angle);
            mY += acceleration * Math.sin(angle);
        }
        x += mX;
        y += mY;
        mX *= velocityDecay;
        mY *= velocityDecay;
    }

    public static boolean collision(Asteroid asteroid) {
        return (Player.getX() + 16 > asteroid.getX() - asteroid.getSize() / 2) && (Player.getX() - 16 < asteroid.getX() + asteroid.getSize() / 2) && (Player.getY() + 16 > asteroid.getY() - asteroid.getSize() / 2) && (Player.getY() - 16 < asteroid.getY() + asteroid.getSize() / 2);
    }

    private static void checkWrap() {
        if (x < 0) x = Main.WIDTH;
        if (x > Main.WIDTH) x = 0;
        if (y < 0) y = Main.HEIGHT;
        if (y > Main.HEIGHT) y = 0;
    }

    public static int getX() {
        return (int) x;
    }

    public static int getY() {
        return (int) y;
    }
}
