# Types of Testing in a Spring Boot application
[1]: https://www.codejava.net/frameworks/spring-boot/unit-testing-rest-apis-tutorial ""
[2]: https://www.baeldung.com/spring-boot-testing ""
[3]: https://springframework.guru/testing-spring-boot-restful-services/ ""

Certainly! When it comes to testing REST APIs in a Spring Boot application, there are several types of tests you can implement. Let's explore them:

1. **Unit Testing**:
    - **What is it?** Unit testing involves testing individual components (such as controllers, services, and repositories) in isolation.
    - **How to do it?** You can use tools like JUnit and Mockito to write unit tests for your REST controllers. For example, you can test whether your API endpoints handle requests and produce expected responses¹[1].
    - **Why is it important?** Unit tests catch issues early in the development process and ensure that each component works correctly.

2. **Integration Testing**:
    - **What is it?** Integration testing verifies interactions between different components of your application.
    - **How to do it?** Use Spring Boot's testing support to create integration tests. These tests can bootstrap the Spring context and test the entire application stack, including REST controllers, services, and database interactions²[3].
    - **Why is it important?** Integration tests ensure that different parts of your application work together seamlessly.

3. **End-to-End (E2E) Testing**:
    - **What is it?** E2E testing simulates real user scenarios by testing the entire application flow.
    - **How to do it?** Tools like Selenium or RestAssured can be used to write E2E tests. These tests cover the entire application stack, including the REST endpoints, UI (if applicable), and database interactions.
    - **Why is it important?** E2E tests catch issues related to the entire system, including user interactions.

4. **Security Testing**:
    - **What is it?** Security testing ensures that your REST APIs are secure against common vulnerabilities.
    - **How to do it?** Use tools like OWASP ZAP or SonarQube to scan your APIs for security vulnerabilities. Test for things like SQL injection, cross-site scripting (XSS), and authentication/authorization issues.
    - **Why is it important?** Security vulnerabilities can lead to serious consequences, so it's crucial to identify and fix them early.

5. **Performance Testing**:
    - **What is it?** Performance testing evaluates how well your APIs perform under load.
    - **How to do it?** Use tools like JMeter or Gatling to simulate multiple concurrent users and measure response times, throughput, and resource usage.
    - **Why is it important?** Performance bottlenecks can impact user experience and scalability.

Remember that a combination of these testing types provides comprehensive coverage for your REST APIs. Start with unit tests and gradually expand to other types based on your project requirements. Happy testing! 😊🚀¹[1] ²[3]

