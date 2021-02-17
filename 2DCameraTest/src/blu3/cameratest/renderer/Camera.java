package blu3.cameratest.renderer;

//-------------------------------------------
// Responsible for telling things wether or not to draw. 
// If something isn't on screen, it won't be told to draw.
// Mostly just for adding offsets to existing things
// so we actually have the ability to move around.
//-------------------------------------------
public class Camera {
    
    // dont instantiate this i stg
    private Camera() {
        
    }

    private static long offsetX = 0, offsetY = 0;

    public static long getOffsetX() {
        return offsetX;
    }

    public static long getOffsetY() {
        return offsetY;
    }

    public static void addOffsetX(int offset) {
        offsetX += offset;
    }

    public static void addOffsetY(int offset) {
        offsetY += offset;
    }

}
