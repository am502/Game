package ru.itis.level;

import ru.itis.graphics.Screen;
import ru.itis.level.tiles.Tile;

public class Level {
    protected int width;
    protected int height;
    protected int[] tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new int[width * height];
        generateLevel();
    }

    protected void generateLevel() {
    }

    public void update() {

    }

    // xScroll, yScroll - player's coordinate (?), >> 5 - size of the tile
    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int xLeft = (xScroll) >> 5;
        int xRight = (xScroll + screen.getWidth() + 32) >> 5;
        int yUp = yScroll >> 5;
        int yDown = (yScroll + screen.getHeight() + 32) >> 5;

        for (int x = xLeft; x < xRight; x++) {
            for (int y = yUp; y < yDown; y++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }

    // x, y - position of the tile
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.voidTile;
        }
        if (tiles[x + y * width] == 0) {
            return Tile.grass;
        }
        return Tile.voidTile;
    }

    public int[] getTiles() {
        return tiles;
    }
}
