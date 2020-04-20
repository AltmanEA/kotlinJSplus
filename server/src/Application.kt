package ru.altmanEA.example.kotlinJsPlus

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import data.*
import io.ktor.features.ContentNegotiation
import io.ktor.request.receive
import io.ktor.serialization.json
import io.ktor.serialization.serialization

var state = State(
    arrayOf(
        "Lecture",
        "Practice",
        "Exam"
    ).mapIndexed { index, lesson ->
        index to Lesson(lesson)
    }.toMap(),
    arrayListOf(
        Student("Sheldon", "Cooper"),
        Student("Leonard", "Hofstadter"),
        Student("Howard", "Wolowitz")
    ).mapIndexed { index, student ->
        index to student
    }.toMap(),
    arrayListOf(0, 1, 2).associateWith {
        arrayListOf(0, 1, 2).associateWith { false }
    }
)

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/") {
            call.respond(state)
        }
        post("/") {
            state = call.receive()
            call.respond(HttpStatusCode.OK)
        }
    }
}


