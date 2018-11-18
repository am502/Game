package ru.itis.level;

public class TileCoordinate {
    private int x;
    private int y;
    private static final int TILE_SIZE = 32;

    public TileCoordinate(int x, int y) {
        this.x = x * TILE_SIZE;
        this.y = y * TILE_SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
