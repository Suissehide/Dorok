package fr.playtipus.dorok.Monsters;

import android.content.Context;

import fr.playtipus.dorok.Player;
import fr.playtipus.dorok.R;
import fr.playtipus.dorok.Sprite;

public class Villager extends Monsters {

    public Villager(Context context, int x, int y) {
        this.sprite = new Sprite(context, R.drawable.player);
        this.pm = 1;
        this.lives = 1;
        this.moving = false;

        this.x = x;
        this.y = y;
    }

    private int isEntity() {
        return (0);
    }

    void update(Player player) {
        if (moving == false) {

        }
    }

    void draw() {

    }
}
