package com.cadcoder.sketch;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Alex on 18/03/14.
 */
public class ClearAllCanvasViewCommand implements Command {
    private ViewGroup vg;

    public ClearAllCanvasViewCommand(ViewGroup viewGroup) {
        vg = viewGroup;
    }

    @Override
    public void execute() {
        clearCanvasViews(vg);
    }

    private void clearCanvasViews(ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            if (child instanceof CanvasView) {
                ((CanvasView) child).clearCanvas();
            } else if (child instanceof ViewGroup) {
                clearCanvasViews((ViewGroup)child);
            }
        }
    }
}
