package org.robospock.sample.simple.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.EditText;

import junit.framework.Assert;

import org.robospock.sample.simple.SimpleActivity;

public class SimpleActivityTest extends ActivityUnitTestCase<SimpleActivity> {

    SimpleActivity simpleActivity;

    public SimpleActivityTest() {
        super(SimpleActivity.class);

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        simpleActivity = startActivity(new Intent(Intent.ACTION_MAIN), null, null);
        getInstrumentation().callActivityOnCreate(simpleActivity, null);
    }

    public void testInflatingViews() {
        // then
        Assert.assertNotNull(simpleActivity.editText);
        Assert.assertNotNull(simpleActivity.button);
    }

    public void testEditTextHint(){
        // given
        EditText editText = simpleActivity.editText;

        // then
        Assert.assertEquals("Type Here", editText.getHint());
    }

    public void testButtonText(){
        // given
        Button button = simpleActivity.button;

        // then
        Assert.assertEquals("Go Toast!", button.getText());
    }

    public void testClickListener(){
        // only in API 15 :(
        Assert.assertNotNull(simpleActivity.button.hasOnClickListeners());
    }
}
