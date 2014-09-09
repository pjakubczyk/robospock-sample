package org.robospock.sample.simple;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.EditText;


public class SimpleActivity extends ActionBarActivity {

    public EditText editText;
    public Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_acitivity);

        editText = (EditText) findViewById(R.id.edit_text);

        button = (Button) findViewById(R.id.show_toast);

        button.setOnClickListener(new ToastButtonListener(this, editText));
    }

}
