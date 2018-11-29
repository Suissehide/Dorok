package fr.playtipus.dorok.Monsters;

import android.graphics.Canvas;
import android.graphics.Paint;

import fr.playtipus.dorok.Animation;
import fr.playtipus.dorok.Player;
import fr.playtipus.dorok.Sprite;

public abstract class Monsters {

    protected int x;
    protected int y;
    protected boolean moving;
    protected int startY;
    protected int startX;
    protected int speed;

    protected Sprite sprite;
    protected Animation[] animation;
    protected int currentAnimation;

    protected int lives;
    protected int pm;

    protected boolean checkMovements(int[] array) {
        boolean check = false;

        for (int i = 0; i < 4; i ++) {
            if (array[i] != 0) {
                check = true;
            }
        }
        return check;
    }

    abstract void update(Player player, int[] dir);

    abstract void draw(Canvas canvas, Paint paint);

}



