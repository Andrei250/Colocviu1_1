package ro.pub.cs.systems.eim.colocviu1_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_1SecondaryActivity extends AppCompatActivity {
    private TextView textView;
    private Button register, cancel;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register_btn:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel_btn:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_secondary);

        textView = (TextView) findViewById(R.id.second_text);
        register = (Button) findViewById(R.id.register_btn);
        cancel = (Button) findViewById(R.id.cancel_btn);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.INTENT_TAG)) {
            String value = intent.getStringExtra(Constants.INTENT_TAG);
            textView.setText(value);
        }

        register.setOnClickListener(buttonClickListener);
        cancel.setOnClickListener(buttonClickListener);
    }
}