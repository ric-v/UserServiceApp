# UserServiceApp

UserServiceApp is a Spring Boot application that provides user management services, including user registration, login, and token validation. It also includes OAuth2 authorization server configuration.

## Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- MySQL database

## Building and Running the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/ric-v/UserServiceApp.git
   cd UserServiceApp
   ```

2. Update the `src/main/resources/application.properties` file with your MySQL database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Build the project using Maven:
   ```bash
   ./mvnw clean install
   ```

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

## API Endpoints

### User Registration

- **Endpoint:** `/users/signup`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "password123"
  }
  ```
- **Response:**
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "roleList": []
  }
  ```

### User Login

- **Endpoint:** `/users/login`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "email": "john.doe@example.com",
    "password": "password123"
  }
  ```
- **Response:**
  ```json
  {
    "value": "token_value",
    "expiryAt": 1633024800000
  }
  ```

### Token Validation

- **Endpoint:** `/users/validate/{token}`
- **Method:** `GET`
- **Response:**
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "roleList": []
  }
  ```

### Health Check

- **Endpoint:** `/health`
- **Method:** `GET`
- **Response:**
  ```text
  Application is running
  ```

## Authentication and Authorization

The application uses Spring Security and OAuth2 for authentication and authorization. The security configuration is defined in the `SecurityConfig` class.

- **UserDetailsService:** In-memory user details service with a default user.
- **RegisteredClientRepository:** In-memory registered client repository with a default client.
- **JWKSource:** RSA key pair for JWT signing.
- **AuthorizationServerSettings:** Default authorization server settings.

For more details, refer to the `SecurityConfig` class in the `src/main/java/com/example/userservicenov24/configs` package.
