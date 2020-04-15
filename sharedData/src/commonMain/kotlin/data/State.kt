package data

import kotlinx.serialization.Serializable

typealias LessonState = Map<Int, Lesson>

typealias StudentState = Map<Int, Student>

typealias Presents = Map<Int, Map<Int, Boolean>>

@Serializable
class State(
    val lessons: LessonState,
    val students: StudentState,
    val presents: Presents
)
