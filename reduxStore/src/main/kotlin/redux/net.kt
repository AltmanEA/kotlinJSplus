package redux

import data.State
import kotlinx.serialization.json.Json
import org.w3c.xhr.XMLHttpRequest
import kotlin.js.Promise

@JsModule("cross-fetch")
@JsNonModule
external fun fetch(url: String,
                   options: dynamic= definedExternally): Promise<HTTPResult>

interface HTTPResult{
    fun json(): Promise<dynamic>
    fun text(): Promise<String>
}

fun postState(state: State){
    val stateJson = Json.stringify(State.serializer(), state)
    val xhttp = XMLHttpRequest()
    xhttp.open("POST", serverUrl(), false);
    xhttp.setRequestHeader("Accept", "application/json")
    xhttp.setRequestHeader("Content-Type", "application/json")
    xhttp.onload = {}
    xhttp.send(stateJson)
}

fun serverUrl() = "http://localhost:8000/"

