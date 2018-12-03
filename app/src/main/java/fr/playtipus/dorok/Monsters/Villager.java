package fr.playtipus.dorok.Monsters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import fr.playtipus.dorok.Animation;
import fr.playtipus.dorok.Player;
import fr.playtipus.dorok.R;
import fr.playtipus.dorok.Sprite;

public class Villager extends Monsters {

    public Villager(Context context, int x, int y) {
        this.sprite = new Sprite(context, R.drawable.player);
        this.animation = new Animation[5];
        this.animation[0] = new Animation(sprite.getBitmap(), new int[]{2, 0, 0, 0}, 145, 185, 50, 0);
        this.pm = 1;
        this.lives = 1;
        this.moving = false;
        this.speed = 5;

        this.x = x;
        this.y = y;
    }

    private void movement(int[] dir) {
        int tileSizeX = 64; //temporary
        int tileSizeY = 64; //temporary

        if (Math.abs(x - startX) > tileSizeX || Math.abs(y - startY) > tileSizeY) {
            moving = false;
            currentAnimation = 0;
        }
        if (dir[0] == 1 && dir[1] == 1) { //top right
            x += speed;
            y -= speed;
            currentAnimation = 1;
        } else if (dir[0] == 1 && dir[3] == 1) { //top left
            x -= speed;
            y -= speed;
            currentAnimation = 2;
        } else if (dir[2] == 1 && dir[1] == 1) { //bottom right
            x += speed;
            y += speed;
            currentAnimation = 3;
        } else if (dir[2] == 1 && dir[3] == 1) { //bottom left
            x -= speed;
            y += speed;
            currentAnimation = 4;
        }
    }

    void update(Player player, int[] dir) {
        if (checkMovements(dir) && !moving) {
            moving = true;
            startX = x;
            startY = y;
        }

        if (moving) {
            movement(dir);
        }
    }

    void draw(Canvas canvas, Paint paint) {
        animation[currentAnimation].draw(canvas, x, y, paint);
    }
}
