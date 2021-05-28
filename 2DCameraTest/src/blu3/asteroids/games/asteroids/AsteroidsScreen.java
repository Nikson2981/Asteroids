package blu3.asteroids.games.asteroids;

import blu3.asteroids.Main;
import blu3.asteroids.games.asteroids.asteroids.Asteroid;
import blu3.asteroids.games.asteroids.player.Bullet;
import blu3.asteroids.games.asteroids.player.Player;
import blu3.asteroids.renderer.Renderer;
import blu3.asteroids.renderer.Textures;

import java.awt.*;
import java.util.Random;


public class AsteroidsScreen {

    public static int score = 0, DP_EXPLOSION_OFFSET = 0; // my naming conventions suddenly changed here
    public static double DP_posOffset = 0;

    public static void drawWorld() {
        Random random = new Random();
        for (Asteroid asteroid : Game.asteroids) {
            Renderer.drawTexture(asteroid.getTexture(), (asteroid.getX() - asteroid.getSize() / 2), (asteroid.getY() - asteroid.getSize() / 2), asteroid.getSize(), asteroid.getSize(), (asteroid.getRotation()));
        }
        for (Bullet bullet : Game.bullets) {
            Renderer.drawTexture(Textures.BULLET, bullet.getX() - 4, bullet.getY() - 4, 8, 8, random.nextInt(360));
        }

        if (Player.alive) {
            Renderer.drawTexture(Textures.PLAYER, Player.getX() - 16, Player.getY() - 16, 32, 32, (int) Player.rot);
            DP_posOffset = 0;
            DP_EXPLOSION_OFFSET = 0;
        } else {
            DP_posOffset += 0.1;
            DP_EXPLOSION_OFFSET++;
            double angle = Math.toRadians(Player.rot - 90);
            Renderer.drawTexture(Textures.P_DEAD1, (int) (Player.getX() - 16 - DP_posOffset * Math.cos(angle)), (int) (Player.getY() - 16 - DP_posOffset * Math.sin(angle)), 32, 32, (int) Player.rot);
            Renderer.drawTexture(Textures.P_DEAD2, (int) (Player.getX() - 16 + DP_posOffset * Math.cos(angle)), (int) (Player.getY() - 16 - DP_posOffset * Math.sin(angle)), 32, 32, (int) Player.rot);
            Renderer.drawTexture(Textures.P_DEAD3, (int) (Player.getX() - 16 - DP_posOffset * Math.cos(angle)), (int) (Player.getY() + 16 - DP_posOffset * Math.sin(angle)), 32, 32, (int) Player.rot);
            if (DP_posOffset <= 5) {
                Renderer.drawTexture(Textures.P_EXP1, Player.getX() - 16 - (DP_EXPLOSION_OFFSET / 2), Player.getY() - 16 - (DP_EXPLOSION_OFFSET / 2), 32 + DP_EXPLOSION_OFFSET, 32 + DP_EXPLOSION_OFFSET, random.nextInt(360));
            }
        }

        if (Main.NODEBUG) return;

        for (Asteroid asteroid : Game.asteroids) {
            Renderer.drawRect(asteroid.getX() - asteroid.getSize() / 2, asteroid.getY() - asteroid.getSize() / 2, asteroid.getX() + asteroid.getSize() / 2, asteroid.getY() + asteroid.getSize() / 2, 0xAA00FFFF);
        }
        for (Bullet bullet : Game.bullets) {
            Renderer.drawRect(bullet.getX() - 4, bullet.getY() - 4, bullet.getX() + 4, bullet.getY() + 4, 0xFF00FFFF);
        }
        Renderer.drawRect(Player.getX() - 16, Player.getY() - 16, Player.getX() + 16, Player.getY() + 16, 0xFFFF0000);
        Renderer.drawString("Debug mode enabled! Click to exit.", Main.WIDTH / 2 - 180, 30, Color.WHITE);

        Renderer.drawString(Player.mX + ", " + Player.mY, Main.WIDTH / 2 - 180, 60, Color.WHITE);

    }

    public static void drawHUD() {
        if (score < Game.score) score++;
        if (score > Game.score) score -= 5;
        Renderer.drawString("Level " + Game.level, Main.WIDTH - 150, 30, Color.WHITE);
        Renderer.drawString("Score: " + score, Main.WIDTH - 150, 60, Color.WHITE);

        if (!Player.alive)
            Renderer.drawString(Game.resetTicks + "", 30, 60, Color.WHITE);
        if (Player.I_FRAMES > 0)
            Renderer.drawString(Player.I_FRAMES + "", Player.getX() - 13, Player.getY() - 20, Color.WHITE);
    }
}
