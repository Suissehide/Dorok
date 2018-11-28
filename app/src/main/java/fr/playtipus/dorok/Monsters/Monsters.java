package fr.playtipus.dorok.Monsters;

import fr.playtipus.dorok.Player;
import fr.playtipus.dorok.Sprite;

abstract class Monsters {

    protected int x;
    protected int y;
    protected boolean moving;
    protected int startY;
    protected int startX;

    protected Sprite sprite;

    protected int lives;
    protected int pm;

    abstract void update(Player player);

    abstract void draw();

}



