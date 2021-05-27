package blu3.asteroids.math;

import java.awt.image.BufferedImage;

public class TextureListEntry {

    public final BufferedImage texture;
    public final int[] params;
    public final double rot;

    public TextureListEntry(BufferedImage texture, int[] params, double degTheta) { // ...
        this.texture = texture;
        this.params = params;
        this.rot = degTheta;
    }

    public int[] getParams() {
        return params;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public double getRotation() {
        return rot;
    }
}
