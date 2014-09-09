package org.robospock.sample.simple;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import static org.robospock.sample.simple.R.id.edit_text;
import static org.robospock.sample.simple.R.id.show_toast;
import static org.robospock.sample.simple.R.layout.activity_simple_acitivity;

public class SimpleActivity extends Activity {

    public EditText editText;
    public Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_simple_acitivity);

        editText = (EditText) findViewById(edit_text);

        button = (Button) findViewById(show_toast);

        button.setOnClickListener(new ToastButtonListener(this, editText));
    }
}
