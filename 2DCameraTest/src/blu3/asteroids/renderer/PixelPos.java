package blu3.asteroids.renderer;

import blu3.asteroids.Main;

import java.util.ArrayList;
import java.util.List;

//-------------------------------------------------------
// Allows you to input the X and Y coordinates of a pixel
// and converts it to a position on the screen.
// Highly inefficient, however fixing it is not within my pay grade.
//
//...
//
// never allow me to code again
//-------------------------------------------------------

public class PixelPos {

    private final short x, y;
    private final int colour;

    public PixelPos(short x, short y, int colour) {
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    public int getScreenPosition(int screenWidth) {
        return (y * screenWidth) + x;
    }

    // Iterates from the top left to bottom right and returns a list of positions to be changed.
    public static List<PixelPos> fill(short left, short top, short right, short bottom, int colour) {
        List<PixelPos> pos = new ArrayList<>();
        for (short x = left; x < right; x++) {
            for (short y = top; y < bottom; y++) {
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


    public static List<PixelPos> line(int startX, int startY, int endX, int endY, int colour) {
        return bresenham(startX, startY, endX, endY, colour);
    }

    public static List<PixelPos> bresenham(int x1, int y1, int x2, int y2, int colour) {
        List<PixelPos> pos = new ArrayList<>();
        int m_new = 2 * (y2 - y1);
        int slope_error_new = m_new - (x2 - x1);

        for (int x = x1, y = y1; x <= x2; x++) {
            System.out.print("(" + x + "," + y + ")\n");
            PixelPos pixelPos = new PixelPos((short) x, (short) y, colour);
            if (!pixelPos.isOffscreen()) {
                pos.add(pixelPos);
            }

            // Add slope to increment angle formed
            slope_error_new += m_new;

            // Slope error reached limit, time to
            // increment y and update slope error.
            if (slope_error_new >= 0) {
                y++;
                slope_error_new -= 2 * (x2 - x1);
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
