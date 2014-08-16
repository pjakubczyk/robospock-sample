package org.robospock.sample.simple.robolectric;

import android.widget.EditText;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robospock.sample.simple.ToastButtonListener;

@RunWith(RobolectricTestRunner.class)
public class ToastButtonListenerTest {


    private EditText editText;
    private ToastButtonListener toastButtonListener;

    @Before
    public void setUp() throws Exception {
        editText = new EditText(Robolectric.application);

        toastButtonListener = new ToastButtonListener(Robolectric.application, editText);
    }

    @Test
    public void testClickShowToast() {
        toastButtonListener.onClick(editText);

        // then
        // :( Can't test toast
    }

    @Test
    public void testShortText1() {
        // given
        editText.setText("a");

        // when
        CharSequence res = toastButtonListener.trimText();

        // then
        Assert.assertEquals("a", res);
    }

    @Test
    public void testShortText2() {
        // given
        editText.setText("ab");

        // when
        CharSequence res = toastButtonListener.trimText();

        // then
        Assert.assertEquals("ab", res);
    }

    @Test
    public void testShortText3() {
        // given
        editText.setText("abc");

        // when
        CharSequence res = toastButtonListener.trimText();

        // then
        Assert.assertEquals("abc", res);
    }

    @Test
    public void testNormalTest1() {
        // given
        editText.setText("abcd");

        // when
        String res = toastButtonListener.trimText().toString();

        // then
        Assert.assertEquals("d", res);
    }

    @Test
    public void testNormalTest2() {
        // given
        editText.setText("abcde");

        // when
        String res = toastButtonListener.trimText().toString();

        // then
        Assert.assertEquals("de", res);
    }

    @Test
    public void testLongText1() {
        // given
        editText.setText("a1234567890");

        // when
        String res = toastButtonListener.trimText().toString();

        // then
        Assert.assertEquals("3456789", res);
    }

    @Test
    public void testLongText2() {
        // given
        editText.setText("a12345678901");

        // when
        String res = toastButtonListener.trimText().toString();

        // then
        Assert.assertEquals("3456789", res);
    }

    @Test
    public void testLongText3() {
        // given
        editText.setText("a123456789012");

        // when
        String res = toastButtonListener.trimText().toString();

        // then
        Assert.assertEquals("3456789", res);
    }
}
