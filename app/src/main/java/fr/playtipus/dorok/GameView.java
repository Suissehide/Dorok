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

import fr.playtipus.dorok.GameEngine.GameEngine;

public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    long fps;
    private long timeThisFrame;

    private Thread gameThread = null;
    private GameEngine gameEngine;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private static final String TAG = "GameView";
    private float initialX, initialY;
    private int[] dir;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        gameEngine = new GameEngine(context, screenX, screenY);

        //initializing drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();

        dir = new int[4];
    }

    @Override
    public void run() {
        while (playing) {
            // Capture the current time in milliseconds in startFrameTime
            long startFrameTime = System.currentTimeMillis();

            update();
            draw();
            control();

            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }
        }
    }


    private void update() {
        //Log.d(TAG, "Val=" + dir[0] + dir[1] + dir[2] + dir[3]);
        gameEngine.update(dir);

        //reset dir
        Arrays.fill(dir, 0);
    }

    private void draw() {

        if (surfaceHolder.getSurface().isValid()) {
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();
            //drawing a background color for canvas
            canvas.drawColor(Color.BLACK);

            gameEngine.draw(canvas, paint);

            paint.setColor(Color.argb(255,  249, 129, 0));
            paint.setTextSize(45);
            canvas.drawText("FPS:" + fps, 20, 40, paint);

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
                gameEngine.getPlayer().setDir(dir);
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
