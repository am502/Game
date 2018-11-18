package ru.itis.entities.mobs;

import ru.itis.graphics.Screen;
import ru.itis.graphics.sprites.Sprite;
import ru.itis.inputs.Keyboard;

public class Player extends Mob {

    private Keyboard input;

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
    }

    public Player(Keyboard input) {
        this.input = input;
    }

    public void update() {
        int xa = 0;
        int ya = 0;
        if (input.isUp()) ya--;
        if (input.isDown()) ya++;
        if (input.isLeft()) xa--;
        if (input.isRight()) xa++;

        if (xa != 0 || ya != 0) move(xa, ya);
    }

    public void render(Screen screen) {
        if (direction == 0) {
            sprite = Sprite.playerForward;
        }
        if (direction == 1) {
            sprite = Sprite.playerRight;
        }
        if (direction == 2) {
            sprite = Sprite.playerBack;
        }
        if (direction == 3) {
            sprite = Sprite.playerLeft;
        }
        screen.renderPlayer(x - 16, y - 16, sprite);
    }
}
