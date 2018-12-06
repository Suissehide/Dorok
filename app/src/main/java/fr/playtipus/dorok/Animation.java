package fr.playtipus.dorok;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import android.graphics.RectF;
import android.util.Log;

public class Animation {

    private Bitmap bmp;
    private long lastTime = 0;
    private int frameLength = 100;
    private int reverse;
    private int backtrace = 0;
    private int paused = 0;

    private int start_row;
    private int start_col;
    private int rows;
    private int cols;

    private int currentFrameX = 0;
    private int currentFrameY = 0;
    private int width;
    private int height;

    private static final String TAG = "Animation";

    // grid 0: start_row, 1: start_col, 2:end_row, 3: end_col
    public Animation(Bitmap bmp, int[] grid, int width, int height, int frameLength, int reverse) {
        this.bmp = bmp;
        this.start_row = grid[0];
        this.start_col = grid[1];
        this.rows = grid[2];
        this.cols = grid[3];
        this.width = width;
        this.height = height;
        this.reverse = reverse;
        this.frameLength = frameLength;
    }

    private void normalUpdate() {
        if (currentFrameX < rows) {
            currentFrameX += 1;
        } else if (currentFrameY < cols) {
            currentFrameY += 1;
            currentFrameX = 0;
        } else {
            currentFrameY = 0;
            currentFrameX = 0;
        }
    }

    private void reverseUpdate() {

        if (backtrace == 0) {
            if (currentFrameX < rows) {
                currentFrameX += 1;
            } else if (currentFrameY < cols) {
                currentFrameY += 1;
                currentFrameX = 0;
            } else {
                backtrace = 1;
            }
        }
        if (backtrace == 1) {
            if (currentFrameX > 0) {
                currentFrameX -= 1;
            } else if (currentFrameY > 0) {
                currentFrameY -= 1;
                currentFrameX = rows;
            }
            if (currentFrameX <= 0 && currentFrameY <= 0) {
                backtrace = 0;
            }
        }
    }

    private void update() {
        long time = System.currentTimeMillis();

        if (time > lastTime + frameLength && paused == 0) {
            if (reverse == 0) {
                normalUpdate();
            } else {
                reverseUpdate();
            }
            lastTime = time;
        }
    }

    public void draw(Canvas canvas, int x, int y, Paint paint) {
        update();
        //Log.d(TAG, Integer.toString((currentFrameX + start_row) * width) + ", " + Integer.toString((currentFrameY + start_col) * height));
        int srcX = (currentFrameX + start_row) * width;
        int srcY = (currentFrameY + start_col) * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(bmp, src, dst, paint);
    }

    public void reset() {
        currentFrameX = 0;
        currentFrameY = 0;
    }

    public void pause() {
        this.paused = 1;
    }

    public void run() {
        this.paused = 0;
    }
}
