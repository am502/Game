package ru.itis.application;

import ru.itis.entities.mobs.Player;
import ru.itis.graphics.Screen;
import ru.itis.inputs.Keyboard;
import ru.itis.level.Level;
import ru.itis.level.RandomLevel;
import ru.itis.level.TileCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {
    private static final int WIDTH = 320;
    private static final int HEIGHT = WIDTH / 16 * 9;
    private static final int SCALE = 4;
    private static final int MAP_SIZE = 10;

    private Thread thread;
    private JFrame jFrame;
    private boolean isRunning = false;

    private Level level;
    private Keyboard keyboard;
    private Player player;
    private Screen screen;

    private int fps;
    private int ups;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setPreferredSize(size);
        jFrame = new JFrame();

        level = new RandomLevel(MAP_SIZE, MAP_SIZE);
        keyboard = new Keyboard();
        TileCoordinate spawn = new TileCoordinate(MAP_SIZE / 2, MAP_SIZE / 2);
        player = new Player(spawn.getX(), spawn.getY(), keyboard);
        player.setLevel(level);
        screen = new Screen(WIDTH, HEIGHT);

        jFrame.addKeyListener(keyboard);
        jFrame.setResizable(false);
        jFrame.setTitle("Craft Punk");
        jFrame.add(this);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public synchronized void start() {
        isRunning = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        keyboard.update();
        player.update();
        if (keyboard.isEsc()) {
            System.exit(0);
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        int xScroll = player.getX() - screen.getWidth() / 2;
        int yScroll = player.getY() - screen.getHeight() / 2;
        level.render(xScroll, yScroll, screen);
        player.render(screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.getPixels()[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.WHITE);
        g.drawString("x = " + player.getX() + " y = " + player.getY()
                + " fps = " + fps + " ups = " + ups, 20, 20);
        g.dispose();
        bs.show();
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        while (isRunning) {
            long nowTime = System.nanoTime();
            delta += (nowTime - lastTime) / ns;
            lastTime = nowTime;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                fps = frames;
                ups = updates;
                updates = 0;
                frames = 0;
            }
        }
    }

    public static void main(String[] args) {
        new Game().start();
    }
}
