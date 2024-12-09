package za.co.test_project.unit_integration.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenStudentEmailExists() {
        //Given
        Student student = new Student(
                "Ishmail",
                "ishmail@gmail.com",
                Gender.MALE
        );
        underTest.save(student);
        //When
        boolean expected = underTest.selectExistsEmail("ishmail@gmail.com");
        //Then
        assertThat(expected).isTrue();
    }
    @Test
    void itShouldCheckWhenStudentEmailDoesNotExists() {
        //Given
        String email = "ishmail@gmail.com";
        //When
        boolean expected = underTest.selectExistsEmail(email);
        //Then
        assertThat(expected).isFalse();
    }

}