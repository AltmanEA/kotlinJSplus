import data.Lesson
import data.State
import data.Student
import kotlinx.serialization.json.Json
import org.w3c.xhr.XMLHttpRequest
import redux.*
import kotlin.browser.window
import kotlin.js.Promise

fun main(){
    fun testData(store: Store<ReduxState, RAction, WrapperAction>) =
        store.apply {
            dispatch(AddLesson(Lesson("Lecture")))
            dispatch(AddLesson(Lesson("Practice")))
            dispatch(AddLesson(Lesson("Exam")))
            dispatch(AddStudent(Student("Sheldon", "Cooper")))
            dispatch(AddStudent(Student("Leonard", "Hofstadter")))
            dispatch(AddStudent(Student("Howard", "Wolowitz")))
        }

    window.onload = {
        val store = testData(store())
        postState(store.getState().state)
        store.dispatch(RemoveLesson(0))
        store.dispatch(FetchState)
        console.info(store.getState())
        window.setTimeout({console.info(store.getState())},1000)
    }
}

