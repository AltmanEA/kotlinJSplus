package data

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val firstname: String,
    val surname: String
) {
    override fun toString(): String =
        "$firstname $surname"
}
