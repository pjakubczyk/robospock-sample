package org.robospock.sample.simple.test;

import android.test.AndroidTestCase;
import android.widget.TextView;

import junit.framework.Assert;

import org.robospock.sample.simple.SimpleActivity;

public class ActivityTest extends AndroidTestCase {

    public void testSearch() {
        String[] array = {"abcdef", "abc"};

        Assert.assertEquals(3, SimpleActivity.searchStrings("ab", array).size());
    }

    public void testTextView(){
        TextView textView = new TextView(getContext());

        textView.setText("foobar");

        Assert.assertEquals("foobar", textView.getText());
    }


}
