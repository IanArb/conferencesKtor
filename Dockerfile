FROM openjdk:8

ENV APPLICATION_USER ktor
RUN adduser -D -g '' $APPLICATION_USER

RUN mkdir /app
RUN chown -R $APPLICATION_USER /app

USER $APPLICATION_USER

COPY build.gradle.kts settings.gradle.kts gradlew gradle.properties ./app
COPY gradle ./app/gradle
RUN ./gradlew build || return 0
COPY . .
RUN ./gradlew build

WORKDIR /build
COPY ./build/libs/conferences-api-0.0.1-all.jar /app/conferences-api-0.0.1-all.jar

CMD ["java", "-server", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-XX:InitialRAMFraction=2", "-XX:MinRAMFraction=2", "-XX:MaxRAMFraction=2", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=100", "-XX:+UseStringDeduplication", "-jar", "conferences-api-0.0.1-all.jar"]