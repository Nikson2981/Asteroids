package blu3.asteroids;

import blu3.asteroids.audio.Sounds;
import blu3.asteroids.controls.Controller;
import blu3.asteroids.controls.InputHandler;
import blu3.asteroids.math.StringListEntry;
import blu3.asteroids.math.TextureListEntry;
import blu3.asteroids.renderer.Renderer;
import blu3.asteroids.renderer.Textures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import static blu3.asteroids.audio.Sounds.loadSound;
import static blu3.asteroids.renderer.Textures.loadTexture;

//------------------------------------------------------------------------------
// super fucking janky shit right here
// comments are few and far between so you'll just have to live with that
// most of them are just me complaining about things anyway
//------------------------------------------------------------------------------
public class Main extends Canvas implements Runnable {

    private final BufferedImage img;
    private final int[] pixels;
    private boolean running = false;
    public static final int HEIGHT = 720, WIDTH = HEIGHT * 16 / 9; // 720p by default
    public static final String NAME = "Asteroids";
    public final InputHandler input;
    private final Controller controls;
    private int fps = 0;
    public static boolean NODEBUG = true;

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
        int i = 0;
        while (i < Sounds.sounds.size()) {
            loadSound(i);
            i++;
        }
        i = 0;
        while (i < Textures.textures.size()) { // load all assets now, one at a time
            loadTexture(i);
            i++;
        }
        JFrame frame = new JFrame();
        frame.add(main);
        frame.pack();
        frame.setTitle(NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        Logger.INFO("Render thread started.");
        main.start();
        GameThread.main(args);
    }


    @Override
    public void run() {
        // would you believe that all this is for an FPS counter?
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
                if (ticked) {
                    render(); // FINALLY we get to rendering
                    frames++;
                }
            } catch (Exception e) {
                Logger.ERROR("[Fatal] Exception caught! Shutting down..."); // pls no
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
        long ms = System.currentTimeMillis();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> { // for debug purposes only
            Sounds.CLOSE_GAME.play(); // except for this
            long newMs = System.currentTimeMillis();
            long seconds = (System.currentTimeMillis() - ms) / 1000;
            String str;
            long minutes, newSeconds;
            if (seconds < 60) str = seconds + "s";
            else {
                minutes = seconds / 60;
                newSeconds = seconds % 60;
                if (minutes < 60) str = minutes + "m, " + newSeconds + "s";
                else {
                    long hours = minutes / 60;
                    long newMinutes = minutes % 60;
                    str = hours + "h, " + newMinutes + "m, " + newSeconds + "s";
                }
            }
            Logger.INFO("Total runtime: " + str);
            Logger.INFO("Shutting down...");
            while (((System.currentTimeMillis() - newMs) / 1000) < 1) {
                // do literally nothing just to delay for a second to let the sound play lmfao
                // this is what it's come to
            }
        }));
    }

    public void tick(boolean[] key, boolean mouseDown) {
        boolean forward = key[KeyEvent.VK_W];
        boolean counterClockwise = key[KeyEvent.VK_A];
        boolean reverse = key[KeyEvent.VK_S];
        boolean clockwise = key[KeyEvent.VK_D];
        boolean space = key[KeyEvent.VK_SPACE];
        controls.tick(forward, reverse, counterClockwise, clockwise, mouseDown, space);
    }


    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Renderer.render();
        System.arraycopy(Renderer.pixels, 0, pixels, 0, Renderer.pixels.length);
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        for (TextureListEntry entry : Renderer.getImagesToRender()) {
            Graphics2D imag = (Graphics2D) bs.getDrawGraphics();
            double rot = entry.getRotation();
            if (entry.getRotation() != 0) {
                imag.rotate(rot, entry.getParams()[0] + (entry.getParams()[2] / 2.0), entry.getParams()[1] + (entry.getParams()[3] / 2.0)); // lol
            }
            imag.drawImage(entry.getTexture(), entry.getParams()[0], entry.getParams()[1], entry.getParams()[2], entry.getParams()[3], null); // the fuck was i on?
            imag.dispose();
        }
        g.setFont(new Font("Verdana", Font.PLAIN, 20));
        for (StringListEntry entry : Renderer.getStringsToDraw()) {
            g.setColor(entry.getColour());
            g.drawString(entry.getText(), entry.getX(), entry.getY());
        }
        g.setColor(Color.WHITE);
        g.drawString(fps + " FPS", 20, 30);
        g.dispose();
        bs.show();
    }
}
