package redux

import data.*
import kotlinx.serialization.json.Json
import kotlin.reflect.KFunction0

class ChangePresent(val lessonID: Int, val studentID: Int) : RAction

class AddStudent(val student: Student) : RAction

class RemoveStudent(val id: Int) : RAction

class ChangeStudent(val id: Int, val newStudent: Student) : RAction

class AddLesson(val lesson: Lesson) : RAction

class RemoveLesson(val id: Int) : RAction

class ChangeLesson(val id: Int, val newLesson: Lesson) : RAction

class RequestState() : RAction

class ReceiveState(val state: State) : RAction

val FetchState = object : RThunk {
    override fun invoke(dispatch: (RAction) -> WrapperAction, getState: KFunction0<*>): WrapperAction {
        dispatch(RequestState())
        fetch(serverUrl())
            .then { it.text() }
            .then {
                dispatch(
                    ReceiveState(
                        Json.parse(State.serializer(), it)
                    )
                )
            }
        return nullAction
    }
}
