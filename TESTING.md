# TESTING

This project uses a mix of slice tests:

- Validation tests (jakarta validation) for entity constraints.
- Web/controller tests using `@WebMvcTest` + MockMvc.
- Persistence tests using `@DataJpaTest` for repository save/find/delete behavior.

## Run all tests
```bash
./mvnw test
