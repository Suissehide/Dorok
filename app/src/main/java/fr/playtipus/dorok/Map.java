package fr.playtipus.dorok;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Map {

    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed;

    public Map(Context context, int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile);
        bitmap = Bitmap.createScaledBitmap(bitmap,100, 100, false);

        x = screenX / 2 - bitmap.getHeight() / 2;
        y = screenY / 2 - bitmap.getWidth() / 2;
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
}

