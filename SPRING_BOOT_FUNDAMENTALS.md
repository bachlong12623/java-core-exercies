# Spring Boot Fundamentals

## Table of Contents
1. [@SpringBootApplication](#springbootapplication)
2. [Auto-configuration](#auto-configuration)
3. [Starter Dependencies](#starter-dependencies)
4. [Project Structure](#project-structure)

---

## @SpringBootApplication

### Overview
`@SpringBootApplication` is the main annotation used to bootstrap a Spring Boot application. It's a convenience annotation that combines three annotations into one.

### What It Does
```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### Equivalent To
`@SpringBootApplication` is equivalent to:
```java
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
    // ...
}
```

### Breaking It Down

#### 1. **@Configuration**
- Marks the class as a source of bean definitions
- Allows you to define beans using `@Bean` methods
- Makes the class a Spring configuration class

#### 2. **@EnableAutoConfiguration** (or @AutoConfiguration in newer versions)
- Tells Spring Boot to automatically configure the application based on jar dependencies
- Enables intelligent defaults for Spring frameworks
- Can be overridden if you need custom configuration

#### 3. **@ComponentScan**
- Scans for Spring components (beans) in the current package and subpackages
- Automatically discovers classes annotated with `@Component`, `@Service`, `@Repository`, `@Controller`, etc.
- Default scanning starts from the package where the main class is located

### Key Point
You should only have **one** `@SpringBootApplication` annotation in your project, typically in your main application class.

---

## Auto-configuration

### What Is Auto-configuration?

Auto-configuration is a feature that automatically configures your Spring application based on:
- Jar files present on the classpath
- Classes you have defined on your classpath
- Bean definitions you have set

### How It Works

1. **Detection**: Spring Boot detects what's on the classpath
2. **Matching**: It matches detected components against configuration classes
3. **Registration**: Automatically registers beans and configurations
4. **Override**: You can override defaults with your own configuration

### Example: DataSource Auto-configuration

If you add an H2 database dependency to your classpath:
```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

Spring Boot automatically:
- Creates a DataSource bean
- Configures a connection pool
- Sets up JPA/Hibernate if present

### Enabling/Disabling Auto-configuration

#### Exclude Specific Auto-configurations
```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

#### Disable All Auto-configuration
```java
@SpringBootApplication(autoConfiguration = {})
public class Application {
    // ...
}
```

### Application Properties for Auto-configuration

Control auto-configuration behavior using `application.properties` or `application.yml`:

```properties
# Enable/disable specific features
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password

# Enable debug logging to see auto-configuration report
debug=true
```

### Debug Mode
Run with `--debug` flag to see a report of auto-configuration:
```bash
java -jar app.jar --debug
```

---

## Starter Dependencies

### What Are Starter Dependencies?

Starter dependencies (or "starters") are a set of convenient dependency descriptors that simplify Maven or Gradle configuration. They provide a one-stop-shop for all the Spring and related technology you need.

### Why Use Them?

- **Simplified Configuration**: One dependency instead of many
- **Version Management**: Compatible versions are pre-selected
- **Reduced Boilerplate**: No need to manually manage transitive dependencies
- **Best Practices**: Follow Spring conventions

### Common Spring Boot Starters

#### Web Development
```xml
<!-- Spring MVC and embedded Tomcat -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

#### Database
```xml
<!-- Spring Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- MySQL Driver -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mysql</artifactId>
</dependency>
```

#### Security
```xml
<!-- Spring Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

#### Testing
```xml
<!-- Testing with JUnit, Mockito, etc. -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

#### RESTful Services
```xml
<!-- REST support (included in spring-boot-starter-web) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

#### Validation
```xml
<!-- Bean Validation with Hibernate Validator -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### What Gets Included?

`spring-boot-starter-web` includes:
- Spring MVC
- Spring Core
- Validation
- JSON binding
- Embedded Tomcat

Without starters, you'd need to manually add 10+ dependencies!

---

## Project Structure

### Recommended Directory Layout

```
my-spring-boot-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           ├── Application.java          [Main entry point]
│   │   │           ├── controller/
│   │   │           │   └── UserController.java
│   │   │           ├── service/
│   │   │           │   └── UserService.java
│   │   │           ├── repository/
│   │   │           │   └── UserRepository.java
│   │   │           ├── entity/
│   │   │           │   └── User.java
│   │   │           ├── dto/
│   │   │           │   └── UserDTO.java
│   │   │           └── config/
│   │   │               └── AppConfig.java
│   │   └── resources/
│   │       ├── application.properties           [Main config]
│   │       ├── application-dev.properties       [Dev profile]
│   │       ├── application-prod.properties      [Prod profile]
│   │       └── templates/
│   │           └── (HTML templates if using Thymeleaf)
│   └── test/
│       ├── java/
│       │   └── com/example/
│       │       ├── UserControllerTest.java
│       │       └── UserServiceTest.java
│       └── resources/
│           └── application-test.properties
├── pom.xml                                       [Maven config]
├── build.gradle                                  [Gradle config (if used)]
└── README.md
```

### Folder Structure Explanation

#### `src/main/java/com/example/`
- **Application.java**: Main entry point with `@SpringBootApplication`
- **controller/**: REST controllers (`@RestController`, `@Controller`)
- **service/**: Business logic layer (`@Service`)
- **repository/**: Data access layer (`@Repository`)
- **entity/**: JPA entities (`@Entity`)
- **dto/**: Data Transfer Objects
- **config/**: Configuration classes (`@Configuration`)

#### `src/main/resources/`
- **application.properties**: Main configuration file
- **application-{profile}.properties**: Environment-specific configs (dev, prod, test)
- **templates/**: HTML templates (for Thymeleaf or similar)
- **static/**: CSS, JavaScript, images
- **db/**: Database migration scripts (Flyway/Liquibase)

#### `src/test/java/`
- Unit and integration tests
- Test configuration

### Key Files

#### 1. **pom.xml** (Maven)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <name>My Spring Boot App</name>
    <description>Sample Spring Boot Application</description>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.1.0</version>
        <relativePath/>
    </parent>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- More dependencies -->
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

#### 2. **application.properties**
```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/api

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Application Name
spring.application.name=My-App
```

#### 3. **Application.java**
```java
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### Layered Architecture Pattern

Spring Boot projects typically follow a layered architecture:

```
┌─────────────────────────────┐
│   Controller Layer          │  (@RestController, @Controller)
│   (HTTP Requests/Responses) │
├─────────────────────────────┤
│   Service Layer             │  (@Service)
│   (Business Logic)          │
├─────────────────────────────┤
│   Repository Layer          │  (@Repository)
│   (Data Access)             │
├─────────────────────────────┤
│   Entity/Database           │  (@Entity)
│   (Data Persistence)        │
└─────────────────────────────┘
```

### Build and Run

#### With Maven
```bash
# Compile
mvn clean compile

# Run
mvn spring-boot:run

# Build JAR
mvn clean package

# Run JAR
java -jar target/my-app-1.0.0.jar
```

#### With Gradle
```bash
# Run
gradle bootRun

# Build JAR
gradle bootJar

# Run JAR
java -jar build/libs/my-app-1.0.0.jar
```

---

## Quick Summary

| Concept | Purpose |
|---------|---------|
| **@SpringBootApplication** | Main annotation combining @Configuration, @EnableAutoConfiguration, @ComponentScan |
| **Auto-configuration** | Automatically configures the app based on classpath dependencies |
| **Starter Dependencies** | Pre-configured dependency bundles (e.g., spring-boot-starter-web) |
| **Project Structure** | Organized folder layout following layered architecture (controller → service → repository → entity) |

---

## Quick Start Example

```java
// 1. Main Application Class
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// 2. Entity
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // getters and setters
}

// 3. Repository
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

// 4. Service
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}

// 5. Controller
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
```

---

## Resources for Learning
- [Spring Boot Official Documentation](https://spring.io/projects/spring-boot)
- [Spring Boot Guides](https://spring.io/guides)
- [Spring Boot API Documentation](https://docs.spring.io/spring-boot/docs/current/api/)
