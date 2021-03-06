package com.ianarbuckle.conferencesapi.di

import com.ianarbuckle.conferencesapi.repository.ConferenceRepository
import com.ianarbuckle.conferencesapi.service.ConferenceService
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val appModule = module {
    single { (uri: String) -> KMongo.createClient(uri).coroutine }
    single { (client: CoroutineClient) -> ConferenceRepository(client) }
    single { (repository: ConferenceRepository) -> ConferenceService(repository) }
}
