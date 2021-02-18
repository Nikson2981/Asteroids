package blu3.cameratest;

import blu3.cameratest.controls.Controller;
import blu3.cameratest.controls.InputHandler;
import blu3.cameratest.renderer.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

//------------------------------------------------------------------------------
// Some real tasty spaghetti code right here. All of this random bullshit comes
// together to make a window that can draw pixels. Base input code is not mine
// and the BufferStrategy code was taken straight from stackoverflow.
// Only change things in this unless absolutely necessary, and please, for the
// love of God, add code comments!
//
// As of 02/17/2021 I have decided to make this a 2D side-scroller, so I can at 
// least try experimenting with video-game physics. The concept? Similar
// to that of Terraria I suppose, but just *different*.
//------------------------------------------------------------------------------
public class Main extends Canvas implements Runnable {

    private final BufferedImage img;
    private final int[] pixels;
    private boolean running = false;
    public static final int WIDTH = 1280, HEIGHT = 720;
    public static final String NAME = "2DCameraTest";
    public final InputHandler input;
    private final Controller controls;
    private int fps = 0;

    private static Main INSTANCE;

    public static Main getRenderThread() {
        return INSTANCE;
    }

    public Main() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
        controls = new Controller();
        input = new InputHandler();
        addKeyListener(input);
        addFocusListener(input);
        addMouseListener(input);
        addMouseMotionListener(input);
        INSTANCE = this;
    }

    public static void main(String[] args) {
        Main main = new Main();
        JFrame frame = new JFrame();
        frame.add(main);
        frame.pack();
        frame.setTitle(NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        Logger.INFO("Render thread started");
        main.start();
        GameThread.main(null);
    }

    @Override
    public void run() {
        // would you believe that all this is for an FPS counter?
        // FIXME: this is terrible - make a timer and use it instead
        int frames = 0;
        double unprocessedSeconds = 0;
        long previousTime = System.nanoTime();
        double secondsPerTick = 1 / 60.0;
        int tickCount = 0;
        boolean ticked = false;
        while (running) {
            long currentTime = System.nanoTime();
            long passedTime = currentTime - previousTime;
            previousTime = currentTime;
            unprocessedSeconds += passedTime / 1000000000.0;
            while (unprocessedSeconds > secondsPerTick) {
                unprocessedSeconds -= secondsPerTick;
                ticked = true;
                tickCount++;
                if (tickCount % 60 == 0) {
                    fps = frames;
                    previousTime += 1000;
                    frames = 0;
                }
            }
            try {
                if(ticked) {
                    render();
                    frames++;
                }
            } catch (Exception e) {
                Logger.ERROR("[Fatal] Exception caught! Shutting down...");
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    private void start() {
        if (running) return;
        Thread thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void tick(boolean[] key, boolean mouseDown) {
        boolean forward = key[KeyEvent.VK_W]; // TODO: remove this
        boolean back = key[KeyEvent.VK_S]; // TODO: remove this
        boolean left = key[KeyEvent.VK_A];
        boolean right = key[KeyEvent.VK_D];
        // boolean jump = key[KeyEvent.VK_SPACE];
        // boolean esc = key[KeyEvent.VK_ESCAPE];
        controls.tick(forward, back, left, right, mouseDown);
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }   
        Renderer.render(); // This must be called here for now.
        System.arraycopy(Renderer.pixels, 0, pixels, 0, Renderer.pixels.length); // TODO: different thread for math and synchronize it with this.
        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g.setFont(new Font("Verdana", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString(fps + " FPS", 20, 30);
        g.dispose();
        bs.show();
    }
}
