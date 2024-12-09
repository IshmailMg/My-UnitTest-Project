package za.co.test_project.unit_integration.student;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.test_project.unit_integration.student.exception.BadRequestException;
import za.co.test_project.unit_integration.student.exception.StudentNotFoundException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void canGetAllStudents() {
        //When
        underTest.getAllStudents();
        //Then
        verify(studentRepository).findAll();
    }

    @Test
    void canAddStudent() {
        //Given
        Student student = new Student(
                "Ishmail",
                "ishmail@gmail.com",
                Gender.MALE
        );
        //When
        underTest.addStudent(student);
        //Given
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(studentRepository)
                .save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }
    @Test
    void willThrowWhenEmailIsTaken() {
        //Given
        Student student = new Student(
                "Ishmail",
                "ishmail@gmail.com",
                Gender.MALE
        );
        given(studentRepository.selectExistsEmail(student.getEmail()))
                .willReturn(true);
        //When
        //Given
        assertThatThrownBy(() ->underTest.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " taken");
        verify(studentRepository, never()).save(any());
    }

    @Test
    void canDeleteStudent() {
        // Given
        Long studentId = 1L;
        given(studentRepository.existsById(studentId))
                .willReturn(true);

        // When
        underTest.deleteStudent(studentId);

        // Then
        verify(studentRepository).deleteById(studentId);
    }

    @Test
    void willThrowWhenStudentIdDoesNotExist() {
        // Given
        Long studentId = 1L;
        given(studentRepository.existsById(studentId))
                .willReturn(false);

        // When
        // Then
        assertThatThrownBy(() -> underTest.deleteStudent(studentId))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student with id " + studentId + " does not exists");

        verify(studentRepository, never()).deleteById(any());
    }
}