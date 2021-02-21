package blu3.cameratest.renderer;

import blu3.cameratest.*;
import blu3.cameratest.screen.TitleScreen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//------------------------------------------------------------------------------
// This is THE renderer. If you want to draw something, do it here.
// All rendering things must be called via the render() method, else you'll
// get some real bad screen-tearing...
//------------------------------------------------------------------------------

public class Renderer {
    public static int[] pixels = new int[Main.WIDTH * Main.HEIGHT];
    public static HashMap<BufferedImage, Integer[]> imagesToRender = new HashMap<>();
    public static HashMap<String, Integer[]> stringsToDraw = new HashMap<>();

    // TODO: add an annotation or similar to be able to call draw methods outside this

    public static void render() {
        if (Main.getRenderThread().titleScreen) {
            TitleScreen.draw();
        } else {
            imagesToRender.clear();
            Arrays.fill(pixels, 0);
            drawWorld();
            //drawRect(700, 100, 800, 200, 0xFF00FFFF);
        }
    }

    public static HashMap<BufferedImage, Integer[]> getImagesToRender() {
        return imagesToRender;
    }

    public static HashMap<String, Integer[]> getStringsToDraw() {
        return stringsToDraw;
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
        if (left > Main.WIDTH || top > Main.HEIGHT || right < 0 || bottom < 0) {
            return;
        }
        // Run through all pixels that PixelPos.fill(IIIII) gives us and set their colours accordingly.
        // This isn't efficient in the slightest... Too bad!
        for (PixelPos pos : PixelPos.fill(left, top, right, bottom, colour)) {
            pixels[pos.getScreenPosition(Main.WIDTH)] = pos.getColour();
        }
    }

    public static void drawTexture(Texture texture, int x, int y, int w, int h) {
        imagesToRender.put(texture.image, new Integer[]{x, y, w, h});
    }

    public static void drawString(String text, int x, int y) {
        stringsToDraw.put(text, new Integer[]{x, y});
    }

    public static void drawWorld() {
        drawTexture(Textures.DIRT1, 100 - Camera.getOffsetX(), 100 - Camera.getOffsetY(), 64, 64);
        drawTexture(Textures.DIRT2, 164 - Camera.getOffsetX(), 100 - Camera.getOffsetY(), 64, 64);
    }
}
