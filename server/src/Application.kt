package ru.altmanEA.example.kotlinJsPlus

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import data.*
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.request.receive
import io.ktor.serialization.json

var state = State(
    mapOf(),
    mapOf(),
    mapOf()
)

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        json()
    }
    install(CORS) {
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        header(HttpHeaders.Origin)
        header(HttpHeaders.ContentType)
        anyHost()
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


