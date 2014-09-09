package org.robospock.sample.simple.mock

import android.view.View
import android.widget.Button
import org.robolectric.Robolectric
import pl.polidea.robospock.RoboSpecification

class MockingSpec extends RoboSpecification {


    def "spocking example"() {
        given:
        def button = new Button(Robolectric.application)

        and:
        def mock = Mock(View.OnClickListener)

        and:
        button.setOnClickListener(mock)

        when:
        button.performClick()

        then:
        1 * mock.onClick(_)
    }

    def "stubbing example"(){
        when:
        def mock = Mock(List) {
            get(_) >> "stub!"
        }

        then:
        "stub!" == mock.get(0)
    }
}