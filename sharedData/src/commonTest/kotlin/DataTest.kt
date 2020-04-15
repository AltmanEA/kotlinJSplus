import data.Lesson
import data.State
import data.Student
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlin.test.Test
import kotlin.test.assertEquals

class DataTest {
    val json = Json(JsonConfiguration.Stable)

    @Test
    fun lessonTest() {
        assertEquals(
            "{\"name\":\"test lesson\"}",
            json.stringify(
                Lesson.serializer(),
                Lesson("test lesson")
            )
        )
        assertEquals(
            "test lesson",
            json.parse(
                Lesson.serializer(),
                "{\"name\":\"test lesson\"}"
            ).name
        )
    }

    @Test
    fun studentTest() {
        assertEquals(
            "{\"firstname\":\"test\",\"surname\":\"student\"}",
            json.stringify(
                Student.serializer(),
                Student("test", "student")
            )
        )
        assertEquals(
            "student",
            json.parse(
                Student.serializer(),
                "{\"firstname\":\"test\",\"surname\":\"student\"}"
            ).surname
        )
    }

    @Test
    fun stateTest() {
        val state = State(
            lessons = mapOf(
                0 to Lesson("lesson 0"),
                2 to Lesson("lesson 2")
            ),
            students = mapOf(
                1 to Student("student", "1"),
                2 to Student("student", "2")
            ),
            presents = mapOf(
                0 to mapOf(
                    1 to false,
                    2 to true
                ),
                2 to mapOf(
                    1 to false,
                    2 to false
                )
            )
        )
        val stateJson =
            "{\"lessons\":{\"0\":{\"name\":\"lesson 0\"},\"2\":{\"name\":\"lesson 2\"}},\"students\":{\"1\":{\"firstname\":\"student\",\"surname\":\"1\"},\"2\":{\"firstname\":\"student\",\"surname\":\"2\"}},\"presents\":{\"0\":{\"1\":false,\"2\":true},\"2\":{\"1\":false,\"2\":false}}}"
        assertEquals(
            stateJson,
            json.stringify(State.serializer(), state)
        )
        assertEquals(
            2,
            json.parse(State.serializer(), stateJson).students.size
        )
    }
}