package james.wearcolorpickersample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import james.wearcolorpicker.WearColorPickerActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    private View content;

    private int color = Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(this);
        content = findViewById(android.R.id.content);
        content.setBackgroundColor(color);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, WearColorPickerActivity.class);
        intent.putExtra(WearColorPickerActivity.EXTRA_COLOR, color);
        startActivityForResult(intent, 247);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 247 && data.hasExtra(WearColorPickerActivity.EXTRA_COLOR)) {
            if (resultCode == RESULT_OK) {
                color = data.getIntExtra(WearColorPickerActivity.EXTRA_COLOR, Color.BLACK);
                Toast.makeText(this, "Confirmed: " + String.format("#%06X", 0xFFFFFF & color), Toast.LENGTH_SHORT).show();
                content.setBackgroundColor(color);
            } else {
                Toast.makeText(this, "Cancelled: " + String.format("#%06X", 0xFFFFFF & color), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
