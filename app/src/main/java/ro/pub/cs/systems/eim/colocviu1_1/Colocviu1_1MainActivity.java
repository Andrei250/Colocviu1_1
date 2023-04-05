package ro.pub.cs.systems.eim.colocviu1_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_1MainActivity extends AppCompatActivity {
    private TextView label;
    private Button north, west, east, south, navigate;
    ButtonClickListener buttonClickListener = new ButtonClickListener();
    private int pressedButtons = 0;

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String txt = (String) label.getText();

            switch (view.getId()) {
                case R.id.north_btn:
                    pressedButtons++;
                    if (txt.length() < 1)
                        label.setText("North");
                    else
                        label.setText(txt + ", North");
                    break;

                case R.id.east_btn:
                    pressedButtons++;
                    if (txt.length() < 1)
                        label.setText("East");
                    else
                        label.setText(txt + ", East");
                    break;
                case R.id.south_btn:
                    pressedButtons++;
                    if (txt.length() < 1)
                        label.setText("South");
                    else
                        label.setText(txt + ", South");
                    break;
                case R.id.west_btn:
                    pressedButtons++;
                    if (txt.length() < 1)
                        label.setText("West");
                    else
                        label.setText(txt + ", West");
                    break;
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_main);

        label = (TextView) findViewById(R.id.label_text);
        north = (Button) findViewById(R.id.north_btn);
        south = (Button) findViewById(R.id.south_btn);
        east = (Button) findViewById(R.id.east_btn);
        west = (Button) findViewById(R.id.west_btn);
        navigate = (Button) findViewById(R.id.navigate_btn);

        north.setOnClickListener(buttonClickListener);
        south.setOnClickListener(buttonClickListener);
        east.setOnClickListener(buttonClickListener);
        west.setOnClickListener(buttonClickListener);
        navigate.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.PRESSED_BTNS)) {
                pressedButtons = savedInstanceState.getInt(Constants.PRESSED_BTNS);
            }
        } else {
            pressedButtons = 0;
        }

        Log.i(Constants.PRESSED_BTN_TAG, String.valueOf(pressedButtons));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(Constants.PRESSED_BTNS, pressedButtons);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.PRESSED_BTNS)) {
            pressedButtons = savedInstanceState.getInt(Constants.PRESSED_BTNS);
        }

        Log.i(Constants.PRESSED_BTN_TAG, String.valueOf(pressedButtons));
    }
}