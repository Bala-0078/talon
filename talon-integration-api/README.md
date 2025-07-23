# Talon.One Integration API Spring Boot Application

This project is a Spring Boot implementation of the Talon.One Integration API, generated from the provided OpenAPI specification.

## Features
- REST endpoints for customer sessions, profiles, audiences, loyalty, referrals, coupons, events, catalogs, and more.
- JPA entities and repositories for persistence.
- Service layer for business logic.
- Security configuration for API key authentication.
- H2 in-memory database for development/testing.

## Prerequisites
- Java 17+
- Maven 3.6+

## Running the Application

```
mvn clean package
java -jar target/talon-integration-api-0.0.1-SNAPSHOT.jar
```

Application will start on `http://localhost:8080`.

## API Documentation

Swagger UI is available at `http://localhost:8080/swagger-ui.html`.

## Configuration

Database and security settings can be found in `src/main/resources/application.properties`.

## Example Endpoints
- `PUT /v2/customer_sessions/{customerSessionId}`: Update or create a customer session.
- `GET /v2/customer_sessions/{customerSessionId}`: Retrieve a customer session.
- `PUT /v2/customer_profiles/{integrationId}`: Update or create a customer profile.
- `POST /v2/audiences`: Create an audience.
- ...

## Metadata
See `talon-integration-api/metadata.json` for a summary of the generated application.

---

This project was generated automatically from the Talon.One Integration API OpenAPI YAML specification.
