package blu3.cameratest;

public class Logger {
    public static void INFO(String text) {
        System.out.println("> [Logger.INFO]: " + text);
    }
    public static void WARN(String text) {
        System.out.println("> [Logger.WARN]: " + text);
    }
    public static void ERROR(String text) {
        System.out.println("> [Logger.ERROR]: " + text);
    }
}
