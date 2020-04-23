import data.*
import redux.*
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {

    fun testData(store: Store<FullState, RAction, WrapperAction>) =
        store.apply {
            dispatch(AddLesson(Lesson("Lecture")))
            dispatch(AddLesson(Lesson("Practice")))
            dispatch(AddLesson(Lesson("Exam")))
            dispatch(AddStudent(Student("Sheldon", "Cooper")))
            dispatch(AddStudent(Student("Leonard", "Hofstadter")))
            dispatch(AddStudent(Student("Howard", "Wolowitz")))
        }

    @Test
    fun lessonsTest() {
        val store = testData(store())
        assertEquals(3, store.getState().lessons.size)
        store.dispatch(AddLesson(Lesson("test lesson")))
        assertEquals(4, store.getState().lessons.size)
        assertEquals("test lesson", store.getState().lessons[3]?.name)
        assertEquals(4, store.getState().presents.size)
        store.dispatch(RemoveLesson(0))
        assertEquals(3, store.getState().lessons.size)
        assertEquals("test lesson", store.getState().state.lessons[3]?.name)
        assertEquals(3, store.getState().presents.size)
        store.dispatch(ChangeLesson(1, Lesson("new lesson")))
        assertEquals(3, store.getState().lessons.size)
        assertEquals(2, store.getState().lessons.filter {
            it.value.name.contains("lesson")
        }.size)
    }

    @Test
    fun studentsTest() {
        val store = testData(store())
        assertEquals(3, store.getState().students.size)
        store.dispatch(AddStudent(Student("test", "student")))
        assertEquals(4, store.getState().students.size)
        assertEquals("test", store.getState().students[3]?.firstname)
        assertEquals(4, store.getState().presents[0]?.size)
        store.dispatch(RemoveStudent(0))
        assertEquals(3, store.getState().students.size)
        assertEquals("student", store.getState().students[3]?.surname)
        assertEquals(3, store.getState().presents[0]?.size)
        store.dispatch(ChangeStudent(1, Student("new", "student")))
        assertEquals(3, store.getState().students.size)
        assertEquals(2, store.getState().students.filter {
            it.value.surname.contains("student")
        }.size)
    }

    @Test
    fun presentsTest() {
        val store = testData(store())
        assertEquals(3, store.getState().presents.size)
        assertEquals(false, store.getState().presents[0]?.get(0))
        store.dispatch(ChangePresent(0, 0))
        assertEquals(true, store.getState().presents[0]?.get(0))
        store.dispatch(ChangePresent(0, 0))
        assertEquals(false, store.getState().presents[0]?.get(0))
        store.dispatch(AddLesson(Lesson("test lesson")))
        store.dispatch(AddStudent(Student("test", "student")))
        assertEquals(false, store.getState().presents[3]?.get(3))
        store.dispatch(ChangePresent(3, 3))
        store.dispatch(RemoveLesson(0))
        store.dispatch(RemoveStudent(1))
        assertEquals(true, store.getState().presents[3]?.get(3))
    }
}


