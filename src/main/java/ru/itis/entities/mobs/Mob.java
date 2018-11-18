package ru.itis.entities.mobs;

import ru.itis.entities.Entity;
import ru.itis.graphics.sprites.Sprite;

public abstract class Mob extends Entity {
    protected Sprite sprite;
    protected int direction = 0;
    private boolean moving = false;

    public void move(int xa, int ya) {
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }

        if (xa > 0) direction = 1;
        if (xa < 0) direction = 3;
        if (ya > 0) direction = 2;
        if (ya < 0) direction = 0;
        if (!collision(xa, ya)) {
            x += xa;
            y += ya;
        }
    }

    public void update() {

    }

    public void render() {

    }

    private boolean collision(int xa, int ya) {
        boolean solid = false;
        for (int corner = 0; corner < 4; corner++) {

        }
        if (level.getTile((x + xa) >> 5, (y + ya) >> 5).isSolid()) {
            solid = true;
        }
        return solid;
    }
}
