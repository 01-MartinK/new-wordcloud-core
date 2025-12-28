# Wordcloud Core Service

The **Wordcloud Core** is a Spring Boot application written in Kotlin that serves as the backend orchestrator for the
Wordcloud generation system. It handles file uploads, manages processing tasks via RabbitMQ, and persists application
state in PostgreSQL.

## Tech Stack

* **Language:** Kotlin (Java 21)
* **Framework:** Spring Boot
* **Database:** PostgreSQL (with Flyway for schema migration)
* **Messaging:** RabbitMQ (AMQP)
* **Build Tool:** Gradle (Kotlin DSL)
* **Containerization:** Docker & Docker Compose

### Components

[Wordcloud Core](https://github.com/01-MartinK/new-wordcloud-core) <- this

[Wordcloud Worker](https://github.com/01-MartinK/new-wordcloud-worker)

[Wordcloud Frontend](https://github.com/01-MartinK/new-wordcloud-frontend)

## Architecture Overview

1. **Rest API**: Exposes endpoints for clients to upload text files and check processing status.
2. **Storage**: Temporarily stores uploaded files on the local file system.
3. **Database**: Persists task status (`Pending`, `InProgress`, `Complete`) and results in PostgreSQL.
4. **Messaging**: Publishes parsing requests to a **RabbitMQ** exchange to be consumed by the Worker service.

## Getting Started

### Prerequisites

* JDK 21
* Docker & Docker Compose (recommended for running the full stack)

### Running with Docker Compose

The project includes a `compose.yaml` file to orchestrate the Core service, Worker, Frontend, Database, and Message
Broker.

To start the stack:

```bash
docker-compose up

docker-compose up -d #for headless version
```

The frontend will be accessible at ``` localhost:8080 ```

To clear and pull the images again:

```bash
docker-compose stop
docker-compose rm -f
docker-compose pull   
```

To run it locally:

```bash
./gradlew build
DB_PASSWORD=<password> java -jar build/libs/wordcloud-core-0.0.1-SNAPSHOT.jar
```

* **Containerization:** Docker & Docker Compose

## Configuration & Environment Variables

The application uses a combination of `src/main/resources/application.yml` and environment variables. For local
development, you should create a `.env` file in the project root.

### Environment Variables

| Variable Name          | Description               | Default                                      |
|------------------------|---------------------------|----------------------------------------------|
| `DB_USERNAME`          | PostgreSQL username       | `postgres`                                   |
| `DB_PASSWORD`          | PostgreSQL password       | (required)                                   |
| `DB_URL`               | PostgreSQL connection URL | `jdbc:postgresql://localhost:5432/wordcloud` |
| `RABBIT_MQ_QUEUE`      | RabbitMQ queue name       | `wordcloud`                                  |
| `RABBIT_MQ_EXCHANGE`   | RabbitMQ exchange name    | `772`                                        |
| `RABBIT_MQ_ROUTINGKEY` | RabbitMQ routing key      | `tagcloud`                                   |
| `STORAGE`              | Path to look for files    | `upload-dir`                                 |
