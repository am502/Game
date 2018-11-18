package ru.itis.graphics.sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    private String path;
    private int size;
    private int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);

    public SpriteSheet(String path, int size) {
        this.path = path;
        this.size = size;
        pixels = new int[size * size];
        load();
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getSize() {
        return size;
    }
}
