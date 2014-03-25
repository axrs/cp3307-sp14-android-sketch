package com.cadcoder.sketch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MyActivity extends Activity {

    private static final int SETTINGS_ACTIVITY_SAVED = 1;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_settings:
                CanvasView view = (CanvasView) findViewById(R.id.sketch);

                Intent intent = new Intent(this, SettingsActivity.class);

                intent.putExtra("color", view.getPenColor());
                intent.putExtra("width", view.getStrokeWidth());
                startActivityForResult(intent, SETTINGS_ACTIVITY_SAVED);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SETTINGS_ACTIVITY_SAVED:
                if (resultCode == RESULT_OK) {
                    Bundle b = data.getExtras();
                    CanvasView view = (CanvasView) findViewById(R.id.sketch);
                    view.setPenColor(b.getInt("color"));
                    view.setStrokeWidth(b.getInt("width"));
                }
                break;
        }
    }
}
