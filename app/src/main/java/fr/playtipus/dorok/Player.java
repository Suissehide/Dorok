package fr.playtipus.dorok;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {

    private Bitmap bitmap;
    private Texture texture;
    private int x;
    private int y;
    private int speed;

    //boolean variable to track the ship is boosting or not
    private boolean boosting;

    //Controlling Y coordinate so that ship won't go outside the screen
    private int maxY;
    private int minY;

    //Limit the bounds of the ship's speed
    private final int MIN_SPEED = 0;
    private final int MAX_SPEED = 20;

    public Player(Context context, int screenX, int screenY) {
        speed = 3;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        bitmap = Bitmap.createScaledBitmap(bitmap,100, 100, false);

        //calculating maxY
        maxY = screenY - bitmap.getHeight();

        //top edge's y point is 0 so min y will always be zero
        minY = 0;

        x = screenX / 2 - bitmap.getHeight() / 2;
        y = screenY / 2 - bitmap.getWidth() / 2;

        boosting = false;
    }

    //setting boosting true
    public void setBoosting() {
        boosting = true;
    }

    //setting boosting false
    public void stopBoosting() {
        boosting = false;
    }

    public void update() {
        //if the ship is boosting
        if (boosting) {
            //speeding up the ship
            speed += 2;
        } else {
            //slowing down if not boosting
            speed -= 5;
        }
        //controlling the top speed
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        //if the speed is less than min speed
        //controlling it so that it won't stop completely
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        //but controlling it also so that it won't go off the screen
        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }
    }

    /*
     * These are getters you can generate it automatically
     * right click on editor -> generate -> getters
     * */
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
