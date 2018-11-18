package ru.itis.entities;

import ru.itis.graphics.Screen;
import ru.itis.level.Level;

import java.util.Random;

public abstract class Entity {
    protected int x;
    protected int y;
    private boolean removed;
    protected Level level;
    private final Random random = new Random();

    public void update() {

    }

    public void render(Screen screen) {

    }

    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
