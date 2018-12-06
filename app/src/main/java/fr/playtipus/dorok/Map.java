package fr.playtipus.dorok;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Tile> lTiles;
    private static final String TAG = "JsonTest";
    private Bitmap bitmap;
    private int x;
    private int y;
    private JSONObject obj ;
    private int size;

    public Map(Context context, int screenX, int screenY) {
        InputStream ips = context.getResources().openRawResource(R.raw.test);
        InputStreamReader ipsr = new InputStreamReader(ips);
        StringBuilder text = new StringBuilder();
        BufferedReader br = null;

        try {
            br = new BufferedReader(ipsr);
            String line;

            while ((line = br.readLine()) != null) {

                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        } finally {
            try { br.close(); } catch (Exception e) { }
        }
        lTiles = new ArrayList<Tile>();
            try {
                obj = new JSONObject(text.toString());
                size = obj.getInt("Size");
                JSONArray tiles = obj.getJSONArray("Tiles");
                for (int i=0; i< tiles.length(); i++ ) {
                    JSONObject tile = tiles.getJSONObject(i);
                    Tile nt = new Tile(tile.getInt("X"), tile.getInt("Y"), context, tile.getInt("Type"));
                    lTiles.add(nt);
                }

            } catch (JSONException e) {
                e.printStackTrace();
        }
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile2);
        bitmap = Bitmap.createScaledBitmap(bitmap,100, 100, false);

        x = screenX / 2 - bitmap.getHeight() / 2;
        y = screenY / 2 - bitmap.getWidth() / 2;

    }

    public void drawMap(Canvas canvas, Paint paint) {
        int incTile = 0;
        int i = 0;
        int x;
        int y;
        while (i < lTiles.size()){
            x = this.lTiles.get(incTile).getX() * 50 + this.x;
            y = this.lTiles.get(incTile).getY() * 40 + this.y;
            canvas.drawBitmap(this.lTiles.get(incTile).getBitmap(), x, y, paint);
            i += 1;
            incTile += 1;
        }
    }

    public List<Tile> getlTiles() {
        return lTiles;
    }
}

