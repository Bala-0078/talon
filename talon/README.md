# Talon Integration API Spring Boot Application

This project is a Spring Boot implementation of the Talon.One Integration API, generated from the provided OpenAPI specification. It provides endpoints for managing customer sessions, profiles, audiences, loyalty, coupons, referrals, events, and more.

## Prerequisites
- Java 17+
- Maven 3.8+
- (Optional) Docker for containerized deployment

## Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/Bala-0078/talon.git
   cd talon
   ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the application:
   ```sh
   mvn spring-boot:run
   ```
   or
   ```sh
   java -jar target/talon-integration-api.jar
   ```

## API Documentation
- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Configuration
- Edit `src/main/resources/application.yml` for database and security settings.

## Features
- Implements endpoints for customer sessions, profiles, audiences, loyalty, coupons, referrals, events, and more.
- Layered architecture: Controller, Service, Repository.
- JPA/H2 for persistence (can be configured for other databases).
- Spring Security with API key authentication.
- OpenAPI/Swagger documentation.

## Example Endpoints
- `PUT /v2/customer_sessions/{customerSessionId}`: Update or create a customer session.
- `GET /v2/customer_sessions/{customerSessionId}`: Retrieve a customer session.
- `PUT /v2/customer_profiles/{integrationId}`: Update or create a customer profile.

## Running Tests
```sh
mvn test
```

## Packaging
To build a runnable JAR:
```sh
mvn clean package
```
The JAR will be in `target/talon-integration-api.jar`.

## License
MIT
