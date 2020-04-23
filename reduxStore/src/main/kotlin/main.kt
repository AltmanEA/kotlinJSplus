//import data.Lesson
//import data.Student
//import redux.*
//import kotlin.browser.window
//
//fun main(){
//    fun testData(store: Store<FullState, RAction, WrapperAction>) =
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
//        postState(store.getState().state)
//        store.dispatch(RemoveLesson(0))
//        store.dispatch(FetchState)
//        console.info(store.getState())
//        window.setTimeout({console.info(store.getState())},1000)
//    }
//}
//
