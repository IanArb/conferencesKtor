package com.ianarbuckle.conferencesapi.repository

import com.ianarbuckle.conferencesapi.Constants
import com.ianarbuckle.conferencesapi.models.Conference
import org.litote.kmongo.coroutine.CoroutineClient

class ConferenceRepository(private val coroutineClient: CoroutineClient) {

    suspend fun insertConference(request: Conference) = collection().insertOne(request)

    suspend fun findAllConferences(): List<Conference> =
        collection()
            .find()
            .toList()

    suspend fun findOneConference(id: String): Conference? = collection().findOneById(id)

    suspend fun deleteConference(id: String) = collection().deleteOneById(id)

    suspend fun updateConference(requestBody: Conference) =
        collection().updateOneById(requestBody._id ?: "", requestBody)

    private fun collection() =
        coroutineClient
            .getDatabase(Constants.DATABASE_NAME)
            .getCollection<Conference>(Constants.COLLECTION_NAME_V2)

}