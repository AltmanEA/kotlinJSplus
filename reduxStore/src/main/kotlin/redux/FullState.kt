package redux

import data.LessonState
import data.Presents
import data.State
import data.StudentState

class FullState(
    val lessons: LessonState,
    val students: StudentState,
    val presents: Presents,
    val isLoad: Boolean
) {
    constructor(state: FullState, isLoad: Boolean) :
            this(
                state.lessons,
                state.students,
                state.presents,
                isLoad
            )

    constructor(state: State, isLoad: Boolean) :
            this(
                state.lessons,
                state.students,
                state.presents,
                isLoad
            )

    val state: State
        get() = State(lessons, students, presents)
}

fun initialState() =
    FullState(
        mapOf(),
        mapOf(),
        mapOf(),
        true
    )