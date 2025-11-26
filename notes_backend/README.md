# Notes Backend (Spring Boot)

Simple Spring Boot backend exposing CRUD APIs for notes with in-memory H2 persistence for runtime data.

## How to run

- Java 17 required.
- From this folder:

```
./gradlew bootRun
```

App starts by default on port 8080.

H2 console: /h2-console (JDBC URL: jdbc:h2:mem:testdb, user: sa, password: empty)

OpenAPI/Swagger UI: /swagger-ui.html  
OpenAPI JSON: /api-docs

## Endpoints

Base: http://localhost:8080

- GET /notes  
  List all notes.

- GET /notes/{id}  
  Get a note by id.

- POST /notes  
  Creates a new note.  
  Request body (application/json):
  {
    "title": "My Note",
    "content": "Some content"
  }

- PUT /notes/{id}  
  Updates an existing note.  
  Request body (application/json):
  {
    "title": "Updated title",
    "content": "Updated content"
  }

- DELETE /notes/{id}  
  Deletes a note.

### Example curl

Create:
curl -s -X POST http://localhost:8080/notes -H "Content-Type: application/json" -d '{"title":"Hello","content":"World"}'

List:
curl -s http://localhost:8080/notes

Get:
curl -s http://localhost:8080/notes/1

Update:
curl -s -X PUT http://localhost:8080/notes/1 -H "Content-Type: application/json" -d '{"title":"New","content":"Text"}'

Delete:
curl -i -X DELETE http://localhost:8080/notes/1
