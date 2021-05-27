package blu3.asteroids.games.asteroids;

import blu3.asteroids.Logger;
import blu3.asteroids.Main;
import blu3.asteroids.audio.Sounds;
import blu3.asteroids.games.asteroids.asteroids.Asteroid;
import blu3.asteroids.games.asteroids.asteroids.AsteroidBig;
import blu3.asteroids.games.asteroids.asteroids.AsteroidMedium;
import blu3.asteroids.games.asteroids.player.Bullet;
import blu3.asteroids.games.asteroids.player.Player;
import blu3.asteroids.renderer.Textures;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

public class Game { // spaghetti code i know shut up

    public static List<Asteroid> asteroids = new ArrayList<>();
    public static List<Bullet> bullets = new ArrayList<>(); // fuck this arraylist in particular

    public static int level = 1, score = 0, resetTicks = 120;

    public static void hardReset() {
        level = 1;
        score = 0;
        startGame();
        AsteroidsScreen.score = 0;
    }

    public static void startGame() {
        asteroids.clear();
        Sounds.NEW_WAVE.play();
        Player.reset();
        Random random = new Random();
        for (int i = 0; i < 3 + (level * 2); i++) {
            AsteroidBig asteroid = new AsteroidBig(random.nextInt(Main.WIDTH), random.nextInt(Main.HEIGHT), (random.nextInt(4) + 1) * (random.nextBoolean() ? -1 : 1), (random.nextInt(4) + 1) * (random.nextBoolean() ? -1 : 1));
            asteroid.setTexture(random.nextBoolean() ? Textures.ASTEROID1 : Textures.ASTEROID2);
            asteroid.setRot(random.nextInt(360));
            asteroids.add(asteroid);
        }
    }

    public static void drawScreen() {
        AsteroidsScreen.drawWorld();
        AsteroidsScreen.drawHUD();
    }

    public static void update() {
        if (!Player.alive) {
            resetTicks--;
            score = 0;
            if (resetTicks <= 0) {
                hardReset();
                resetTicks = 120;
            }
        }

        Player.updatePosition();
        Player.I_FRAMES--;
        Random random = new Random();
        try {
            for (Asteroid asteroid : asteroids) {
                asteroid.move();
                if (Player.collision(asteroid) && Player.I_FRAMES < 0 && Player.alive) {
                    Player.alive = false;
                    Sounds.playRandomExplosionSound();
                }
            }
            for (Bullet bullet : bullets) {
                if (bullet.offScreen()) {
                    bullets.remove(bullet);
                    continue;
                }
                bullet.update();
            }
            for (Asteroid asteroid : asteroids) {
                for (Bullet bullet : bullets) {
                    if (asteroid.shot(bullet)) { // ew
                        if (asteroid instanceof AsteroidBig) {
                            AsteroidMedium one = new AsteroidMedium(asteroid.getX(), asteroid.getY(), asteroid.mX() / 2, asteroid.mY() / 2);
                            one.setTexture(random.nextBoolean() ? Textures.ASTEROID1 : Textures.ASTEROID2);
                            one.setRot(random.nextInt(360));

                            AsteroidMedium two = new AsteroidMedium(asteroid.getX(), asteroid.getY(), (random.nextInt(2) + 1) * (random.nextBoolean() ? -1 : 1), (random.nextInt(2) + 1) * (random.nextBoolean() ? -1 : 1));
                            two.setTexture(random.nextBoolean() ? Textures.ASTEROID1 : Textures.ASTEROID2);
                            two.setRot(random.nextInt(360));

                            asteroids.remove(asteroid);
                            asteroids.add(one); // individually do the same steps for each new asteroid for some reason
                            asteroids.add(two); // why can't we have nice things?
                            score += 50;
                        } else {
                            asteroids.remove(asteroid);
                            score += 100;
                        }
                        bullets.remove(bullet);
                    }
                }
            }
        } catch (ConcurrentModificationException ignored) { // i will never
            // ever
        }
// think about this exception again
        if (asteroids.isEmpty()) {
            level++;
            startGame();
            Logger.INFO("Level " + (level - 1) + " completed! Beginning level " + level + "!");
            Logger.INFO("Spawning " + (3 + (level * 2)) + " asteroids!");
        }
    }

    public static void spawnBullet() {
        if (!Player.alive) return;
        if (bullets.size() < 4) {
            Sounds.playRandomShootSound(); // ok
            Bullet bullet = new Bullet();
            bullet.center();
            bullet.calculateAngle();
            bullets.add(bullet);
        }
    }
}
