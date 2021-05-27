package blu3.asteroids;

//------------------------------------------------------------------------------
// So you want to write something to the console? Boy, do I have just the thing for you!
//------------------------------------------------------------------------------
public class Logger {
    public static void INFO(Object text) {
        System.out.println("[Logger/INFO]: " + text);
    }

    public static void WARN(Object text) {
        System.out.println("[Logger/WARN]: " + text);
    }

    public static void ERROR(Object text) {
        System.out.println("[Logger/ERROR]: " + text);
    }
}
