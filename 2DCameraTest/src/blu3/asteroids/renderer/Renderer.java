package blu3.asteroids.renderer;

import blu3.asteroids.Logger;
import blu3.asteroids.Main;
import blu3.asteroids.games.asteroids.Game;
import blu3.asteroids.math.StringListEntry;
import blu3.asteroids.math.TextureListEntry;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Renderer {
    public static final int[] pixels = new int[Main.WIDTH * Main.HEIGHT];

    public static final List<TextureListEntry> images = new ArrayList<>();
    public static final List<StringListEntry> strings = new ArrayList<>();

    public static void render() {
        Arrays.fill(pixels, 0);
        images.clear();
        strings.clear();
        try {
            Game.drawScreen();
        } catch (ConcurrentModificationException ffs) {
            Logger.WARN("caught an exception for some reason"); // fuck off
        }
    }

    public static List<TextureListEntry> getImagesToRender() {
        return images;
    }

    public static List<StringListEntry> getStringsToDraw() {
        return strings;
    }

    // fgdytisulivh no
    public static void drawRect(int left, int top, int right, int bottom, int colour) {
        if (left > right || top > bottom) {
            Logger.ERROR("You tried to draw a shape that doesn't exist");
            RuntimeException up = new RuntimeException("oh-h fffuck-k...");
            throw up;
        }
        // Don't even bother if entirely offscreen
        if (left > Main.WIDTH || top > Main.HEIGHT || right < 0 || bottom < 0) {
            return;
        }
        // Run through all pixels that PixelPos.fill(IIIII) gives us and set their colours accordingly.
        // This isn't efficient in the slightest... Too bad!
        for (PixelPos pos : PixelPos.fill((short) Math.max(left, 0), (short) Math.max(top, 0), (short) Math.min(right, Main.WIDTH), (short) Math.min(bottom, Main.HEIGHT), colour)) {
            // math stuff above to get rid of any pixels not within the bounds of the screen
            // trying to save as much rendering time as possible
            pixels[pos.getScreenPosition(Main.WIDTH)] = pos.getColour();
        }
    }

    //------------------------------------------------------------------------------
    // Draws a texture to the screen, using the 4 parameters.
    // For an image to be drawn normally, leave rotationDegrees as 0.
    // my renderer code is all terrible and inefficient, if you hadn't guessed
    //------------------------------------------------------------------------------
    public static void drawTexture(Texture texture, int x, int y, int w, int h, int rotationDegrees) {
        if (x > Main.WIDTH || y > Main.HEIGHT || x + w < 0 || y + h < 0) {
            return; // almost forgot the whole "don't draw this if it's offscreen" thing
        }
        images.add(new TextureListEntry(texture.image, new int[]{x, y, w, h}, Math.toRadians(rotationDegrees)));
    }

    public static void drawString(String text, int x, int y, Color color) {
        strings.add(new StringListEntry(text, x, y, color));
    }
}
