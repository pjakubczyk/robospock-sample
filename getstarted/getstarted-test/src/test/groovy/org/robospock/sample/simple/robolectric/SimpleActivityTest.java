package org.robospock.sample.simple.robolectric;

import android.widget.Button;
import android.widget.EditText;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robospock.sample.simple.SimpleActivity;

@RunWith(RobolectricTestRunner.class)
public class SimpleActivityTest {

    SimpleActivity simpleActivity;

    @Before
    public void setup(){
        simpleActivity = Robolectric.buildActivity(SimpleActivity.class).create().get();
    }

    @Test
    public void testInflatingViews(){
        Assert.assertNotNull(simpleActivity.button);
        Assert.assertNotNull(simpleActivity.editText);
    }

    @Test
    public void testEditTextHint(){
        // given
        EditText editText = simpleActivity.editText;

        // then
        Assert.assertEquals("Type Here", editText.getHint());
    }

    @Test
    public void testButtonText(){
        // given
        Button button = simpleActivity.button;

        // then
        Assert.assertEquals("Go Toast!", button.getText());
    }

    @Test
    public void testClickListener(){
        // only in API 15 :(
        Assert.assertNotNull(simpleActivity.button.hasOnClickListeners());
    }
}
