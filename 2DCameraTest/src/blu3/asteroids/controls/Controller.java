package blu3.asteroids.controls;

import blu3.asteroids.Main;
import blu3.asteroids.audio.Sounds;
import blu3.asteroids.games.asteroids.Game;
import blu3.asteroids.games.asteroids.player.Player;

import java.awt.event.KeyEvent;

public class Controller {

    public static boolean[] pressedKeys = new boolean[68836];

    //------------------------------------------------------------------------------
    // Sorts through given input each tick and actually does things with it.
    // This needs major refactoring, however that can come at a later date.
    // edit: i did the refactoring
    //------------------------------------------------------------------------------

    public void tick(boolean[] key, boolean mouse) {
        Player.updateThrust(key[KeyEvent.VK_W]);
        if (key[KeyEvent.VK_A] && Player.alive) {
            Player.rot -= 3;
        }
        if (key[KeyEvent.VK_D] && Player.alive) {
            Player.rot += 3;
        }

        if (!pressedKeys[KeyEvent.VK_SPACE] && key[KeyEvent.VK_SPACE]) {
            Game.spawnBullet();
            pressedKeys[KeyEvent.VK_SPACE] = true;
        }
//        if (!mouseDown && mouse) {
//            do something with this later
//        }

        if (!pressedKeys[KeyEvent.VK_Q] && key[KeyEvent.VK_Q]) {
            Main.NODEBUG = !Main.NODEBUG;
            Sounds.BEEP.play();
            pressedKeys[KeyEvent.VK_Q] = true;
        }

        for (int i = 0; i < pressedKeys.length; i++) {
            if (pressedKeys[i] && !key[i]) pressedKeys[i] = false; // may tank performance but ok
        }
//        if (mouseDown && !mouse) mouseDown = false; // and this
    }
}
