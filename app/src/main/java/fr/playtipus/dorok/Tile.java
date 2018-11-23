package fr.playtipus.dorok;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tile extends AbstractTile {
    public Tile(int x, int y, Context context) {
        super(x, y);
        this.sprite =  BitmapFactory.decodeResource(context.getResources(), R.drawable.tile);
        this.sprite = Bitmap.createScaledBitmap(sprite,100, 100, false);
    }
}
