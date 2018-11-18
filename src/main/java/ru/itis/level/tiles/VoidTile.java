package ru.itis.level.tiles;

import ru.itis.graphics.Screen;
import ru.itis.graphics.sprites.Sprite;

public class VoidTile extends Tile {

    public VoidTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 5, y << 5, this);
    }

    public boolean isSolid() {
        return true;
    }
}
