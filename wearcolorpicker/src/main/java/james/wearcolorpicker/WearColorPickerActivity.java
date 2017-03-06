package james.wearcolorpicker;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.widget.NestedScrollView;
import android.support.wearable.view.drawer.WearableActionDrawer;
import android.support.wearable.view.drawer.WearableDrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

public class WearColorPickerActivity extends Activity {

    public static final String EXTRA_COLOR = "james.wearcolorpicker.EXTRA_COLOR";

    private WearableDrawerLayout drawerLayout;
    private WearableActionDrawer actionDrawer;
    private NestedScrollView scrollView;

    private EditText editText;
    private SeekBar red, blue, green;

    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        drawerLayout = (WearableDrawerLayout) findViewById(R.id.drawerLayout);
        actionDrawer = (WearableActionDrawer) findViewById(R.id.actionDrawer);
        scrollView = (NestedScrollView) findViewById(R.id.scrollView);
        editText = (EditText) findViewById(R.id.editText);
        red = (SeekBar) findViewById(R.id.red);
        blue = (SeekBar) findViewById(R.id.blue);
        green = (SeekBar) findViewById(R.id.green);

        drawerLayout.peekDrawer(Gravity.BOTTOM);
        actionDrawer.lockDrawerClosed();
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == 0 || scrollY < oldScrollY)
                    drawerLayout.peekDrawer(Gravity.BOTTOM);
                else drawerLayout.closeDrawer(Gravity.BOTTOM);
            }
        });

        red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    color = Color.argb(255, seekBar.getProgress(), Color.green(color), Color.blue(color));
                    setColor(color);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    color = Color.argb(255, Color.red(color), seekBar.getProgress(), Color.blue(color));
                    setColor(color);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    color = Color.argb(255, Color.red(color), Color.green(color), seekBar.getProgress());
                    setColor(color);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                color = Color.parseColor(editText.getText().toString());
                actionDrawer.setBackgroundColor(color);
                red.setProgress(Color.red(color));
                blue.setProgress(Color.blue(color));
                green.setProgress(Color.green(color));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        findViewById(R.id.action_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        color = getIntent().getIntExtra(EXTRA_COLOR, Color.BLACK);
        setColor(color);
    }

    private void setColor(@ColorInt int color) {
        editText.setText(String.format("#%06X", 0xFFFFFF & color));
        actionDrawer.setBackgroundColor(color);
        red.setProgress(Color.red(color));
        blue.setProgress(Color.blue(color));
        green.setProgress(Color.green(color));
    }
}
