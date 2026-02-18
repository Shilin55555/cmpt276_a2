# cmpt276_a2
# Staff Ratings (Spring Boot)

This project includes validation and automated tests.

## Features
- List all ratings: `/ratings`
- View rating detail: `/ratings/{id}`
- Create a new rating: `/ratings/new`
- Edit an existing rating: `/ratings/{id}/edit`
- Delete confirmation + delete action: `/ratings/{id}/delete`
- Server-side validation (Jakarta Validation)
- Automatic overall score calculation (based on rating fields)
- Preloaded sample data for quick demo (if `data.sql` is present)

## Tech Stack
- Java 17
- Spring Boot (Web, Thymeleaf, Validation)
- Spring Data JPA
- H2 database
- `./mvnw`

## Requirements
- Java 17

## How to Run (Local)
From the project root (where `mvnw` is located):

```bash
./mvnw test
./mvnw spring-boot:run
