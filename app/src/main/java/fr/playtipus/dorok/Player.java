package fr.playtipus.dorok;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.Arrays;

import android.util.Log;

public class Player {

    private Bitmap bitmap;
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
        speed = 10;
        dir = new int[4];
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        bitmap = Bitmap.createScaledBitmap(bitmap,100, 100, false);

        //calculating maxY
        maxY = screenY - bitmap.getHeight();

        //top edge's y point is 0 so min y will always be zero
        minY = 0;

        x = screenX / 2 - bitmap.getHeight() / 2;
        y = screenY / 2 - bitmap.getWidth() / 2;

        moving = false;
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
            }
            //Log.d(TAG, "arr: " + Arrays.toString(dir));
            if (dir[0] == 1 && dir[1] == 1) { //top right
                x += speed;
                y -= speed;
            }
            else if (dir[0] == 1 && dir[3] == 1) { //top left
                x -= speed;
                y -= speed;
            }
            else if (dir[2] == 1 && dir[1] == 1) { //bottom right
                x += speed;
                y += speed;
            }
            else if (dir[2] == 1 && dir[3] == 1) { //bottom left
                x -= speed;
                y += speed;
            }
        }

        //but controlling it also so that it won't go off the screen
        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
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
