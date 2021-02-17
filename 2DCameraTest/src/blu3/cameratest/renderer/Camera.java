package blu3.cameratest.renderer;

public class Camera {

    private static long offsetX, offsetY;

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
