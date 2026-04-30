# Student Platform Backend - Maven Project

## Import into STS (Spring Tool Suite)

### Step 1: Import as Maven Project
1. Open STS
2. **File** > **Import** > **Maven** > **Existing Maven Projects**
3. Browse to: `StudentPlatform-Backend-Maven` folder
4. Select the folder (STS will find pom.xml)
5. Click **Finish**

### Step 2: Run the Application
1. Wait for Maven to download dependencies (bottom right progress)
2. Right-click on the project
3. **Run As** > **Spring Boot App**
4. Or right-click `StudentPlatformApplication.java` > **Run As** > **Java Application**

### Step 3: Verify Backend
- Console should show: `Started StudentPlatformApplication`
- Access: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console

## Project Structure
```
StudentPlatform-Backend-Maven/
src/main/java/com/studentplatform/backend/
    StudentPlatformApplication.java    # Main Spring Boot Application
    controller/                        # REST Controllers
        AuthController.java            # Authentication endpoints
        UserController.java             # User management
        ConferenceController.java       # Conference management
        ReviewController.java           # Review management
        SubmissionController.java        # Paper submission
    service/                          # Business Logic
        UserService.java               # User operations
        ConferenceService.java          # Conference operations
        ReviewService.java              # Review operations
        SubmissionService.java          # Submission operations
    model/                            # Entity Classes
        User.java                       # User entity
        Conference.java                 # Conference entity
        Review.java                     # Review entity
        Submission.java                 # Paper submission entity
        Profile.java                    # User profile
        AIFeedback.java                 # AI feedback
    repository/                       # JPA Repositories
        UserRepository.java             # User repository
        ConferenceRepository.java       # Conference repository
        ReviewRepository.java           # Review repository
        SubmissionRepository.java       # Submission repository
    security/                         # Security Configuration
        SecurityConfig.java             # Spring Security config
        JwtUtil.java                    # JWT token utilities
        JwtAuthenticationFilter.java    # JWT authentication filter
        UserDetailsServiceImpl.java      # User details service
        SecurityBeansConfig.java        # Security beans
    dto/                              # Data Transfer Objects
        RegisterRequest.java            # Registration request DTO
        LoginRequest.java               # Login request DTO
    DataSeeder.java                   # Data initialization
src/main/resources/
    application.properties             # Application configuration
    static/                           # Static resources
    templates/                        # Template files
```

## Maven Commands (if needed)
```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package as JAR
mvn package

# Run application
mvn spring-boot:run
```

## Configuration (application.properties)
```properties
# Server Configuration
server.port=8080

# Database Configuration (H2 for development)
spring.datasource.url=jdbc:h2:mem:studentplatform;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver

# H2 Console (for development)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false

# JWT Configuration
jwt.secret=mySecretKey12345678901234567890123456789012345678901234567890
jwt.expiration=86400000

# Logging
logging.level.com.studentplatform.backend=DEBUG
logging.level.org.springframework.security=DEBUG
```

## API Endpoints

### Authentication
- POST `/api/auth/register` - Register new user
- POST `/api/auth/login` - User login

### Users
- GET `/api/users` - Get all users
- GET `/api/users/{id}` - Get user by ID
- PUT `/api/users/{id}` - Update user

### Conferences
- GET `/api/conferences` - Get all conferences
- GET `/api/conferences/{id}` - Get conference by ID
- POST `/api/conferences` - Create conference

### Reviews & Submissions
- GET `/api/reviews` - Get all reviews
- GET `/api/submissions` - Get all submissions

## Frontend Integration

### CORS Configuration
Backend allows requests from:
- http://localhost:5173
- http://localhost:5174  
- http://localhost:5175
- http://localhost:5176

### Frontend Setup (VS Code)
```bash
cd ../studentplatform
npm install
npm run dev
```

## Database Access (H2 Console)
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:studentplatform`
- Username: `sa`
- Password: (leave empty)

## Troubleshooting

### Maven Dependencies Issues
1. Right-click project > Maven > Update Project
2. Check "Force Update of Snapshots/Releases"
3. Click OK

### Port Already in Use
Change `server.port=8081` in `application.properties`

### Java Version Issues
Update `pom.xml` properties:
```xml
<properties>
    <java.version>21</java.version>  <!-- or 17 -->
</properties>
```

### Clean Build
```bash
mvn clean install
```

## Key Differences from Gradle Version
- Uses `pom.xml` instead of `build.gradle`
- Maven commands instead of Gradle commands
- Standard Maven directory structure
- Better STS integration (native support)

## Features
- Spring Boot 3.2.0
- Java 17 (configurable)
- Spring Security with JWT
- H2 Database
- JPA/Hibernate
- RESTful APIs
- CORS support
- Role-based authentication
