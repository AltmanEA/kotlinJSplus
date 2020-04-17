//import data.Lesson
//import data.State
//import data.Student
//import redux.*
//import kotlin.browser.window
//
//fun main() {
//    fun testData(store: Store<State, RAction, WrapperAction>) =
//        store.apply {
//            dispatch(AddLesson(Lesson("Lecture")))
//            dispatch(AddLesson(Lesson("Practice")))
//            dispatch(AddLesson(Lesson("Exam")))
//            dispatch(AddStudent(Student("Sheldon", "Cooper")))
//            dispatch(AddStudent(Student("Leonard", "Hofstadter")))
//            dispatch(AddStudent(Student("Howard", "Wolowitz")))
//        }
//
//    window.onload = {
//        val store = testData(store())
//        console.info(store.getState().lessons.size)
//        store.dispatch(AddLesson(Lesson("test lesson")))
//        console.info(store.getState().lessons.size)
//        console.info(store.getState().lessons[3]?.name)
//        console.info(store.getState().presents.size)
//        store.dispatch(RemoveLesson(0))
//        console.info(store.getState().lessons.size)
//        console.info(store.getState().lessons[3]?.name)
//        console.info(store.getState().presents.size)
//        store.dispatch(ChangeLesson(1, Lesson("new lesson")))
//        console.info(store.getState().lessons.size)
//        console.info(store.getState().lessons.filter {
//            it.value.name.contains("lesson")
//        }.size)
//    }
//}