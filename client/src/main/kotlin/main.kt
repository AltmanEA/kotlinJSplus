import react.dom.render
import react.redux.provider
import react.router.dom.hashRouter
import kotlin.browser.document
import redux.*
import container.*

fun main() {
    render(document.getElementById("root")) {
        provider(store()) {
            hashRouter {
                appContainer {}
            }
        }
    }
}

