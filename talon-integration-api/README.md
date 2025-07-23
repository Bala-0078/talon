# Talon.One Integration API Spring Boot Application

This project provides a Spring Boot application that integrates with the Talon.One Integration API as described in the provided OpenAPI specification. It exposes endpoints to interact with customer sessions, loyalty programs, audiences, and more, and demonstrates a custom function for syncing customer sessions.

## Features
- REST endpoints for customer sessions, profiles, loyalty, coupons, audiences, and more
- Service layer for Talon.One Integration API calls
- Security configuration for API key authentication
- Custom function for syncing customer session data to Talon.One
- Example usage of Talon.One Rule Builder integration

## Prerequisites
- Java 17+
- Maven 3.8+

## Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/Bala-0078/talon.git
   cd talon/talon-integration-api
   ```
2. Configure your Talon.One API key in `src/main/resources/application.yml`:
   ```yaml
   talon:
     api-key: YOUR_TALON_ONE_API_KEY
     base-url: https://yourbaseurl.talon.one
   ```
3. Build the application:
   ```sh
   mvn clean package
   ```
4. Run the application:
   ```sh
   java -jar target/talon-integration-api-0.0.1-SNAPSHOT.jar
   ```

## Endpoints
- `PUT /api/customer-sessions/{customerSessionId}`: Sync or update a customer session
- `GET /api/customer-sessions/{customerSessionId}`: Get a customer session
- `POST /api/customer-sessions/{customerSessionId}/returns`: Return cart items
- `PUT /api/customer-sessions/{customerSessionId}/reopen`: Reopen a session
- `PUT /api/customer-profiles/{integrationId}`: Update or create a customer profile
- `POST /api/loyalty-programs/{loyaltyProgramId}/profile/{integrationId}/points`: Update loyalty points

## Custom Function Example
A custom service method is provided to call `/v2/customer_sessions/{customerSessionId}` for syncing session data. This can be triggered after a purchase, referral, or audience condition via the Talon.One Rule Builder.

## OpenAPI Spec
The full OpenAPI specification is available in `talon.yml`.

## Packaging
The application is packaged as a runnable JAR. Use `mvn clean package` to build.

## License
MIT
