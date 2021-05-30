package blu3.asteroids.renderer;

import java.util.Arrays;
import java.util.List;

public class Textures {

    public static final Texture PLAYER =    new Texture(textureLocation("asteroids/Player"), 0, 0, 32, 32);
    public static final Texture ASTEROID1 = new Texture(textureLocation("asteroids/Asteroid1"), 0, 0, 64, 64);
    public static final Texture ASTEROID2 = new Texture(textureLocation("asteroids/Asteroid2"), 0, 0, 64, 64);
    public static final Texture BULLET =    new Texture(textureLocation("asteroids/bullet"), 0, 0, 8, 8);

    public static final Texture P_DEAD1 =   new Texture(textureLocation("asteroids/p_dead1"), 0, 0, 32, 32);
    public static final Texture P_DEAD2 =   new Texture(textureLocation("asteroids/p_dead2"), 0, 0, 32, 32);
    public static final Texture P_DEAD3 =   new Texture(textureLocation("asteroids/p_dead3"), 0, 0, 32, 32);
    public static final Texture P_EXP1 =    new Texture(textureLocation("asteroids/p_exp1"), 0, 0, 32, 32);

    public static final List<Texture> textures = Arrays.asList(PLAYER, ASTEROID1, ASTEROID2, BULLET, P_DEAD1, P_DEAD2, P_DEAD3, P_EXP1); // add new textures to the list

    public static void loadTexture(int i) {
        if (textures.get(i) != null) {
            textures.get(i).load();
        }
    }

    public static String NULL() {
        return textureLocation("null");
    } // imagine being able to name a method "null"

    public static String textureLocation(String fileName) {
        return "/textures/" + fileName + ".png";
    }
}
