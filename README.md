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

// ... existing code ...

* **Containerization:** Docker & Docker Compose

## ⚙️ Configuration & Environment Variables

The application uses a combination of `src/main/resources/application.yml` and environment variables. For local
development, you should create a `.env` file in the project root.

### Required Environment Variables

The following variables **must** be defined for the application to function correctly:

| Variable              | Description                                                                                                      |
|:----------------------|:-----------------------------------------------------------------------------------------------------------------|
| `DATASOURCE_PASSWORD` | Password for the PostgreSQL database.                                                                            |
| `STORAGE_LOCATION`    | For local testing! The absolute path where uploaded files will be stored (e.g., `/home/user/wordcloud/uploads`). |

### Optional Environment Variables

These have defaults defined in `application.yml` but can be overridden:

| Variable               | Default                                      | Description                             |
|:-----------------------|:---------------------------------------------|:----------------------------------------|
| `PORT`                 | `8081`                                       | Server port.                            |
| `DATASOURCE_URL`       | `jdbc:postgresql://localhost:5432/wordcloud` | Database connection string.             |
| `DATASOURCE_USERNAME`  | `postgres`                                   | Database username.                      |
| `RABBIT_MQ_HOST`       | `localhost`                                  | Hostname for the RabbitMQ broker.       |
| `RABBIT_MQ_QUEUE`      | `my-queue`                                   | The queue name for processing requests. |
| `RABBIT_MQ_EXCHANGE`   | `my-exchange`                                | The exchange name for RabbitMQ.         |
| `RABBIT_MQ_ROUTINGKEY` | `my-routingkey`                              | The routing key for messages.           |

### Example `.env` File

Create a file named `.env` in the `wordcloud` root directory:

```env
DATASOURCE_PASSWORD=your_secure_password
STORAGE_LOCATION=./upload-dir
# Optional overrides
PORT=8081
RABBIT_MQ_HOST=localhost
```

## 🏗 Architecture Overview

// ... existing code ...