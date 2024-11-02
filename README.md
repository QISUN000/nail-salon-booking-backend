# Nail Salon Booking System - Backend

## Overview
Backend service for the Nail Salon Booking System, built with Spring Boot 3.3.4. This service provides REST APIs for appointment management, user authentication, and salon service management.

##Live Demo
🚀 Visit the Live Site 
https://qisun.lol/

## Tech Stack
- **Java Version:** 21
- **Framework:** Spring Boot 3.3.4
- **Database:** MySQL (Azure Database for MySQL)
- **Build Tool:** Maven
- **Documentation:** OpenAPI (Swagger)

### Key Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Security
- Spring Boot Validation
- MySQL Connector
- JWT (JSON Web Token)
- Lombok
- SpringDoc OpenAPI (Swagger UI)

## Project Structure
```
com.example.nail_salon_booking_backend/
├── config/
│   ├── OpenAPIConfig         # Swagger configuration
│   └── WebConfig             # Web and CORS configuration
├── controller/               # REST API controllers
├── exception/               # Custom exception handlers
├── model/                   # Entity classes
├── payload/
│   ├── ApiResponse          # Standard API response format
│   ├── BookingRequest       # Booking related DTOs
│   ├── EmailCheckRequest    # Email validation DTOs
│   ├── GoogleLoginRequest   # Google OAuth DTOs
│   ├── JwtAuthentication    # JWT related DTOs
│   ├── LoginRequest         # Login DTOs
│   ├── RegisterRequest      # Registration DTOs
│   └── UserResponse         # User related DTOs
├── repository/              # JPA repositories
├── security/
│   ├── CustomUserDetailsService
│   ├── JwtAuthenticationEntryPoint
│   ├── JwtAuthenticationFilter
│   ├── JwtTokenProvider
│   ├── SecurityConfig
│   └── UserPrincipal
├── service/
│   ├── BookingService       # Appointment management
│   ├── CategoryService      # Service category management
│   ├── NailServiceService   # Nail service management
│   ├── NotificationService  # Notification handling
│   ├── ProfessionalService  # Staff management
│   └── UserService          # User management
└── NailSalonBookingBackendApplication.java
```

## Setup and Installation

### Prerequisites
- JDK 21
- Maven
- MySQL Server
- IDE (IntelliJ IDEA recommended)

### Database Configuration
Configure MySQL connection in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://[YOUR_DATABASE_HOST]:3306/nail_salon_db
spring.datasource.username=[USERNAME]
spring.datasource.password=[PASSWORD]
```

### Building the Project
```bash
mvn clean install
```

### Running the Application
```bash
mvn spring-boot:run
```

## API Documentation
- Swagger UI: `/swagger-ui.html`
- OpenAPI Docs: `/v3/api-docs`

## Security

### JWT Configuration
- Secret key configuration in application.properties
- Token expiration: 24 hours
- Token validation and refresh mechanisms

### Authentication Endpoints
- `/api/auth/register` - User registration
- `/api/auth/login` - Email/password login
- `/api/auth/google` - Google OAuth login
- `/api/auth/check-email` - Email availability check

### Security Features
- JWT based authentication
- Role-based access control (USER, ADMIN, PROFESSIONAL)
- Password encryption
- CORS configuration
- Request validation

## Environment Configuration
```properties
# Server Configuration
server.port=8080

# JWT Configuration
app.jwtSecret=[YOUR_JWT_SECRET]
app.jwtExpirationInMs=86400000

# CORS Configuration
cors.allowed-origins=[YOUR_FRONTEND_URL]

# Logging Configuration
logging.level.root=INFO
logging.level.org.springframework.web=DEBUG
```

## Development Guidelines

### API Response Format
```json
{
  "success": boolean,
  "message": "string",
  "data": object
}
```

### Error Handling
- Global exception handling
- Standardized error responses
- Validation error handling

### Database Schema Management
- JPA auto-update schema
- Database version control (recommended)
- Data seeding scripts (if needed)

## Deployment
Currently deployed on Azure with:
- Azure Database for MySQL
- Azure App Service (or your deployment platform)
- SSL/TLS enabled
- Environment-specific configurations

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit changes
4. Push to the branch
5. Create a Pull Request

