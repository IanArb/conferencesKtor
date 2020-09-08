package com.ianarbuckle.conferencesapi.service

import com.ianarbuckle.conferencesapi.models.Conference
import com.ianarbuckle.conferencesapi.repository.ConferenceRepository

class ConferenceService(private val repository: ConferenceRepository) {

    suspend fun findAll(): List<Conference> = repository.findAllConferences()

    suspend fun findOne(id: String): Conference? = repository.findOneConference(id)

    suspend fun insertEntity(request: Conference) = repository.insertConference(request)

    suspend fun deleteEntity(id: String) = repository.deleteConference(id)

    suspend fun updateEntity(requestBody: Conference) = repository.updateConference(requestBody)

}