# TESTING

## What I Tested

### 1) Application Context Smoke Test
File:
- `AssignmentApplicationTests.java`

- The Spring application context loads successfully with the current configuration.

### 2) Validation Tests
File:
- `StaffRatingValidationTests.java`

- Required fields cannot be blank/null
- Rating fields must be within allowed ranges (1–10)
- Email format validation
- Invalid entities produce validation violations

### 3) Repository / Persistence Tests
File:
- `StaffRatingRepositoryTests.java`

- Saving an entity persists it correctly.
- Finding by id returns expected data.
- Deleting removes the entity.
  
### 4) Controller Web Layer Tests
File:
- `StaffRatingControllerTests.java`

- Key routes respond correctly (OK / redirects):
  - `/ratings` (list page)
  - `/ratings/{id}` (detail page)
  - form pages such as create/edit/delete
- Correct view is returned and required model attributes exist

## How to Run Tests

From the project root:

Run:
```bash
./mvnw test
