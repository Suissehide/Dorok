package fr.playtipus.dorok;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.content.Context;

public class Sprite {

    private Bitmap bitmap;

    public Sprite (Context context, int name) {
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), name);
        this.bitmap.setDensity(DisplayMetrics.DENSITY_DEFAULT);
    }

    public void mirrorFlip()
    {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        this.bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, false);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
