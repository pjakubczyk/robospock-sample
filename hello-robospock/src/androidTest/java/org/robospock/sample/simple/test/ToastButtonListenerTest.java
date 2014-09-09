package org.robospock.sample.simple.test;

import android.test.AndroidTestCase;
import android.widget.EditText;

import org.robospock.sample.simple.ToastButtonListener;

public class ToastButtonListenerTest extends AndroidTestCase {

    private EditText editText;
    private ToastButtonListener toastButtonListener;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        editText = new EditText(getContext());
        toastButtonListener = new ToastButtonListener(getContext(), editText);
    }

    public void testClickShowToast() {
        // given
        EditText editText = new EditText(getContext());

        // when
        ToastButtonListener toastButtonListener = new ToastButtonListener(getContext(), editText);
        toastButtonListener.onClick(editText);

        // then
        // :( Can't test toast
    }

    public void testShortText1() {
        // given
        editText.setText("a");

        // when
        CharSequence res = toastButtonListener.trimText();

        // then
        assertEquals("a", res);
    }

    public void testShortText2() {
        // given
        editText.setText("ab");

        // when
        CharSequence res = toastButtonListener.trimText();

        // then
        assertEquals("ab", res);
    }

    public void testShortText3() {
        // given
        editText.setText("abc");

        // when
        CharSequence res = toastButtonListener.trimText();

        // then
        assertEquals("abc", res);
    }

    public void testNormalTest1() {
        // given
        editText.setText("abcd");

        // when
        String res = toastButtonListener.trimText().toString();

        // then
        assertEquals("d", res);
    }

    public void testNormalTest2() {
        // given
        editText.setText("abcde");

        // when
        String res = toastButtonListener.trimText().toString();

        // then
        assertEquals("de", res);
    }

    public void testLongText1() {
        // given
        editText.setText("a1234567890");

        // when
        String res = toastButtonListener.trimText().toString();

        // then
        assertEquals("3456789", res);
    }

    public void testLongText2() {
        // given
        editText.setText("a12345678901");

        // when
        String res = toastButtonListener.trimText().toString();

        // then
        assertEquals("3456789", res);
    }

    public void testLongText3() {
        // given
        editText.setText("a123456789012");

        // when
        String res = toastButtonListener.trimText().toString();

        // then
        assertEquals("3456789", res);
    }
}
