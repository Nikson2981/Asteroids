package blu3.cameratest.renderer;

import blu3.cameratest.*;

import java.util.Arrays;

 //------------------------------------------------------------------------------
 // This is THE renderer. If you want to draw something, do it here.
 // All rendering things must be called via the render() method, else you'll
 // get some real bad screen-tearing...
 //------------------------------------------------------------------------------

public class Renderer {
    public static int[] pixels = new int[Main.WIDTH * Main.HEIGHT];

    // TODO: add an annotation or similar to be able to call draw methods outside this
    public static void render() {
        Arrays.fill(pixels, 0);
        drawRect(700, 100, 800, 200, 0xFF00FFFF);
    }

    //------------------------------------------------------------------------------
    // Draws a solid colour rectangle to the screen, using the 4 side parameters.
    // The colour is best done using hexadecimal (0xAARRGGBB).
    // If for some reason you try to invert the sides, an exception is thrown.
    //------------------------------------------------------------------------------
    public static void drawRect(int left, int top, int right, int bottom, int colour) {
        // If right comes before left or bottom comes before top it simply won't
        // render, and that's kind of a problem when our entire goal is to render.
        // So just throw an exception and exit.
        if (left > right || top > bottom) {
            Logger.ERROR("You tried to draw a shape that doesn't exist");
            ShittyCodeException e = new ShittyCodeException("fffuck-k...");
            e.post();
            return;
        }
        // Don't even bother if entirely offscreen
        if (left + Camera.getOffsetX() > Main.WIDTH || top + Camera.getOffsetY() > Main.HEIGHT || right + Camera.getOffsetX() < 0 || bottom + Camera.getOffsetY() < 0) {
            return;
        }
        // Run through all pixels that PixelPos.fill(IIIII) gives us and set their colours accordingly.
        // This isn't efficient in the slightest... Too bad!
        for (PixelPos pos : PixelPos.fill(left + (int) Camera.getOffsetX(), top + (int) Camera.getOffsetY(), right + (int) Camera.getOffsetX(), bottom + (int) Camera.getOffsetY(), colour)) {
            pixels[pos.getScreenPosition(Main.WIDTH)] = pos.getColour();
        }
    }
}
