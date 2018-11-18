package ru.itis.level.tiles;

import ru.itis.graphics.Screen;
import ru.itis.graphics.sprites.Sprite;

public class Tile {

    private int x;
    private int y;
    private Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grassSprite);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {

    }

    public boolean isSolid() {
        return false;
    }

    public boolean isBreakable() {
        return false;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
