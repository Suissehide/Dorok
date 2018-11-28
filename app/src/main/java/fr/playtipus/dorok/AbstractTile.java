package fr.playtipus.dorok;

import android.graphics.Bitmap;

public abstract class AbstractTile {
    protected  int x;
    protected int y;
    protected Bitmap sprite;

    public AbstractTile(int x, int y){
        this.x = x;
        this.y = y;
    }
}
