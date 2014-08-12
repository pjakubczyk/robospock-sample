package org.robospock.sample.simple.test;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

import junit.framework.Assert;

import org.robospock.sample.simple.SimpleActivity;

public class ActivityInstrumentTest extends ActivityUnitTestCase<SimpleActivity> {

    SimpleActivity simpleActivity;

    public ActivityInstrumentTest() {
        super(SimpleActivity.class);

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        simpleActivity = startActivity(new Intent(Intent.ACTION_MAIN), null, null);
        getInstrumentation().callActivityOnCreate(simpleActivity, null);
    }

    public void testInflatingViews() {

        Assert.assertNotNull(simpleActivity.searchEt);
        Assert.assertNotNull(simpleActivity.searchBtn);
        Assert.assertNotNull(simpleActivity.listView);
    }

    public void testSettingListAdapter(){
        Assert.assertNotNull(simpleActivity.listView.getAdapter());
    }

    public void testClickListener(){
        // only in API 15 :(
        Assert.assertNotNull(simpleActivity.searchBtn.hasOnClickListeners());
    }
}
