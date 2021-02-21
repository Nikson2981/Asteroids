package blu3.cameratest.screen;

import blu3.cameratest.Main;
import blu3.cameratest.renderer.Renderer;
import blu3.cameratest.renderer.Textures;
import static blu3.cameratest.renderer.Renderer.drawTexture;
import static blu3.cameratest.renderer.Renderer.drawString;

public class TitleScreen {

    private static boolean mDown = false;

    public static void draw() {
        
        drawString("TitleName", Main.WIDTH / 2 - 80, 40);

    }

    public static void updateMouse(int mX, int mY, boolean clicked) {
        if (!clicked && mDown) {
            mDown = false;
            released(mX, mY);
        }
        if (clicked && !mDown) {
            mDown = true;
            clicked(mX, mY);
        }
    }

    public static void clicked(int mouseX, int mouseY) {
        if (mouseX < 100 && mouseY < 100) Main.getRenderThread().titleScreen = false;
    }

    public static void released(int mouseX, int mouseY) {

    }
}
