package ru.itis.graphics.sprites;

public class Sprite {
    private int size;
    private int x;
    private int y;
    private int[] pixels;
    private SpriteSheet sheet;

    public static Sprite grassSprite = new Sprite(32, 0, 0, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(32, 0x551a8b);

    public static Sprite playerBack = new Sprite(32, 0, 7, SpriteSheet.tiles);
    public static Sprite playerForward = new Sprite(32, 1, 7, SpriteSheet.tiles);
    public static Sprite playerRight = new Sprite(32, 3, 7, SpriteSheet.tiles);
    public static Sprite playerLeft = new Sprite(32, 2, 7, SpriteSheet.tiles);

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        this.size = size;
        pixels = new int[size * size];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }

    public Sprite(int size, int colour) {
        this.size = size;
        pixels = new int[size * size];
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = colour;
        }
    }

    private void load() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                pixels[x + y * size] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getSize()];
            }
        }
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getSize() {
        return size;
    }
}
