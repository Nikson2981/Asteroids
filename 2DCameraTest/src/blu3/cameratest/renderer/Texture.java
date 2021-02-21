package blu3.cameratest.renderer;

import blu3.cameratest.Logger;
import blu3.cameratest.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Texture {
    public int width, height;
    public int[] pixels;
    public BufferedImage image;

    public final int x, y, textureWifth, textureHeight;

    public Texture(String path, int x, int y, int textureWidth, int textureHeight) {
        long ms = System.currentTimeMillis();
        this.x = x;
        this.y = y;
        this.textureWifth = textureWidth;
        this.textureHeight = textureHeight;
        try {
            image = ImageIO.read(Main.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load texture.");
        }
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pixels = new int[this.width * this.height];
        Logger.INFO("Loaded texture: " + path + ", size " + width + " x " + height + ", took " + (System.currentTimeMillis() - ms) + " millis");
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), this.pixels, 0, image.getWidth());
    }
}