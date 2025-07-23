# Talon.One Integration API Spring Boot Application

This project is a Spring Boot implementation of the Talon.One Integration API, generated from the provided OpenAPI 3.1.0 specification.

## Features
- Implements REST endpoints for customer sessions, profiles, audiences, loyalty, referrals, events, and more
- OpenAPI 3.1.0 compliant models and request/response validation
- Layered architecture: Controller, Service, Repository
- In-memory H2 database for demo/testing
- Spring Security (API Key authentication)
- Production-ready code with best practices

## Prerequisites
- Java 17+
- Maven 3.8+

## Running the Application

```
git clone https://github.com/Bala-0078/talon.git
cd talon/talon-integration-api
mvn clean package
java -jar target/talon-integration-api-0.0.1-SNAPSHOT.jar
```

The application will start on port 8080 by default.

## API Documentation
Once running, access the OpenAPI/Swagger UI at:
- http://localhost:8080/swagger-ui.html

## Authentication
All endpoints require an API Key in the `Authorization` header:
```
Authorization: ApiKey-v1 <your-api-key>
```

## Project Structure
- `src/main/java/com/talonone/integrationapi/` - Main source code
- `src/main/resources/application.yml` - Application configuration
- `src/main/resources/db/` - Database migration scripts
- `src/main/resources/static/` - Static resources

## License
MIT
