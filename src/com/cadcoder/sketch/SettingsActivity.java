package com.cadcoder.sketch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

/**
 * Created by Alex on 25/03/14.
 */
public class SettingsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        setWidth(bundle.getInt("width"));
        setColor(bundle.getInt("color"));
    }

    public void setWidth(int width) {
        SeekBar bar = (SeekBar) findViewById(R.id.optionWidth);
        bar.setProgress(width);
    }

    public void setColor(int color) {

        View v = null;
        switch (color) {
            case Color.RED:
                v = findViewById(R.id.optionRed);
                break;
            case Color.YELLOW:
                v = findViewById(R.id.optionYellow);

                break;
            case Color.GREEN:
                v = findViewById(R.id.optionGreen);

                break;
            case Color.BLUE:
                v = findViewById(R.id.optionBlue);

                break;
            case Color.MAGENTA:
                v = findViewById(R.id.optionMagenta);
                break;
        }

        if (v != null) {
            ((RadioButton) v).setChecked(true);
        }
    }

    private int getSelectedColor() {
        RadioGroup group = (RadioGroup) findViewById(R.id.colorOptions);

        int c = Color.BLUE;
        switch (group.getCheckedRadioButtonId()) {
            case R.id.optionRed:
                c = Color.RED;
                break;
            case R.id.optionYellow:
                c = Color.YELLOW;
                break;
            case R.id.optionGreen:
                c = Color.GREEN;
                break;
            case R.id.optionBlue:
                c = Color.BLUE;
                break;
            case R.id.optionMagenta:
                c = Color.MAGENTA;
                break;
        }

        return c;
    }

    private int getSelectedWidth() {
        SeekBar bar = (SeekBar) findViewById(R.id.optionWidth);

        return (bar.getProgress() == 0) ? 1 : bar.getProgress();
    }


    public void saveSettings(View sender) {
        Intent selectedSettings = new Intent();
        selectedSettings.putExtra("color", getSelectedColor());
        selectedSettings.putExtra("width", getSelectedWidth());

        setResult(RESULT_OK, selectedSettings);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}