package blu3.asteroids;

import blu3.asteroids.games.asteroids.Game;

import javax.swing.*;

public class GameThread implements Runnable { // guess who learned how to multithread

    private boolean began = false, gameStarted = false;
    private final SpaceTimeContinuum timer;
    private static GameThread gameThread;

    public GameThread() {
        timer = new SpaceTimeContinuum();
    }

    @Override
    public void run() {
        while (began) {
            if (timer.passedS(1 / 60f)) { // 60 fps... hard limit as i cba to add delta time
// ... i'm certain your pc can handle it
                if (timer.passedS(1 / 30f)) {
                    Logger.WARN("Missed a frame");
                }
                try {                                                                                   // even with my shitass code
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
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "[Logger/ERROR]: Something went wrong... And the game has crashed. Fear not! Simply launch the game again to continue playing!", "", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
                timer.reset();
            }
        }
    }

    public static void main(String[] args) {
        gameThread = new GameThread();
        gameThread.start();
    }

    public static void stop() {
        gameThread.began = false;
        Logger.INFO("Game thread stopped.");
    }

    public void start() {
        if (began) return;
        began = true;
        Thread thread = new Thread(this, "GameThread");
        thread.start();
        Logger.INFO("Game thread started. \n");

    }
}
