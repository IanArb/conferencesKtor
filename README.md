# Conferences Ktor App

An example usage of Ktor.

The applications displays a list of conferences retrieved from https://androidstudygroup.github.io/conferences/

This repository accepts pull requests and all contributions are welcome!

### Ktor
You need to add Run/Debug configuration to Edit Configurations targetting main class ```com.ianarbuckle.conferencesapi.ApplicationKt```

You can configure your port and your mongo URI in the ``application.conf`` file 

```
ktor {
    deployment {
        port = 8080
    }
    application {
        modules = [ com.ianarbuckle.conferencesapi.ApplicationKt.module ]
    }
    mongoUri = ${MONGO_URI}
}
```

#### Languages, libraries and tools used
* [Kotlin](https://kotlinlang.org/)
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization)
* [Ktor client library](https://github.com/ktorio/ktor)
* [KMongo](https://github.com/Litote/kmongo)
* [MongoDB Atlas](https://www.mongodb.com/cloud/atlas)