package blu3.cameratest;

public class GameThread implements Runnable{

    private boolean began = false;
    private final TimerUtils timer;

    public GameThread() {
        timer = new TimerUtils();

    }

    @Override
    public void run() {
        while(began) {
            if (timer.passedS(1 / 60f)) {
                Main.getRenderThread().tick(Main.getRenderThread().input.key, Main.getRenderThread().input.mouseDown);
                // TODO: main game loop goes here
                timer.reset();
            }
        }
    }

    public static void main(String[] args) {
        GameThread gameThread = new GameThread();
        gameThread.start();
    }

    public void start() {
        if (began) return;
        began = true;
        Thread thread = new Thread(this, "GameThread");
        thread.start();
        Logger.INFO("Game thread started");
    }
}
