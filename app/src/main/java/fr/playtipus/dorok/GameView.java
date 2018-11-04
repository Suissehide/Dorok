package fr.playtipus.dorok;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import java.util.Arrays;

import android.util.Log;

public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;

    private Player player;
    private Map map;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private static final String TAG = "GameView";
    private float initialX, initialY;
    private int[] dir;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        player = new Player(context, screenX, screenY);
        map = new Map(context, screenX, screenY);

        //initializing drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();

        dir = new int[4];
    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }


    private void update() {
        player.update();
    }

    private void draw() {
        int i = 0;
        int j = 0;
        int size = 7;
        int x = 100 * (-(size-1)/2);
        int y = 0;

        if (surfaceHolder.getSurface().isValid()) {
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();
            //drawing a background color for canvas
            canvas.drawColor(Color.BLACK);
            //drawing the map

            while (j < size){
                while (i < size){
                    canvas.drawBitmap(map.getBitmap(), map.getX() + x, map.getY() + y, paint);
                    x += 100/2;
                    y -= 100/2;
                    i += 1;
                }
                i = 0;
                j += 1;
                x = 100 * (-(size-1)/2) + j* 100/2;
                y = j * 100/2;
            }

            //Drawing the player
            canvas.drawBitmap(player.getBitmap(), player.getX(), player.getY(), paint);
            //Unlocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        playing = false;
        try {
            //stopping the thread
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        //when the game is resumed
        //starting the thread again
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                float finalX = motionEvent.getX();
                float finalY = motionEvent.getY();

                if (initialY > finalY) {
                    Log.d(TAG, "Down to Up swipe performed");
                    dir[0] = 1;
                }
                if (initialX < finalX) {
                    Log.d(TAG, "Left to Right swipe performed");
                    dir[1] = 1;
                }
                if (initialY < finalY) {
                    Log.d(TAG, "Up to Down swipe performed");
                    dir[2] = 1;
                }
                if (initialX > finalX) {
                    Log.d(TAG, "Right to Left swipe performed");
                    dir[3] = 1;
                }
                player.setDir(dir);

                //reset dir
                Arrays.fill(dir, 0);

                break;

            case MotionEvent.ACTION_DOWN:
                initialX = motionEvent.getX();
                initialY = motionEvent.getY();

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG,"Action was CANCEL");
                break;

            case MotionEvent.ACTION_OUTSIDE:
                Log.d(TAG, "Movement occurred outside bounds of current screen element");
                break;
        }
        return true;
    }
}
