package data

import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    val name: String
) {
    override fun toString(): String = name
}
