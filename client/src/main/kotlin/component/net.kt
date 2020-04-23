package component

import kotlinx.html.js.onClickFunction
import react.RProps
import react.dom.button
import react.dom.div
import react.functionalComponent

interface NetProps : RProps {
    var load: ()->Unit
    var save: ()->Unit
}

fun fNet() =
    functionalComponent<NetProps> {props ->
        div {
            button {
                +"Load"
                attrs.onClickFunction = { props.load() }
            }
            button {
                +"Save"
                attrs.onClickFunction = { props.save() }
            }
        }
    }