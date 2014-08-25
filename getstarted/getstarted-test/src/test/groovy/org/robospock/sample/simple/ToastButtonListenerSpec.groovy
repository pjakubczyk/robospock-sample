package org.robospock.sample.simple

import android.widget.EditText
import org.robolectric.Robolectric
import org.robolectric.shadows.ShadowToast
import pl.polidea.robospock.RoboSpecification
import spock.lang.Unroll


class ToastButtonListenerSpec extends RoboSpecification {


    @Unroll
    def "should trim text from '#input' to '#result'"() {
        given:
        def editText = new EditText(Robolectric.application)

        def toastButtonListener = new ToastButtonListener(Robolectric.application, editText)

        and:
        editText.setText(input)

        expect:
        toastButtonListener.trimText() == result

        where:
        input           | result
        "a"             | "a"
        "ab"            | "ab"
        "abc"           | "abc"
        "abcd"          | "d"
        "abcde"         | "de"
        "a1234567890"   | "3456789"
        "a12345678901"  | "3456789"
        "a123456789012" | "3456789"
    }

    def "should show toast on click"(){
        given:
        def editText = new EditText(Robolectric.application)

        def toastButtonListener = new ToastButtonListener(Robolectric.application, editText)

        when:
        editText.setText("a123456789012")

        and:
        toastButtonListener.onClick(null);

        then:
        ShadowToast.textOfLatestToast == "3456789"
    }
}