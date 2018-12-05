package fr.playtipus.dorok;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tile extends AbstractTile {
    public Tile(int x, int y, Context context, int NSprite) {
        super(x, y, NSprite);
        if (NSprite == 1) {
            this.sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile);
            this.sprite = Bitmap.createScaledBitmap(sprite, 100, 100, false);
        }
        else {
            this.sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile2);
            this.sprite = Bitmap.createScaledBitmap(sprite, 100, 100, false);
        }
    }
    public int getNSprite() {
        return NSprite;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Bitmap getBitmap(){return sprite;}
}
