package blu3.cameratest.controls;

import blu3.cameratest.renderer.Camera;
import blu3.cameratest.renderer.Renderer;
import blu3.cameratest.screen.TitleScreen;

public class Controller {

    public static boolean walk = false;

    //------------------------------------------------------------------------------
    // Sorts through given input each tick and actually does things with it.
    // This needs major refactoring, however that can come at a later date.
    // The boolean walk can probably be replaced...
    //------------------------------------------------------------------------------

    public void tick(boolean forward, boolean back, boolean left, boolean right, boolean mouseDown) {

        // TODO: EntityMovementCalc class, and add offsets to Camera based on speed
        // instead of sudden starting and stopping
        if (forward) {
            Camera.addOffsetY(-4);
            walk = true;
        }

        if (back) {
            Camera.addOffsetY(4);
            walk = true;
        }

        if (left) {
            Camera.addOffsetX(-4);
            walk = true;
        }

        if (right) {
            Camera.addOffsetX(4);
            walk = true;
        }

        if (!forward && !back && !left && !right) {
            walk = false;
        }

        TitleScreen.updateMouse(InputHandler.MouseX, InputHandler.MouseY, mouseDown);
    }
}
