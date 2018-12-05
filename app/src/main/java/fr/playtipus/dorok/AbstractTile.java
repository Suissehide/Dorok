package fr.playtipus.dorok;

import android.graphics.Bitmap;

public abstract class AbstractTile {
    protected  int x;
    protected int y;
    protected int NSprite;
    protected Bitmap sprite;

    public AbstractTile(int x, int y, int NSprite){
        this.x = x;
        this.y = y;
        this.NSprite = NSprite;
    }
}
