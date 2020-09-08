package com.ianarbuckle.conferencesapi.controller

import com.ianarbuckle.conferencesapi.models.Conference
import com.ianarbuckle.conferencesapi.service.ConferenceService
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.*
import io.ktor.response.respond
import io.ktor.routing.*
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.updateMany

fun Route.conferenceRoutes(service: ConferenceService) {

    route("/conferences") {

        get {
            call.respond(HttpStatusCode.OK, service.findAll())
        }

        get("/{id}") {
            val id = call.parameters["id"]
            id?.let {
                service.findOne(id)?.let { conference ->
                    call.respond(
                        HttpStatusCode.OK, conference
                    )
                }
            }
        }

        post<Conference>("") { request ->
            service.insertEntity(request)

            call.respond(HttpStatusCode.Created)
        }

        put("") {
            val requestBody = call.receiveOrNull<Conference>()
            requestBody?.let {
                call.respond(HttpStatusCode.OK, service.updateEntity(it))
            }
        }

        delete("/{id}") {
            val parameters = call.parameters
            val id = parameters.entries().find { it.key == "id" }?.value?.first()
            call.respond(HttpStatusCode.OK, service.deleteEntity(id ?: ""))
        }

    }
}