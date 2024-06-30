# Authors & Books API

This is a RESTful API built with Java and Spring Boot for managing authors and books. It provides endpoints to perform CRUD operations on both authors and books.

## Technologies Used

- Java
- Spring Boot
- Maven
- PostgreSQL

## API Endpoints

### Authors

- **POST /authors:** Create a new author
- **GET /authors:** Retrieve all authors
- **GET /authors/{id}:** Retrieve an author by ID
- **PUT /authors/{id}:** Update an author
- **PATCH /authors/{id}:** Partially update an author
- **DELETE /authors/{id}:** Delete an author

### Books

- **PUT /books/{isbn}:** Create or update a book
- **GET /books:** Retrieve all books (with pagination)
- **GET /books/{isbn}:** Retrieve a book by ISBN
- **PATCH /books/{isbn}:** Partially update a book
- **DELETE /books/{isbn}:** Delete a book

## Swagger Documentation

The API is documented using Swagger UI, which provides interactive documentation for all endpoints. To explore and interact with the API documentation, run the application and navigate to:
http://localhost:8080/swagger-ui.html

## Setup and Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/authors-books-api.git
   cd authors-books-api
2. **Set up PostgreSQL locally**:

- Install PostgreSQL locally and ensure it's running on port 5432.
- Create a database named authors_books_db.
- Update src/main/resources/application.properties with your PostgreSQL username, password, and database name.

3. **Run the application.**
4. **Access the API:**
   Open your browser or API client (e.g., Postman) and access endpoints at http://localhost:8080.
