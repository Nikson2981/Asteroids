package blu3.hub;

import blu3.hub.games.asteroids.Game;

public class GameThread implements Runnable{

    private boolean began = false, gameStarted = false;
    private final TimerUtils timer;
    public GameThread() {
        timer = new TimerUtils();
    }

    @Override
    public void run() {
        while(began) {
            if (timer.passedS(1 / 60f)) {
                try {
                    if (!gameStarted) {
                        Game.startGame();
                        gameStarted = true;
                        continue;
                    }
                    Main.getRenderThread().tick(Main.getRenderThread().input.key, Main.getRenderThread().input.mouseDown);
                    Game.update();
                } catch (Exception e) {
                    Logger.ERROR("[Fatal] Exception caught! Shutting down...");
                    e.printStackTrace();
                    System.exit(1);
                }
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
        Logger.INFO("Game thread started.");
    }
}
