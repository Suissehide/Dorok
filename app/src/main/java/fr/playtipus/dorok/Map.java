package fr.playtipus.dorok;

import android.content.Context;
import android.content.SyncStatusObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Debug;
import android.util.Log;
import org.json.*;

import java.util.List;


public class Map {


    public static final String mPath = "test.txt";
    private Readfiles mReadfiles;
    private List<String> mLines;
    private static final String TAG = "GameView";
    private Bitmap bitmap;
    private int x;
    private int y;
    private JSONObject obj ;
    public Map(Context context, int screenX, int screenY) {
        mReadfiles = new Readfiles(context);
        mLines = mReadfiles.readLine(mPath);
        for (String string : mLines) {
            Log.d(TAG, string);
            try {
                JSONObject type = new JSONObject(string);
                JSONArray tiles = type.getJSONArray("Tiles");
                for (int i=0; i< tiles.length(); i++ ) {
                    JSONObject tile = (JSONObject) tiles.get(i);
                    System.out.println(tile.getInt("Type"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile2);
        bitmap = Bitmap.createScaledBitmap(bitmap,100, 100, false);

        x = screenX / 2 - bitmap.getHeight() / 2;
        y = screenY / 2 - bitmap.getWidth() / 2;

    }
    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void drawMap(Canvas canvas, Paint paint) {
        int i = 0;
        int j = 0;
        int size = 1;
        int x = 0;
        int y = 80 *  (-(size - 1)/2);

        while (j < size){
            while (i < size){
                canvas.drawBitmap(getBitmap(), getX() + x, getY() + y, paint);
                x -= 100/2;
                y += 80/2;
                i += 1;
            }
            i = 0;
            j += 1;
            x = j * 100/2;
            y = 80 * (-(size-1)/2) + j * 80/2;
        }
    }
}

