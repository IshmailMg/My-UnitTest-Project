# Student Management Project - Tests

## Overview

This project contains a Spring Boot application with comprehensive unit and integration tests for managing student data. The tests ensure robust functionality for both the **repository** (database operations) and **service** (business logic) layers.

---

## Project Structure

- **`StudentRepositoryTest`**: Integration tests for repository operations.
- **`StudentServiceTest`**: Unit tests for service logic using mocked dependencies.

---

## Technologies Used

- **Spring Boot**: Backend framework.
- **JUnit 5 (Jupiter)**: Testing framework.
- **Mockito**: Mocking library.
- **AssertJ**: Fluent assertions.
- **JaCoCo**: Test coverage reporting.

---

## Tests Summary

### **Integration Tests (`StudentRepositoryTest`)**

#### **Annotations**
- `@DataJpaTest`: Loads only JPA components for testing.
- `@AfterEach`: Cleans up test data after each run.

#### **Test Cases**
1. **`itShouldCheckWhenStudentEmailExists`**:
    - Verifies that an existing email is correctly identified.
2. **`itShouldCheckWhenStudentEmailDoesNotExists`**:
    - Validates that a non-existent email returns `false`.

---

### **Unit Tests (`StudentServiceTest`)**

#### **Annotations**
- `@ExtendWith(MockitoExtension.class)`: Enables Mockito-based tests.
- `@BeforeEach`: Initializes mocked dependencies before each test.

#### **Test Cases**
1. **`canGetAllStudents`**:
    - Ensures all students are retrieved using `findAll`.
2. **`canAddStudent`**:
    - Verifies that a new student is added to the repository.
3. **`willThrowWhenEmailIsTaken`**:
    - Confirms that a `BadRequestException` is thrown for duplicate emails.
4. **`canDeleteStudent`**:
    - Checks that a student is deleted if the ID exists.
5. **`willThrowWhenStudentIdDoesNotExist`**:
    - Ensures `StudentNotFoundException` is thrown for invalid IDs.

---

## Running Tests

1. Build the project:
   ```bash
   ./mvnw clean install
