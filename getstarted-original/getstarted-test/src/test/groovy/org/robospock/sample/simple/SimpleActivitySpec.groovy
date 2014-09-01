package org.robospock.sample.simple

import org.robolectric.Robolectric
import pl.polidea.robospock.RoboSpecification

class SimpleActivitySpec extends RoboSpecification {

    SimpleActivity simpleActivity

    def "setup"(){
        simpleActivity = Robolectric.buildActivity(SimpleActivity).create().get()
    }

    def "should inflate views"(){
        expect:
        simpleActivity.button
        simpleActivity.editText
    }

    def "should show edit text hint"(){
        expect:
        simpleActivity.editText.getHint() == "Type Here"
    }

    def "should show button text"(){
        expect:
        simpleActivity.button.text == "Go Toast!"
    }

    def "should assign listener"(){
        expect:
        simpleActivity.button.hasOnClickListeners()
    }

}