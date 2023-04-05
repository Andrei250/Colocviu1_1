package ro.pub.cs.systems.eim.colocviu1_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviu1_1MainActivity extends AppCompatActivity {
    private TextView label;
    private Button north, west, east, south, navigate;
    ButtonClickListener buttonClickListener = new ButtonClickListener();
    private int pressedButtons = 0;
    private IntentFilter intentFilter = new IntentFilter();

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
                case R.id.navigate_btn:
                    Intent newActivityIntent = new Intent(getApplicationContext(), Colocviu1_1SecondaryActivity.class);

                    newActivityIntent.putExtra(Constants.INTENT_TAG, txt);
                    startActivityForResult(newActivityIntent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);

                    label.setText("");
                    pressedButtons = 0;
                    break;
            }

            if (pressedButtons == 4) {
                Intent intent = new Intent(getApplicationContext(), Colocviu1_1Service.class);
                intent.putExtra(Constants.INTENT_TAG, label.getText());
                getApplicationContext().startService(intent);
            }
        }

    }


    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.BROADCAST_RECEIVER_TAG, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
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

        intentFilter.addAction(Constants.INTENT_FILTER);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(Constants.PRESSED_BTNS, pressedButtons);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.PRESSED_BTNS)) {
            pressedButtons = savedInstanceState.getInt(Constants.PRESSED_BTNS);
        }

        Log.i(Constants.PRESSED_BTN_TAG, String.valueOf(pressedButtons));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            if (resultCode == 0)
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviu1_1Service.class);
        stopService(intent);
        super.onDestroy();
    }
}