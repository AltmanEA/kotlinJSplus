import data.State
import io.ktor.application.Application
import io.ktor.http.*
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import kotlinx.serialization.json.Json
import org.junit.Test
import ru.altmanEA.example.kotlinJsPlus.module
import kotlin.test.assertEquals

class AppTest {

    val newStateJson =
        """{"lessons":{"0":{"name":"lesson 0"},"2":{"name":"lesson 2"}},
            |"students":{"1":{"firstname":"student","surname":"1"},"2":{"firstname":"student","surname":"2"}},
            |"presents":{"0":{"1":false,"2":true},"2":{"1":false,"2":false}}
            |}""".trimMargin()

    @Test
    fun testHome() = withTestApplication({ module(true) }) {
        val call = handleRequest(HttpMethod.Post, "/") {
            setBody(newStateJson)
            addHeader("Content-Type", "application/json")
            addHeader("Accept", "application/json")
        }
        assertEquals(HttpStatusCode.OK, call.response.status())
        with(handleRequest(HttpMethod.Get, "/")) {
            assertEquals(HttpStatusCode.OK, response.status())
            val newState = Json.parse(State.serializer(), String(response.byteContent!!))
            assertEquals(2, newState.lessons.size)
            assertEquals(2, newState.students.size)
        }
    }
}