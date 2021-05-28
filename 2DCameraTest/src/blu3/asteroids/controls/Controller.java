package blu3.asteroids.controls;

import blu3.asteroids.Main;
import blu3.asteroids.audio.Sounds;
import blu3.asteroids.games.asteroids.Game;
import blu3.asteroids.games.asteroids.player.Player;

public class Controller {

    public static boolean spaceDown = false, mouseDown = false;

    //------------------------------------------------------------------------------
    // Sorts through given input each tick and actually does things with it.
    // This needs major refactoring, however that can come at a later date.
    //------------------------------------------------------------------------------

    public void tick(boolean forward, boolean back, boolean left, boolean right, boolean mouse, boolean space) { // no moving backwards lol
        Player.updateMoveSpeed(forward);
        if (left && Player.alive) {
            Player.rot -= 3;
        }
        if (right && Player.alive) {
            Player.rot += 3;
        }
        if (!spaceDown && space) {
            Game.spawnBullet();
            spaceDown = true;
        }
        if (spaceDown && !space) spaceDown = false;

        if (!mouseDown && mouse) { // could have used a better button for this but... nope lol why would i EVER do something smart?
            Main.NODEBUG = !Main.NODEBUG;
            mouseDown = true;
            Sounds.BEEP.play();
        }
        if (mouseDown && !mouse) mouseDown = false;
    }
}
