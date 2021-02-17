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

public class Main extends Canvas implements Runnable {

    private final BufferedImage img;
    private final int[] pixels;
    private boolean running = false;
    public static final int WIDTH = 1280, HEIGHT = 720;
    public static final String NAME = "2DCameraTest";
    private final InputHandler input;
    private final Controller controls;
    private int fps = 0;

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
        Logger.INFO("Starting...");
        main.start();
    }

    @Override
    public void run() {
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
                    tick(input.key, input.mouseDown);
                    frames++;
                }
            } catch (Exception e) {
                Logger.ERROR("Exception caught! Shutting down...");
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
        boolean forward = key[KeyEvent.VK_W];
        boolean back = key[KeyEvent.VK_S];
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
        Renderer.render();
        System.arraycopy(Renderer.pixels, 0, pixels, 0, Renderer.pixels.length);
        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g.setFont(new Font("Verdana", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString(fps + " FPS", 20, 30);
        g.dispose();
        bs.show();
    }
}