Source: Conversation with Bing, 29/04/2024
1. [Spring Boot Unit Testing REST APIs Tutorial - CodeJava.net.](https://www.codejava.net/frameworks/spring-boot/unit-testing-rest-apis-tutorial)
2. [Testing Spring Boot RESTful Services - Spring Framework Guru.](https://springframework.guru/testing-spring-boot-restful-services/)
3. [Testing in Spring Boot | Baeldung.](https://www.baeldung.com/spring-boot-testing)


### Project Idea: Book Management REST API

**Description:**
Create a RESTful API for managing a collection of books. Users can perform CRUD operations (Create, Read, Update, Delete) on books stored in a database.

**Features:**

1. **Create Book:** Allow users to add a new book to the collection by providing details such as title, author, ISBN, etc.

2. **Read Book:** Provide endpoints to retrieve information about a specific book by its ID, or list all books in the collection.

3. **Update Book:** Allow users to update information about a book, such as its title, author, or ISBN.

4. **Delete Book:** Enable users to delete a book from the collection by its ID.

**Additional Features (Optional):**

1. **Search:** Implement a search functionality to allow users to search for books by title, author, or any other criteria.

2. **Pagination:** Implement pagination for listing books to improve performance when dealing with large collections.

3. **Authentication and Authorization:** Add user authentication and authorization to restrict access to certain endpoints.

4. **Validation:** Implement input validation to ensure that only valid data is accepted when creating or updating books.

5. **Error Handling:** Provide meaningful error messages and handle exceptions gracefully to improve the user experience.

**Tools and Technologies:**

- **Spring Boot:** For building the REST API.
- **Spring Data JPA:** For interacting with the database.
- **H2 Database (or any other relational database):** For storing book data.
- **Swagger UI:** For API documentation and testing.
- **JUnit and Mockito:** For writing unit and integration tests.

**Implementation Steps:**

1. Set up a new Spring Boot project.
2. Define the Book entity class with properties such as id, title, author, ISBN, etc.
3. Create a repository interface extending JpaRepository to handle CRUD operations for books.
4. Implement REST controller endpoints for handling CRUD operations on books.
5. Configure database connection and initialize data (if needed).
6. Add validation, error handling, and any additional features as required.
7. Test the API endpoints using Swagger UI or Postman.
8. Write unit and integration tests to ensure the functionality works as expected.

This project will give you a hands-on experience with building a simple but functional REST API using Spring Boot and handling basic CRUD operations. Feel free to add more features or customize it according to your requirements and interests!

Yes, absolutely! Instead of storing book data in a database, you can use another REST API as the backend to fetch book information. This approach is commonly known as proxying or integrating with external APIs. Here's how you can modify the project to use another REST API as the backend:

1. **Remove Database Configuration**: Since you won't be using a database, you can remove the database configuration from your Spring Boot application.

2. **Define Book Model Class**: You still need a Book model class to represent the structure of book data received from the external API.

3. **Service for Calling External API**: Create a service class that communicates with the external API to fetch book data. You'll use Spring's RestTemplate or WebClient to make HTTP requests.

4. **REST Controller**: Modify the REST controller to call the service that fetches book data from the external API. This controller will then act as a proxy, forwarding requests to the external API and returning the response to the client.

Here's a high-level overview of how you can structure the modified project:

```
DemoRestApi/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── demorestapi/
│   │   │               ├── DemoRestApiApplication.java
│   │   │               ├── controller/
│   │   │               │   └── BookController.java
│   │   │               ├── model/
│   │   │               │   └── Book.java
│   │   │               └── service/
│   │   │                   └── BookService.java
```

1. **Book Model Class**: `Book.java`

```java
package com.example.demorestapi.model;

public class Book {
    private String title;
    private String author;
    private String isbn;

    // Getters and Setters
}
```

2. **Book Service**: `BookService.java`

```java
package com.example.demorestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {

    @Autowired
    private RestTemplate restTemplate; // or use WebClient

    public Book[] getAllBooks() {
        String apiUrl = "https://api.example.com/books"; // replace with the actual API URL
        return restTemplate.getForObject(apiUrl, Book[].class);
    }
}
```

3. **REST Controller**: `BookController.java`

```java
package com.example.demorestapi.controller;

import com.example.demorestapi.model.Book;
import com.example.demorestapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Book[] getAllBooks() {
        return bookService.getAllBooks();
    }
}
```

In this setup, when a client requests the `/api/books` endpoint, the controller will call the `getAllBooks()` method of the `BookService`, which in turn will make an HTTP GET request to the external API to fetch the list of books. The controller then returns the fetched books to the client.

Make sure to configure RestTemplate or WebClient in your Spring Boot application to enable HTTP communication with the external API. Additionally, handle error scenarios gracefully and consider adding logging and caching mechanisms as needed.


### Connection Pooling in Spring Boot
The `setMaxConnTotal` and `setMaxConnPerRoute` methods are used to configure the connection pool settings for the HttpClient.

- `setMaxConnTotal(int max)` sets the maximum number of connections in total. This is the maximum number of connections that can be open at the same time across all routes.

- `setMaxConnPerRoute(int max)` sets the maximum number of connections per route. This is the maximum number of connections that can be open at the same time for a single route (a route in this context is essentially a target host or URL).

In a scenario where you are only connecting to one URL, the "per route" setting is particularly relevant. For example, if you set `setMaxConnPerRoute(20)`, it means you can have up to 20 simultaneous connections to that single URL.

The `setMaxConnTotal` is still relevant in this case because it sets an upper limit on the total number of connections, regardless of how many URLs you are connecting to. If you are only connecting to one URL, and `setMaxConnTotal` is set to 100, you can still only have 20 connections to that URL because of the `setMaxConnPerRoute` setting, but you could connect to five different URLs with 20 connections each.

In a multiple request situation to a single URL, these parameters help in controlling the number of simultaneous connections that can be made to the server hosting the URL. This can be useful in managing resources and preventing overloading the server with too many simultaneous connections.


`TestRestTemplate` is a convenient utility for integration testing Spring Boot applications, but it's not the only way to perform HTTP requests in tests. Here are a few alternatives:

1. `MockMvc`: This is a Spring MVC testing framework that allows you to test server-side logic without actually sending a real HTTP request. It simulates the full processing stack, allowing for comprehensive testing.

```java
@Autowired
private MockMvc mockMvc;

@Test
public void testGetAllBooks() throws Exception {
    mockMvc.perform(get("/api/books"))
        .andExpect(status().isOk());
}
```

2. `WebTestClient`: This is a non-blocking, reactive web client for testing web servers. It's part of Spring 5's WebFlux module.

```java
@Autowired
private WebTestClient webTestClient;

@Test
public void testGetAllBooks() {
    webTestClient.get().uri("/api/books")
        .exchange()
        .expectStatus().isOk();
}
```

3. `RestTemplate`: Although `RestTemplate` is deprecated in newer versions of Spring, it's still widely used in many projects. It's a synchronous HTTP client that can be used in tests, but it doesn't integrate as nicely with Spring Boot as `TestRestTemplate`.

```java
private RestTemplate restTemplate = new RestTemplate();

@Test
public void testGetAllBooks() {
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/api/books", String.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
}
```

4. `OkHttpClient`: This is a third-party HTTP client that can be used for integration tests. It's not specific to Spring, so it can be used in any Java project.

```java
private OkHttpClient client = new OkHttpClient();

@Test
public void testGetAllBooks() throws IOException {
    Request request = new Request.Builder()
        .url("http://localhost:" + port + "/api/books")
        .build();

    try (Response response = client.newCall(request).execute()) {
        assertEquals(200, response.code());
    }
}
```

Each of these options has its own strengths and weaknesses, and the best choice depends on your specific needs and the nature of your project.


Certainly! Let's explore the value added by `@WebMvcTest` compared to testing the controller as a regular unit test using JUnit.

1. **Focused Testing**:
   - The primary value of `@WebMvcTest` lies in its focused testing approach.
   - When you use `@WebMvcTest`, Spring Boot only loads the necessary components related to the web layer (e.g., controllers, view resolvers, and other MVC-related beans).
   - This avoids loading the entire application context, making your tests faster and more efficient.

2. **Collaborators and Mocking**:
   - In a regular unit test, you would need to manually create mock collaborators (e.g., services, repositories) required by your controller.
   - With `@WebMvcTest`, you can use `@MockBean` to automatically create mock collaborators.
   - This simplifies the process of mocking dependencies and allows you to focus on testing the controller logic.

3. **Validation Testing**:
   - `@WebMvcTest` provides built-in support for validating request mappings, request parameters, request bodies, and response status.
   - You can easily test whether your controller correctly handles different scenarios (e.g., invalid input values, different HTTP methods).

4. **Integration with MockMvc**:
   - `@WebMvcTest` integrates seamlessly with `MockMvc`, which allows you to perform HTTP requests and validate responses.
   - You can use `MockMvc` to simulate requests and verify the behavior of your controller endpoints.

5. **Efficient Testing of Web Layer**:
   - By using `@WebMvcTest`, you can focus specifically on testing the web layer without loading unnecessary components.
   - It's especially useful when you want to verify the behavior of your controllers, request mappings, and view resolution.

In summary, `@WebMvcTest` provides a lightweight and efficient way to test Spring MVC controllers, allowing you to concentrate on the web layer while leveraging Spring's testing infrastructure. If you need to test the entire application context, consider using `@SpringBootTest` with `@AutoConfigureMockMvc` instead[3][3]. 🚀🔍


[1]: https://stackoverflow.com/questions/66476558/is-using-cqrs-with-not-separated-services-and-repositories-really-affecting-the ""
[2]: https://code-maze.com/vertical-slice-architecture-aspnet-core/ ""
[3]: https://developer.okta.com/blog/2021/07/12/spring-boot-test-slices ""
[4]: https://github.com/UCY-LINC-LAB/5G-Slicer ""
[5]: http://garywoodfine.com/implementing-vertical-slice-architecture/ ""
[6]: https://stackoverflow.com/questions/74841432/clean-architecture-with-repositories-and-services ""
[7]: https://blog.treblle.com/minimal-api-with-vertical-slice-architecture/ ""

Certainly! While `@WebMvcTest` is specifically designed for testing the web layer (controllers) in Spring Boot applications, there isn't a direct equivalent for services and repositories. However, you can achieve similar focused testing for services and repositories using other techniques:

1. **Service Testing**:
   - For testing services (business logic), you can use `@Service` or `@Component` along with `@MockBean`.
   - Create a test class annotated with `@SpringBootTest` and `@AutoConfigureMockMvc` (similar to how you would test controllers).
   - Use `@MockBean` to mock any dependencies (such as repositories) that your service relies on.
   - Write test methods to verify the behavior of your service methods.

2. **Repository Testing**:
   - For testing repositories (data access layer), you can use `@DataJpaTest`.
   - `@DataJpaTest` loads only the necessary components for JPA repository testing.
   - It sets up an in-memory database and provides an `EntityManager` for testing JPA queries.
   - You can use `@MockBean` to mock any external dependencies (e.g., external services) that your repository interacts with.

3. **Custom Slice Testing**:
   - If you want to create custom slices for services or repositories, you can define your own test configuration classes.
   - Annotate your custom configuration class with `@TestConfiguration`.
   - Define beans (services, repositories, etc.) specific to your slice.
   - Use `@Import` to include your custom configuration in your test class.

4. **Vertical Slice Architecture**:
   - Consider adopting a vertical slice architecture (similar to what's done in ASP.NET Core).
   - In this approach, you organize your code around features (vertical slices) rather than layers.
   - Each feature includes its own controller, service, and repository (if needed).
   - This promotes better separation of concerns and easier maintenance¹[2][1].

Remember that the goal is to achieve focused testing while maintaining good separation of concerns. Choose the approach that best fits your application's needs and complexity. Whether it's using existing annotations or creating custom slices, the key is to keep your tests efficient and meaningful. 🚀🔍

Source: Conversation with Bing, 01/05/2024
(1) Vertical Slice Architecture in ASP.NET Core - Code Maze. https://code-maze.com/vertical-slice-architecture-aspnet-core/.
(2) Is using CQRS with not separated services and repositories really .... https://stackoverflow.com/questions/66476558/is-using-cqrs-with-not-separated-services-and-repositories-really-affecting-the.
(3) Faster Spring Boot Testing with Test Slices | Okta Developer. https://developer.okta.com/blog/2021/07/12/spring-boot-test-slices.
(4) UCY-LINC-LAB/5G-Slicer - GitHub. https://github.com/UCY-LINC-LAB/5G-Slicer.
(5) How to Implement Vertical Slice Architecture | Gary Woodfine. http://garywoodfine.com/implementing-vertical-slice-architecture/.
(6) Clean Architecture with Repositories and Services. https://stackoverflow.com/questions/74841432/clean-architecture-with-repositories-and-services.
(7) Minimal API with Vertical slice architecture - Treblle. https://blog.treblle.com/minimal-api-with-vertical-slice-architecture/.



[1]: https://docs.spring.io/spring-boot/how-to/testing.html ""
[2]: https://bing.com/search?q=Slice+Testing+in+spring+boot ""
[3]: https://dzone.com/articles/spring-boot-web-slice-test ""
[4]: https://softice.dev/posts/creating_a_custom_spring_boot_test_slice/ ""

Certainly! In Spring Boot, **slice testing** is a powerful concept that allows you to focus your tests on specific layers or components of your application. Instead of loading the entire application context, slice tests create a smaller, more targeted context that includes only the relevant parts. Let's dive into the details:

1. **What is Slice Testing?**
   - Slice testing involves creating a test context that includes a subset of your application components.
   - Each slice corresponds to a specific layer or functionality (e.g., web layer, data access layer).
   - It helps you test individual parts of your application in isolation while keeping the test execution efficient.

2. **Common Slice Test Annotations**:
   - Spring Boot provides several slice test annotations:
      - `@WebMvcTest`: Focuses on testing Spring MVC controllers and related components (e.g., filters, view resolvers).
      - `@DataJpaTest`: Specifically for testing JPA repositories and related database components.
      - `@RestClientTest`: Used for testing REST clients (e.g., when your application consumes external APIs).
      - Other custom slices can be created based on your application's architecture.

3. **How Slice Testing Works**:
   - When you annotate a test class with `@WebMvcTest`, Spring Boot creates a context that includes only the web-related components (controllers, filters, etc.).
   - Component scanning is restricted to the specified slice, excluding other beans not relevant to that slice.
   - You can inject mock dependencies (using `@MockBean`) to isolate the tested component.

4. **Example: @WebMvcTest for Controllers**:
   - Suppose you want to test a controller. Annotate your test class with `@WebMvcTest(MyController.class)`.
   - Spring Boot will load only the relevant web components (including the specified controller) into the test context.
   - You can use `MockMvc` to simulate HTTP requests and verify controller behavior.

5. **Custom Slice Testing**:
   - You can create your own custom slices by defining test configuration classes.
   - Use `@TestConfiguration` to create beans specific to your slice.
   - Import these configuration classes using `@Import` in your test classes.

6. **Benefits of Slice Testing**:
   - Faster test execution: Only relevant components are loaded.
   - Isolation: Test specific layers without unnecessary dependencies.
   - Improved readability: Tests focus on a single concern.

Remember that slice testing complements other testing approaches (such as unit tests and integration tests). Choose the appropriate slice based on what you want to test, and keep your test suite efficient and meaningful¹[1][1]²[3][3]. 🚀🔍
[1]: https://docs.spring.io/spring-boot/how-to/testing.html ""
[3]: https://dzone.com/articles/spring-boot-web-slice-test ""
[4]: https://softice.dev/posts/creating_a_custom_spring_boot_test_slice/ ""

Source: Conversation with Bing, 01/05/2024
(1) Testing :: Spring Boot. https://docs.spring.io/spring-boot/how-to/testing.html.
(2) Spring Boot Web Test Slicing - DZone. https://dzone.com/articles/spring-boot-web-slice-test.
(3) Testing :: Spring Boot. https://bing.com/search?q=Slice+Testing+in+spring+boot.
(4) Creating a custom Spring Boot test slice - Andrei Roșca. https://softice.dev/posts/creating_a_custom_spring_boot_test_slice/.


[1]: https://martinfowler.com/articles/practical-test-pyramid.html ""
[2]: https://www.testlearning.net/en/posts/pyramid-testing ""
[3]: https://relevantcodes.com/software-testing/software-test-pyramid/ ""
[4]: https://medium.com/@elif_parlak/optimizing-software-quality-a-comprehensive-test-pyramid-approach-7aabe26d6085 ""

Certainly! Implementing the test pyramid with unit testing, slice testing, and integration testing is a great approach to cover various quality requirements for your Spring Boot REST API application. Let's break down how each type of testing contributes:

1. **Unit Testing**:
   - Unit tests focus on individual components (e.g., methods, classes) in isolation.
   - Benefits:
      - Validates the correctness of small code units.
      - Helps catch bugs early during development.
      - Provides fast feedback.
   - What to test:
      - Business logic within your services, controllers, and utility classes.
      - Edge cases, error handling, and boundary conditions.
   - Example: Testing a service method that calculates the total price of items in a shopping cart.

2. **Slice Testing**:
   - Slice testing (such as `@WebMvcTest` and `@DataJpaTest`) targets specific layers or functionality.
   - Benefits:
      - Balances confidence and speed of test execution.
      - Isolates relevant components.
   - What to test:
      - `@WebMvcTest`:
         - Controllers, request mappings, and validation logic.
         - Mock external dependencies (e.g., services).
      - `@DataJpaTest`:
         - JPA repositories and database interactions.
   - Example: Testing a controller's REST endpoints or a repository's data access methods.

3. **Integration Testing**:
   - Integration tests verify interactions between different parts of your application.
   - Benefits:
      - Validates the integration of components (e.g., services, databases, external APIs).
      - Detects issues related to data persistence, transactions, and communication.
   - What to test:
      - Database integration (using an in-memory database or test database).
      - Interaction with external services (using mocks or stubs).
      - End-to-end scenarios involving multiple components.
   - Example: Testing the flow from a controller to a service and database.

4. **Other Considerations**:
   - **Security Testing**:
      - Implement security tests (e.g., using tools like OWASP ZAP or SonarQube) to identify vulnerabilities.
      - Test authentication, authorization, and input validation.
   - **Performance Testing**:
      - While not covered by the test pyramid, performance testing (e.g., load testing, stress testing) is essential.
      - Use tools like JMeter or Gatling to assess system performance under various conditions.

In summary, your combination of unit testing, slice testing, and integration testing provides a solid foundation for quality assurance. However, remember to address security and performance testing separately to ensure comprehensive coverage¹[1][1]²[2][2]³[3][3]. 🚀🔍
[1]: https://martinfowler.com/articles/practical-test-pyramid.html ""
[2]: https://www.testlearning.net/en/posts/pyramid-testing ""
[3]: https://relevantcodes.com/software-testing/software-test-pyramid/ ""

Source: Conversation with Bing, 01/05/2024
(1) The Practical Test Pyramid - Martin Fowler. https://martinfowler.com/articles/practical-test-pyramid.html.
(2) Piramid testing: what is it and how does it work? | Testlearning. https://www.testlearning.net/en/posts/pyramid-testing.
(3) Software test pyramid - relevantcodes.com. https://relevantcodes.com/software-testing/software-test-pyramid/.
(4) Optimizing Software Quality: A Comprehensive Test Pyramid Approach. https://medium.com/@elif_parlak/optimizing-software-quality-a-comprehensive-test-pyramid-approach-7aabe26d6085.



You're correct. The `@WebMvcTest` annotation is used for testing the web layer, specifically the controllers. However, it does not load the entire application context. Instead, it only sets up the components necessary for testing the web layer, such as `MockMvc`.

In your `BookControllerMockMvcTest` class, you're using `@MockBean` to create a mock instance of `BookService`. This means that the service layer is not being tested here, only the web layer is. When you call `bookService.getAllBooks()`, it doesn't execute the real method in the service layer, but returns the mock value you've specified in your test (`new ArrayList<>()`).

So, even though it seems like this test is going through all layers, it's actually only testing the web layer. The `@MockBean` annotation ensures that the service layer is bypassed, allowing you to focus on testing the behavior of the controller.

If you want to test the interaction between the web layer and the service layer, you should use `@SpringBootTest` along with `MockMvc`, and not mock the service. This will load the entire application context and allow you to test the actual interaction between the layers.

In a Spring Boot application, you can control the HTTP status code returned by your REST endpoints by using the `ResponseEntity` class. `ResponseEntity` represents the entire HTTP response: status code, headers, and body. You can use it to fully configure the HTTP response.

Here's how you can do it in your `BookControllerImpl`:

```java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Override
    public ResponseEntity<List<Book>> getAllBooks() {
        log.debug("Getting all books");
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books); // Returns HTTP 200
    }

    @Override
    public ResponseEntity<Book> getBook(Long id) {
        log.debug("Getting book with id: {}", id);
        Book book = bookService.getBook(id);
        return ResponseEntity.ok(book); // Returns HTTP 200
    }

    @Override
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        log.debug("Creating book: {}", book);
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.status(201).body(createdBook); // Returns HTTP 201
    }

    @Override
    public ResponseEntity<Book> updateBook(Long id, @RequestBody Book book) {
        log.debug("Updating book with id: {} with data: {}", id, book);
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook); // Returns HTTP 200
    }

    @Override
    public ResponseEntity<Void> deleteBook(Long id) {
        log.debug("Deleting book with id: {}", id);
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build(); // Returns HTTP 204
    }
}
```

In the above code:

- `ResponseEntity.ok(body)` is used to create a `ResponseEntity` with the given body and HTTP status code 200.
- `ResponseEntity.status(201).body(body)` is used to create a `ResponseEntity` with the given body and HTTP status code 201.
- `ResponseEntity.noContent().build()` is used to create a `ResponseEntity` with no body and HTTP status code 204.
