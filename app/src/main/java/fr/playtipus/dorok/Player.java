package fr.playtipus.dorok;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.Arrays;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Player {

    private Sprite sprite;
    private Sprite spriteFlip;
    private Animation[] animation;
    private int currentAnimation = 0;
    private int x;
    private int y;
    private int speed;

    //boolean variable to track the ship is boosting or not
    private boolean moving;
    private int startY;
    private int startX;
    private int[] dir;

    //Controlling Y coordinate so that ship won't go outside the screen
    private int maxY;
    private int minY;

    private static final String TAG = "Player";

    public Player(Context context, int screenX, int screenY) {
        this.speed = 10;
        this.dir = new int[4];
        this.sprite = new Sprite(context, R.drawable.player);

        //bitmap = Bitmap.createScaledBitmap(bitmap,100, 100, false);

        this.animation = new Animation[5];
        this.animation[0] = new Animation(sprite.getBitmap(), new int[]{2, 0, 0, 0}, 145, 220, 50, 0, 0);
        this.animation[1] = new Animation(sprite.getBitmap(), new int[]{13, 0, 4, 0}, 145, 220, 500, 1, 0);
        this.animation[2] = new Animation(sprite.getBitmap(), new int[]{9, 0, 4, 0}, 145, 220, 500, 1, 1);
        this.animation[3] = new Animation(sprite.getBitmap(), new int[]{9, 0, 4, 0}, 145, 220, 500, 1, 0);
        this.animation[4] = new Animation(sprite.getBitmap(), new int[]{9, 0, 4, 0}, 145, 220, 500, 1, 1);
        //calculating maxY
        this.maxY = screenY - sprite.getBitmap().getHeight();
        //top edge's y point is 0 so min y will always be zero
        this.minY = 0;

        this.x = screenX / 2;
        this.y = screenY / 2;
        //x = screenX / 2 - bitmap.getHeight() / 2;
        //y = screenY / 2 - bitmap.getWidth() / 2;

        this.moving = false;
    }

    private boolean checkMovements(int[] array) {
        boolean check = false;

        for (int i = 0; i < 4; i ++) {
            if (array[i] != 0) {
                check = true;
            }
        }
        return check;
    }

    public void setDir(int[] playerDir) {
        System.arraycopy(playerDir, 0, dir, 0, 4);
    }

    public void update() {

        int tileSizeX = 64;
        int tileSizeY = 64;

        //Log.d(TAG, "X = " + dir[0] + " Y = " + dir[1]);

        if (checkMovements(dir) && !moving) {
            moving = true;
            startX = x;
            startY = y;
        }

        if (moving) {
            if (Math.abs(x - startX) > tileSizeX || Math.abs(y - startY) > tileSizeY) {
                Arrays.fill(dir, 0);
                moving = false;
                //currentAnimation = 0;
            }
            //Log.d(TAG, "arr: " + Arrays.toString(dir));
            if (dir[0] == 1 && dir[1] == 1) { //top right
                x += speed;
                y -= speed;
                currentAnimation = 1;
            }
            else if (dir[0] == 1 && dir[3] == 1) { //top left
                x -= speed;
                y -= speed;
                currentAnimation = 2;
            }
            else if (dir[2] == 1 && dir[1] == 1) { //bottom right
                x += speed;
                y += speed;
                currentAnimation = 3;
            }
            else if (dir[2] == 1 && dir[3] == 1) { //bottom left
                x -= speed;
                y += speed;
                currentAnimation = 4;
            }
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        //Log.d(TAG, Integer.toString(x) + ", " + Integer.toString(y));
        animation[currentAnimation].draw(canvas, x, y, paint);
    }

    public Bitmap getBitmap() {
        return sprite.getBitmap();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}
