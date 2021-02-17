package blu3.cameratest.renderer;

import blu3.cameratest.Logger;
import blu3.cameratest.Main;

import java.util.Arrays;

public class Renderer {
    public static int[] pixels = new int[Main.WIDTH * Main.HEIGHT];

    public static void render() {
        Arrays.fill(pixels, 0);
        drawRect(700, 100, 800, 200, 0xFF00FFFF);
    }

    /**
     * Draws a solid colour rectangle to the screen
     *
     * @param left   Left side
     * @param top    Top side
     * @param right  Right side
     * @param bottom Bottom side
     * @param colour The colour you want the pixel to be, best done using hexadecimal (0xAARRGGBB).
     */
    public static void drawRect(int left, int top, int right, int bottom, int colour) {
        // If right comes before left or bottom comes before top it simply won't
        // render, and that's kind of a problem when our entire goal is to render.
        // So just throw an exception and exit.
        if (left > right || top > bottom) {
            Logger.ERROR("Drawing invalid rectangle.");
            throw new RuntimeException("why would you do that?");
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
