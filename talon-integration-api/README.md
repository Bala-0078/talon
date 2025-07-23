# Talon.One Integration API Spring Boot Application

This project is a Spring Boot application that wraps the Talon.One Integration API, providing REST endpoints, authentication, and service layers for easy integration.

## Features
- REST endpoints for customer sessions, profiles, loyalty, coupons, referrals, and more.
- API Key authentication via HTTP header.
- Service and repository layers for business logic and data access.
- Modular, production-ready code structure.

## Prerequisites
- Java 17+
- Maven 3.6+

## Setup & Run
1. Clone the repository:
   ```
   git clone https://github.com/Bala-0078/talon.git
   cd talon
   ```
2. Build the application:
   ```
   mvn clean package
   ```
3. Run the application:
   ```
   java -jar target/talon-integration-api-0.0.1-SNAPSHOT.jar
   ```

## Configuration
Edit `src/main/resources/application.properties` to set the Talon.One base URL and your API key:
```
talon.api.base-url=https://yourbaseurl.talon.one
talon.api.key=ApiKey-v1 YOUR_API_KEY
```

## Endpoints
The application exposes endpoints matching the Talon.One Integration API, such as:
- PUT /api/customer-sessions/{customerSessionId}
- GET /api/customer-sessions/{customerSessionId}
- POST /api/customer-sessions/{customerSessionId}/returns
- PUT /api/customer-sessions/{customerSessionId}/reopen
- PUT /api/customer-profiles/{integrationId}
- ...and more

See the OpenAPI spec for full details.

## Security
All endpoints require an API key in the `Authorization` header.

## Packaging
The application is packaged as a runnable JAR. Build with `mvn package`.

## License
MIT
