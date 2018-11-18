package ru.itis.graphics;

import ru.itis.graphics.sprites.Sprite;
import ru.itis.level.tiles.Tile;

import java.util.Random;

public class Screen {
    private final static int MAP_SIZE = 32;
    private final static int MAP_SIZE_MASK = MAP_SIZE - 1;
    private int width;
    private int height;
    private int xOffset;
    private int yOffset;
    private int[] pixels;
    private int[] tiles;
    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        tiles = new int[MAP_SIZE * MAP_SIZE];
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public int[] getPixels() {
        return pixels;
    }

    // xp, yp - position of the tile on the map, xa, ya - absolute position
    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;
        for (int x = 0; x < tile.getSprite().getSize(); x++) {
            int xa = x + xp;
            for (int y = 0; y < tile.getSprite().getSize(); y++) {
                int ya = y + yp;
                if (xa < 0 || xa >= width || ya < -tile.getSprite().getSize() || ya >= height) break;
                if (ya < 0) {
                    ya = 0;
                }
                pixels[xa + ya * width] = tile.getSprite().getPixels()[x + y * tile.getSprite().getSize()];
            }
        }
    }

    public void renderPlayer(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;
        for (int x = 0; x < MAP_SIZE; x++) {
            int xa = x + xp;
            for (int y = 0; y < MAP_SIZE; y++) {
                int ya = y + yp;
                if (xa < 0 || xa >= width || ya < -MAP_SIZE || ya >= height) break;
                if (ya < 0) {
                    ya = 0;
                }
                int col = sprite.getPixels()[x + y * MAP_SIZE];
                if (col != 0) {
                    pixels[xa + ya * width] = col;
                }
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
