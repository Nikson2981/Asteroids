package blu3.hub.math;

import java.awt.*;

public class StringListEntry {

    public final String text;
    public final int x, y;
    public final Color colour;

    public StringListEntry(String text, int x, int y, Color colour) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public String getText() {
        return text;
    }

    public Color getColour() {
        return colour;
    }
}
