FROM eclipse-temurin:21-jdk-alpine-3.23 as build
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

RUN ./gradlew dependencies --no-daemon

COPY src src
RUN ./gradlew bootJar --no-daemon -x test

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

# Standard Spring Boot port
EXPOSE 8081
EXPOSE 5432

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]