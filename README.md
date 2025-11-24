# Spring Boot and OpenTelemetry Demo

This project demonstrates how to use Spring Boot with OpenTelemetry for distributed tracing.

## Architecture

```
+---------+      +------------------+
|         |----->|                  |
|  User   |      |  hello-service   |
|         |----->|                  |
+---------+      +------------------+
                      |        ^
                      |        |
                      v        |
        +-----------------+    +--------------------+
        |                 |    |                    |
        | greeting-service|    |    user-service    |
        |                 |    |                    |
        +-----------------+    +--------------------+
               |         |            |
               |         |            |
               |         v            v
               |   +--------------------------+
               |   |                          |
               +---> OpenTelemetry Collector  <---+
                   |                          |
                   +--------------------------+
                                |
                                |
                                v
                        +-----------------+
                        |                 |
                        |     Grafana     |
                        |                 |
                        +-----------------+
```

## Getting Started

To get started, you will need to have Docker and Docker Compose installed.

1.  **Build and run the services:**

    ```sh
    docker-compose up --build
    ```

2.  **Access the services:**

    *   **Grafana:** `http://localhost:3000`
    *   **greeting-service:** `http://localhost:8080`
    *   **hello-service:** `http://localhost:8081`
    *   **user-service:** `http://localhost:8082`

## Building from Source

You can also build the services from source using the Gradle wrapper.

*   **Build all services:**

    ```sh
    ./gradlew build
    ```

*   **Build a specific service:**

    ```sh
    ./gradlew :<service-name>:build
    ```

    For example, to build the `greeting-service`:

    ```sh
    ./gradlew :greeting-service:build
    ```

## Troubleshooting

*   **View container logs:**

    ```sh
    docker-compose logs -f <service-name>
    ```

    For example, to view the logs for the `greeting-service`:

    ```sh
    docker-compose logs -f greeting-service
    ```

*   **Check container status:**

    ```sh
    docker-compose ps
    ```

*   **Access a running container:**

    ```sh
    docker-compose exec <service-name> /bin/sh
    ```

## Services

*   **greeting-service:** A simple Spring Boot service that returns a greeting.
*   **hello-service:** A simple Spring Boot service that returns a "hello" message.
*   **user-service:** A simple Spring Boot service that returns a user's name.
*   **grafana-lgtm:** A Grafana instance with the LGTM stack pre-configured for OpenTelemetry.
*   **otel-collector:** An OpenTelemetry collector that receives traces from the services and sends them to Grafana.

## Technologies

*   Spring Boot
*   OpenTelemetry
*   Docker
*   Docker Compose
*   Gradle
*   Java 23
