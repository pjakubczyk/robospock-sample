package org.robospock.sample.simple.mock;

import android.view.View;
import android.widget.Button;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class MockingTest {

    @Test
    public void testMock() {
        // given
        Button button = new Button(Robolectric.application);

        View.OnClickListener mock = Mockito.mock(View.OnClickListener.class);

        button.setOnClickListener(mock);

        // when
        button.performClick();

        // then
        Mockito.verify(mock, Mockito.times(1)).onClick(Matchers.any(View.class));
    }

    @Test
    public void testStub(){
        // given
        List mock = Mockito.mock(List.class);

        // when
        Mockito.when(mock.get(Matchers.anyInt())).thenReturn("stub!");

        // then
        Assert.assertEquals("stub!", mock.get(0));
    }
}
