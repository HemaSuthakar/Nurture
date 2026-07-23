Exercise 9 - Spring Boot Application

Requirements:
- Java 17
- Maven

Run:
1. mvn spring-boot:run
OR
2. Run LibraryManagementApplication.java

REST APIs:

GET    /books
GET    /books/{id}
POST   /books
PUT    /books/{id}
DELETE /books/{id}

Sample POST JSON:
{
  "title":"Spring Boot",
  "author":"John"
}

H2 Console:
http://localhost:8080/h2-console

JDBC URL:
jdbc:h2:mem:librarydb
