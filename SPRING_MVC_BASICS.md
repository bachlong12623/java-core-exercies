# Spring MVC Basics

## Table of Contents
1. [@RestController](#restcontroller)
2. [@RequestMapping](#requestmapping)
3. [@GetMapping & @PostMapping](#getmapping--postmapping)
4. [@PathVariable](#pathvariable)
5. [@RequestBody](#requestbody)
6. Complete Example](#complete-example)

---

## @RestController

### Overview
`@RestController` is a convenience annotation that combines `@Controller` and `@ResponseBody`. It tells Spring that the class is a controller that handles HTTP requests and returns JSON/XML responses directly.

### @RestController vs @Controller

#### @RestController
```java
@RestController
public class UserController {
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return new User(id, "John");  // Automatically converted to JSON
    }
}
```
- Returns **JSON/XML** directly
- Response is automatically serialized
- All methods return data instead of views

#### @Controller
```java
@Controller
public class UserController {
    @GetMapping("/users/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", new User(id, "John"));
        return "user-detail";  // Returns view name (HTML template)
    }
}
```
- Returns **HTML views/templates**
- Requires view template (Thymeleaf, JSP, etc.)
- Typically used for web applications with server-side rendering

### Key Points
- `@RestController` = `@Controller` + `@ResponseBody`
- Used primarily for **REST APIs**
- Default response type is **JSON**
- Each method returns data, not view names

### Basic Example
```java
@RestController
public class HelloController {
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";  // Returns: "Hello, World!"
    }
    
    @GetMapping("/info")
    public Map<String, String> info() {
        // Returns JSON: {"name": "App", "version": "1.0"}
        return Map.of("name", "App", "version", "1.0");
    }
}
```

---

## @RequestMapping

### Overview
`@RequestMapping` is used to map HTTP requests to specific handler methods or controller classes. It's the general-purpose annotation for request mapping.

### Mapping at Class Level

```java
@RestController
@RequestMapping("/api/users")  // Base path for all methods in this class
public class UserController {
    
    @GetMapping
    public List<User> getAllUsers() {
        // GET /api/users
        return userService.findAll();
    }
    
    @PostMapping
    public User createUser(@RequestBody User user) {
        // POST /api/users
        return userService.save(user);
    }
}
```

### Mapping at Method Level

```java
@RestController
public class UserController {
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        // GET /users
        return userService.findAll();
    }
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        // GET /users/1
        return userService.findById(id);
    }
}
```

### @RequestMapping Parameters

#### 1. **value** - URL path
```java
@RequestMapping(value = "/users")
@RequestMapping("/users")  // Shorthand
```

#### 2. **method** - HTTP method
```java
@RequestMapping(value = "/users", method = RequestMethod.GET)
@RequestMapping(value = "/users", method = RequestMethod.POST)
@RequestMapping(value = "/users", method = {RequestMethod.GET, RequestMethod.POST})
```

#### 3. **produces** - Response content type
```java
@RequestMapping(value = "/users", 
    method = RequestMethod.GET, 
    produces = "application/json")
// Returns JSON only
```

#### 4. **consumes** - Request content type
```java
@RequestMapping(value = "/users", 
    method = RequestMethod.POST,
    consumes = "application/json")
// Accepts JSON only
```

### Complete @RequestMapping Example
```java
@RestController
@RequestMapping(value = "/api/v1/users", produces = "application/json")
public class UserController {
    
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
```

---

## @GetMapping & @PostMapping

### Overview
`@GetMapping` and `@PostMapping` are shorthand annotations for `@RequestMapping` with specific HTTP methods. They make code cleaner and more readable.

### @GetMapping

#### What It Does
- Maps to **GET HTTP requests**
- Shorthand for `@RequestMapping(method = RequestMethod.GET)`
- Used for retrieving data

#### Basic Syntax
```java
@GetMapping("/users")           // GET /users
@GetMapping("/users/{id}")      // GET /users/1
@GetMapping(value = "/users")   // Same as above
```

#### Examples
```java
@RestController
@RequestMapping("/api")
public class UserController {
    
    // GET /api/users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    
    // GET /api/users/1
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    // GET /api/users/search?name=John
    @GetMapping("/users/search")
    public List<User> searchUsers(@RequestParam String name) {
        return userService.searchByName(name);
    }
}
```

### @PostMapping

#### What It Does
- Maps to **POST HTTP requests**
- Shorthand for `@RequestMapping(method = RequestMethod.POST)`
- Used for creating/submitting data

#### Basic Syntax
```java
@PostMapping("/users")          // POST /users
@PostMapping(value = "/users")  // Same as above
```

#### Examples
```java
@RestController
@RequestMapping("/api")
public class UserController {
    
    // POST /api/users
    // Request body: {"name": "John", "email": "john@example.com"}
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
    
    // POST /api/users/1/comments
    @PostMapping("/users/{userId}/comments")
    public Comment addComment(@PathVariable Long userId, @RequestBody Comment comment) {
        return userService.addComment(userId, comment);
    }
}
```

### Other HTTP Method Shortcuts

```java
@PutMapping("/users/{id}")      // PUT - Update entire resource
@PatchMapping("/users/{id}")    // PATCH - Partial update
@DeleteMapping("/users/{id}")   // DELETE - Delete resource
```

### Full Example Comparison

#### Without Shortcuts (Old Way)
```java
@RequestMapping(value = "/users", method = RequestMethod.GET)
public List<User> getAllUsers() { }

@RequestMapping(value = "/users", method = RequestMethod.POST)
public User createUser(@RequestBody User user) { }

@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
public User updateUser(@PathVariable Long id, @RequestBody User user) { }

@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
public void deleteUser(@PathVariable Long id) { }
```

#### With Shortcuts (Modern Way)
```java
@GetMapping("/users")
public List<User> getAllUsers() { }

@PostMapping("/users")
public User createUser(@RequestBody User user) { }

@PutMapping("/users/{id}")
public User updateUser(@PathVariable Long id, @RequestBody User user) { }

@DeleteMapping("/users/{id}")
public void deleteUser(@PathVariable Long id) { }
```

---

## @PathVariable

### Overview
`@PathVariable` is used to extract values from the URL path. It binds URL path parameters to method parameters.

### Basic Syntax
```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) {
    // id extracted from URL: /users/123 → id = 123
    return userService.findById(id);
}
```

### How It Works

| URL | PathVariable Value |
|-----|-------------------|
| `/users/1` | id = 1 |
| `/users/99` | id = 99 |
| `/users/john-123` | id = john-123 |

### Single PathVariable

```java
@RestController
@RequestMapping("/api")
public class UserController {
    
    // GET /api/users/5
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    // GET /api/posts/my-first-post
    @GetMapping("/posts/{slug}")
    public Post getPostBySlug(@PathVariable String slug) {
        return postService.findBySlug(slug);
    }
}
```

### Multiple PathVariables

```java
@RestController
@RequestMapping("/api")
public class CommentController {
    
    // GET /api/posts/5/comments/10
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable Long postId, @PathVariable Long commentId) {
        return commentService.findById(postId, commentId);
    }
    
    // GET /api/users/john/posts/my-post/comments/5
    @GetMapping("/users/{username}/posts/{postSlug}/comments/{commentId}")
    public Comment getComment(
        @PathVariable String username,
        @PathVariable String postSlug,
        @PathVariable Long commentId
    ) {
        return commentService.findComment(username, postSlug, commentId);
    }
}
```

### Explicit Naming

By default, the parameter name must match the path variable name. Use explicit naming to match different names:

```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) {
    // Parameter name matches: {id}
    return userService.findById(id);
}

@GetMapping("/posts/{postId}")
public Post getPost(@PathVariable("postId") Long id) {
    // Explicit mapping: {postId} → parameter id
    return postService.findById(id);
}
```

### Type Conversion

Spring automatically converts path variables to the declared type:

```java
@RestController
@RequestMapping("/api")
public class Controller {
    
    @GetMapping("/numbers/{num}")
    public Map<String, Object> getNumber(@PathVariable Integer num) {
        // num is automatically converted to Integer
        return Map.of("number", num, "square", num * num);
    }
    
    @GetMapping("/dates/{date}")
    public String getDate(@PathVariable LocalDate date) {
        // date is automatically converted to LocalDate
        return "Date: " + date;
    }
}
```

### Error Handling

Invalid type conversion returns **400 Bad Request**:

```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) {
    // GET /users/invalid → 400 Bad Request (can't convert "invalid" to Long)
}
```

---

## @RequestBody

### Overview
`@RequestBody` is used to bind HTTP request body to a Java object. It's typically used with POST, PUT, PATCH requests.

### Basic Syntax
```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    // Extracts JSON from request body and converts to User object
    return userService.save(user);
}
```

### How It Works

#### Request
```json
POST /api/users
Content-Type: application/json

{
    "name": "John Doe",
    "email": "john@example.com",
    "age": 30
}
```

#### Controller Method
```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    // user object:
    // {
    //   "name": "John Doe",
    //   "email": "john@example.com",
    //   "age": 30
    // }
    return userService.save(user);
}
```

#### Response
```json
{
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "age": 30
}
```

### Entity Classes for @RequestBody

```java
@Data  // Lombok annotation for getters, setters, toString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private Integer age;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String name;
    private String email;
    private Integer age;
}
```

### Examples

#### Simple POST
```java
@RestController
@RequestMapping("/api")
public class UserController {
    
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        user.setId(1L);
        return user;
    }
}
```

Request:
```json
POST /api/users
{
    "name": "Alice",
    "email": "alice@example.com",
    "age": 28
}
```

#### POST with PathVariable
```java
@PostMapping("/users/{userId}/comments")
public Comment addComment(
    @PathVariable Long userId,
    @RequestBody Comment comment
) {
    comment.setUserId(userId);
    return commentService.save(comment);
}
```

Request:
```json
POST /api/users/5/comments
{
    "text": "Great post!",
    "rating": 5
}
```

#### PUT with PathVariable and RequestBody
```java
@PutMapping("/users/{id}")
public User updateUser(
    @PathVariable Long id,
    @RequestBody User user
) {
    user.setId(id);
    return userService.update(user);
}
```

Request:
```json
PUT /api/users/1
{
    "name": "John Updated",
    "email": "john.new@example.com",
    "age": 31
}
```

### @RequestBody with Collections

```java
@PostMapping("/users/batch")
public List<User> createMultipleUsers(@RequestBody List<User> users) {
    return userService.saveAll(users);
}
```

Request:
```json
POST /api/users/batch
[
    {"name": "User1", "email": "user1@example.com"},
    {"name": "User2", "email": "user2@example.com"}
]
```

### @RequestBody with Maps

```java
@PostMapping("/data")
public Map<String, Object> processData(@RequestBody Map<String, Object> data) {
    // data can contain any JSON structure
    return data;
}
```

Request:
```json
POST /api/data
{
    "key1": "value1",
    "key2": [1, 2, 3],
    "key3": {"nested": "object"}
}
```

### Validation with @RequestBody

```java
@Data
public class User {
    @NotNull
    @NotBlank
    private String name;
    
    @NotNull
    @Email
    private String email;
    
    @Min(0)
    @Max(150)
    private Integer age;
}

@PostMapping("/users")
public User createUser(@Valid @RequestBody User user) {
    // @Valid triggers validation
    // If validation fails → 400 Bad Request with error details
    return userService.save(user);
}
```

---

## Complete Example

### Project Structure
```
UserManagementApp/
├── User.java                 (Entity)
├── UserController.java       (REST Controller)
├── UserService.java          (Service)
└── UserRepository.java       (Repository)
```

### 1. Entity Class
```java
package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false)
    private String name;
    
    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    
    @Min(0)
    @Max(150)
    private Integer age;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
}
```

### 2. REST Controller
```java
package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    // GET /api/v1/users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
    
    // GET /api/v1/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    
    // GET /api/v1/users/search?name=John
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String name) {
        List<User> users = userService.findByName(name);
        return ResponseEntity.ok(users);
    }
    
    // POST /api/v1/users
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    
    // PUT /api/v1/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
        @PathVariable Long id,
        @Valid @RequestBody User user
    ) {
        User updatedUser = userService.update(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }
    
    // DELETE /api/v1/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
```

### 3. Service Class
```java
package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public List<User> findByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }
    
    public User update(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }
    
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
```

### 4. Repository
```java
package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContainingIgnoreCase(String name);
}
```

### Testing the API with cURL

```bash
# GET all users
curl -X GET http://localhost:8080/api/v1/users

# GET user by ID
curl -X GET http://localhost:8080/api/v1/users/1

# Search users
curl -X GET "http://localhost:8080/api/v1/users/search?name=John"

# CREATE user
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com","age":30}'

# UPDATE user
curl -X PUT http://localhost:8080/api/v1/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Jane Doe","email":"jane@example.com","age":28}'

# DELETE user
curl -X DELETE http://localhost:8080/api/v1/users/1
```

---

## Quick Reference

| Annotation | Purpose | Example |
|-----------|---------|---------|
| `@RestController` | Marks class as REST controller, returns JSON | `@RestController public class UserController` |
| `@RequestMapping` | Maps HTTP requests to methods | `@RequestMapping("/api/users")` |
| `@GetMapping` | Maps GET requests | `@GetMapping("/users/{id}")` |
| `@PostMapping` | Maps POST requests | `@PostMapping("/users")` |
| `@PathVariable` | Extracts URL path variables | `@PathVariable Long id` |
| `@RequestBody` | Binds request body to object | `@RequestBody User user` |
| `@RequestParam` | Extracts query parameters | `@RequestParam String name` |
| `@ResponseStatus` | Sets HTTP response status | `@ResponseStatus(HttpStatus.CREATED)` |

---

## Key Concepts Summary

1. **@RestController**: Combines @Controller + @ResponseBody for JSON REST APIs
2. **@RequestMapping**: General-purpose request mapping (class or method level)
3. **@GetMapping/@PostMapping**: Shorthand for specific HTTP methods
4. **@PathVariable**: Extracts values from URL path (e.g., /users/{id})
5. **@RequestBody**: Binds request body JSON to Java objects

---

## Resources
- [Spring MVC Documentation](https://spring.io/projects/spring-framework)
- [Spring Web MVC Reference](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [REST API Best Practices](https://spring.io/guides/gs/rest-service/)
