package com.cadcoder.sketch;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View {

    private Path path;
    private Paint pen;
    private Paint canvasPaint;
    private Canvas canvas;
    private Bitmap canvasBitmap;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void clearCanvas() {
        canvas = null;
        canvasBitmap = null;
        invalidate();
    }

    public Bitmap getCanvasBitmap() {
        if (canvasBitmap == null) {
            canvasBitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
        }
        return canvasBitmap;
    }

    public void setCanvasBitmap(Bitmap canvasBitmap) {
        this.canvasBitmap = canvasBitmap;
    }

    public Canvas getCanvas() {
        if (canvas == null) {
            canvas = new Canvas(getCanvasBitmap());
        }
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Path getPath() {
        if (path == null) {
            path = new Path();
        }
        return path;
    }

    /**
     * Gets the current drawing pen
     *
     * @return Paint drawing pen
     */
    public Paint getPen() {
        if (pen == null) {
            pen = new Paint();
            pen.setColor(Color.BLUE);
            pen.setAntiAlias(true);

            pen.setStrokeWidth(8);
            pen.setStyle(Paint.Style.STROKE);
            pen.setStrokeJoin(Paint.Join.ROUND);
            pen.setStrokeCap(Paint.Cap.ROUND);
        }
        return pen;
    }

    public void setStrokeWidth(int width) {
        getPen().setStrokeWidth(width);
    }

    public int getStrokeWidth() {
        return (int) getPen().getStrokeWidth();
    }

    public int getPenColor() {
        return getPen().getColor();
    }

    public void setPenColor(int c) {
        getPen().setColor(c);
    }

    /**
     * Sets the drawing pen
     *
     * @param pen Drawing pen
     */
    public void setPen(Paint pen) {
        this.pen = pen;
    }


    public Paint getCanvasPaint() {
        if (canvasPaint == null) {
            canvasPaint = new Paint(Paint.DITHER_FLAG);
        }
        return canvasPaint;
    }

    /**
     * Sets the canvas paint
     *
     * @param canvasPaint
     */
    public void setCanvasPaint(Paint canvasPaint) {
        this.canvasPaint = canvasPaint;
    }


    /**
     * Handles a view size changed event
     *
     * @param w    View width
     * @param h    View height
     * @param oldw Previous view width
     * @param oldh Previous view height
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setCanvasBitmap(Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888));
        setCanvas(new Canvas(getCanvasBitmap()));
    }

    /**
     * Draws onto the specified canvas
     *
     * @param canvas Target canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(getCanvasBitmap(), 0, 0, getCanvasPaint());
        canvas.drawPath(getPath(), getPen());
    }

    /**
     * Touch event handler
     *
     * @param event
     * @return True event handled
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            //Touch Start Event
            case MotionEvent.ACTION_DOWN:
                getPath().moveTo(x, y);
                break;
            //Touched and moving
            case MotionEvent.ACTION_MOVE:
                getPath().lineTo(x, y);
                break;
            //Touch Release
            case MotionEvent.ACTION_UP:
                getCanvas().drawPath(getPath(), getPen());
                getPath().reset();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }
}
