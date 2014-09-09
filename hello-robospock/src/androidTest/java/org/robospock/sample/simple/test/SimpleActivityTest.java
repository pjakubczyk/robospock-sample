package org.robospock.sample.simple.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.EditText;

import org.robospock.sample.simple.SimpleActivity;

import static android.content.Intent.ACTION_MAIN;

public class SimpleActivityTest extends ActivityUnitTestCase<SimpleActivity> {

    SimpleActivity simpleActivity;

    public SimpleActivityTest() {
        super(SimpleActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        simpleActivity = startActivity(new Intent(ACTION_MAIN), null, null);
        getInstrumentation().callActivityOnCreate(simpleActivity, null);
    }

    public void testInflatingViews() {
        // expect
        assertNotNull(simpleActivity.editText);
        assertNotNull(simpleActivity.button);
    }

    public void testEditTextHint() {
        // given
        EditText editText = simpleActivity.editText;

        // expect
        assertEquals("Type Here", editText.getHint());
    }

    public void testButtonText() {
        // given
        Button button = simpleActivity.button;

        // expect
        assertEquals("Go Toast!", button.getText());
    }

    public void testClickListener() {
        // only in API 15 :(
        assertNotNull(simpleActivity.button.hasOnClickListeners());
    }
}
