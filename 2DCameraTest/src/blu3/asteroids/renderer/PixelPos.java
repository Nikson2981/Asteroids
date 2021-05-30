package blu3.asteroids.renderer;

import blu3.asteroids.Main;

import java.util.ArrayList;
import java.util.List;

//-------------------------------------------------------
// Allows you to input the X and Y coordinates of a pixel
// and converts it to a position on the screen.
// Highly inefficient, however fixing it is not within my pay grade.
//-------------------------------------------------------

// ...

// never allow me to code again
public class PixelPos {
    private final int x, y;
    private final int colour;
    public PixelPos(int x, int y, int colour) {
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    public int getScreenPosition(int screenWidth) {
        return (y * screenWidth) + x;
    }

    // Iterates from the top left to bottom right and returns a list of positions to be changed.
    public static List<PixelPos> fill(int left, int top, int right, int bottom, int colour) {
        List<PixelPos> pos = new ArrayList<>();
        for (int x = left; x < right; x++) {
            for (int y = top; y < bottom; y++) {
                try {
                    PixelPos pixelPos = new PixelPos(x, y, colour);
                    // If the pixel is offscreen or the pixel is already the right colour, don't bother changing it.
                    if (pixelPos.isOffscreen()) continue;
                    if (Renderer.pixels[pixelPos.getScreenPosition(Main.WIDTH)] == colour)
                        continue; // NOTE: this line currently doesn't work.
                    pos.add(pixelPos);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return pos;
    }


    // Pretty self explanatory, if it isn't within the boundaries of the screen return true
    public boolean isOffscreen() {
        return x < 0 || x > Main.WIDTH - 1 || y < 0 || y > Main.HEIGHT;
    }

    public int getColour() {
        return colour;
    }
}
