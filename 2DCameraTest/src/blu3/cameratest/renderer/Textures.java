package blu3.cameratest.renderer;

import java.util.ArrayList;
import java.util.List;

public class Textures {


    //public static final String ATLAS_LOCATION = "/textures/TextureAtlas.png";

    public static final Texture DIRT1 = new Texture(textureLocation("dirt1"), 0, 0, 16, 16); // pixel offsets within the atlas
    public static final Texture DIRT2 = new Texture(textureLocation("dirt2"), 0, 0, 16, 16);
    public static final Texture DIRT3 = new Texture(textureLocation("dirt3"), 0, 0, 16, 16);
    public static final Texture DIRT4 = new Texture(textureLocation("dirt4"), 0, 0, 16, 16);


    public static String textureLocation(String fileName) {
        return "/textures/" + fileName + ".png";
    }


}
