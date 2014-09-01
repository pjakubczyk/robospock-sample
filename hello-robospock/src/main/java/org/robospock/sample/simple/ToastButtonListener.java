package org.robospock.sample.simple;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class ToastButtonListener implements View.OnClickListener {

    Context context;
    EditText editText;

    public ToastButtonListener(Context context, EditText editText) {
        this.context = context;
        this.editText = editText;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, trimText(), LENGTH_SHORT).show();
    }

    public CharSequence trimText() {
        Editable editable = editText.getText();

        int length = editable.length();

        if (length < 4) {
            return editable.toString();
        } else if (length < 10) {
            return editable.subSequence(3, length);
        } else {
            return editable.subSequence(3, 10);
        }
    }
}
