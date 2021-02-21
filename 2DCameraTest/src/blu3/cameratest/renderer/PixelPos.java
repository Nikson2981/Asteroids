package blu3.cameratest.renderer;

import blu3.cameratest.Main;

import java.util.ArrayList;
import java.util.List;

//-------------------------------------------------------
// Allows you to input the X and Y coordinates of a pixel
// and converts it to a position on the screen.
// Highly inefficient, however fixing it is not within my pay grade.
//-------------------------------------------------------

public class PixelPos {

    private final int x, y;
    private int colour;

    public PixelPos(int x, int y, int colour) {
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    public PixelPos(int x, int y) {
        this.x = x;
        this.y = y;
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
                    //if (Renderer.pixels[pixelPos.getScreenPosition(Main.WIDTH)] == colour) continue; // NOTE: this line currently doesnt work. FIXME: this is stupid
                    pos.add(pixelPos);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return pos;
    }

    // Pretty self explanatory, if it isn't within the boundaries of the screen return true
    // the Main.WIDTH - 1 is because of some minuscule problem where ONE FUCKING PIXEL
    // rendered incorrectly on the left side of the screen while the rectangle is half over the right
    public boolean isOffscreen() {
        return x < 0 || x > Main.WIDTH - 1 || y < 0 || y > Main.HEIGHT;
    }

    public int getColour() {
        return colour;
    }
}
