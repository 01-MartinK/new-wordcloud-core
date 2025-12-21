    FROM openjdk:21 - jdk - slim
    WORKDIR /app
    RUN ./gradlew build
    COPY ./build/libs/wordcloud-0.0.1-SNAPSHOT.jar /app/app.jar
    EXPOSE 8080
    CMD ["java", "-jar", "wordcloud-0.0.1-SNAPSHOT.jar"]