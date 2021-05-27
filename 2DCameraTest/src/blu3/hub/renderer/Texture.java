package blu3.hub.renderer;

import blu3.hub.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class Texture {
    public int width, height;
    public int[] pixels;
    public BufferedImage image;
    public boolean loaded = false;
    private final String path;

    public final int x, y, textureWidth, textureHeight;

    public Texture(String path, int x, int y, int textureWidth, int textureHeight) {
        this.x = x;
        this.y = y;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.path = path;
    }

    public void load() {
        if (loaded) return;
        long ms = System.currentTimeMillis();

        try {
            image = ImageIO.read(Texture.class.getResourceAsStream(path));
        } catch (Exception e) {
            try {
                image = ImageIO.read(Texture.class.getResourceAsStream(Textures.NULL()));
            } catch (Exception f) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load texture.");
            }
        }
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pixels = new int[this.width * this.height];
        Logger.INFO("Loaded texture: " + path + ", size " + width + " x " + height + ", took " + (System.currentTimeMillis() - ms) + " millis");
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), this.pixels, 0, image.getWidth());
        loaded = true;
    }
}