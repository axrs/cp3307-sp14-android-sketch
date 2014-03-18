package com.cadcoder.sketch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void ClearCanvasViews(View sender) {
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        new ClearAllCanvasViewCommand(root).execute();
    }
}
