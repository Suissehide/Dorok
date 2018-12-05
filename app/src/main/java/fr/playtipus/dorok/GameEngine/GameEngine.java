package fr.playtipus.dorok.GameEngine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import fr.playtipus.dorok.Map;
import fr.playtipus.dorok.Monsters.Monsters;
import fr.playtipus.dorok.Monsters.Villager;
import fr.playtipus.dorok.Player;

public class GameEngine {

    private int turn;
    private Monsters[] monsters;
    private Player player;
    private Map map;

    public GameEngine(Context context, int screenX, int screenY) {
        this.turn = 0;
        this.map = new Map(context, screenX, screenY);
        this.player = new Player(context, screenX, screenY);
        initMonsters(context);
    }

    private void initMonsters(Context context) {
        //this.monsters = new Villager(context, (int)Math.random() * ( 100 - 300 ), (int)Math.random() * ( 100 - 300 ));
    }

    public void update(int[] dir) {
        //player.setDir(dir);
        player.update();
    }

    public void draw(Canvas canvas, Paint paint) {
        map.drawMap(canvas, paint);
        //Drawing the player
        player.draw(canvas, paint);
    }

    public Player getPlayer() {
        return player;
    }
}
