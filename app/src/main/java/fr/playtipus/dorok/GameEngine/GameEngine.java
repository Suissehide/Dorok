package fr.playtipus.dorok.GameEngine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.playtipus.dorok.Map;
import fr.playtipus.dorok.Monsters.Monsters;
import fr.playtipus.dorok.Monsters.Villager;
import fr.playtipus.dorok.Player;

public class GameEngine {

    private int turn;
    private List<Monsters> monsters;
    private Player player;
    private Map map;

    public GameEngine(Context context, int screenX, int screenY) {
        this.turn = 0;
        this.map = new Map(context, screenX, screenY);
        this.player = new Player(context, screenX, screenY);
        initMonsters(context);
    }

    private void initMonsters(Context context) {
        int randX = new Random().nextInt(20) + 2;
        int randY = new Random().nextInt(20) + 2;

        this.monsters = new ArrayList<Monsters>();
        this.monsters.add(new Villager(context, randX * 48 + player.getX(), randY * 48 + player.getY()));
    }

    private void drawMonsters(Canvas canvas, Paint paint) {
        for (int i = 0; i < monsters.size(); i++) {
            //monsters.get(i).draw(canvas, paint);
        }
    }

    public void update(int[] dir) {
        //player.setDir(dir);
        player.update();
    }

    public void draw(Canvas canvas, Paint paint) {
        map.drawMap(canvas, paint);
        //Drawing the player
        player.draw(canvas, paint);
        drawMonsters(canvas, paint);
    }

    public Player getPlayer() {
        return player;
    }
}
