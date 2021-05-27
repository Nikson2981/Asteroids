package blu3.hub.renderer;

import java.util.Arrays;
import java.util.List;

public class Textures {

    public static final Texture PLAYER = new Texture(textureLocation("asteroids/Player"), 0, 0, 32, 32);
    public static final Texture ASTEROID1 = new Texture(textureLocation("asteroids/AsteroidBig1"), 0, 0, 64, 64);
    public static final Texture ASTEROID2 = new Texture(textureLocation("asteroids/AsteroidBig2"), 0, 0, 64, 64);
    public static final Texture BULLET = new Texture(textureLocation("asteroids/bullet"), 0, 0, 8, 8);

    public static final List<Texture> textures = Arrays.asList(PLAYER, ASTEROID1, ASTEROID2, BULLET);

    public static void loadTexture(int i) {
        if (textures.get(i) != null) {
            textures.get(i).load();
        }
    }

    public static String NULL() {
        return textureLocation("null");
    }

    public static String textureLocation(String fileName) {
        return "/textures/" + fileName + ".png";
    }
}
