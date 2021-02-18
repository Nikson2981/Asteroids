package blu3.cameratest.renderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class Texture {


    //public int[] dirt = loadBitmap("/textures").getRGB(;

    public static BufferedImage loadBitmap(String fileName) {
        try {
            return ImageIO.read(Texture.class.getResource(fileName));
        } catch (Exception e) {
            System.out.println("Failed to load texture.");
            throw new RuntimeException(e);
        }
    }
}
