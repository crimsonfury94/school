package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {


    @Mock
    private StudentRepository studentRepository;


    @InjectMocks
    private StudentService studentService;

//    @BeforeEach
//    public void beforeEach() {
//        List<Student> students = List.of(
//                new Student(1, "Harry Potter", 18),
//                new Student(2, "Luna Lovegood", 16),
//                new Student(3, "Cedric Diggory", 22),
//                new Student(4, "Ron Weasley", 18)
//
//        );
//        when(studentRepository.findAll()).thenReturn(students);
//    }

    @ParameterizedTest
    @MethodSource("ageParams")
    void createStudent(Student student) {

        studentRepository.save(student);
        assertThat(studentService.getAllStudents(18)).containsExactlyInAnyOrder(student);
    }

    @ParameterizedTest
    @MethodSource("ageParams")
    void findStudent() {
    }

    @ParameterizedTest
    @MethodSource("ageParams")
    void editStudent() {
    }

    @ParameterizedTest
    @MethodSource("ageParams")
    void deleteStudent() {
    }

    @ParameterizedTest
    @MethodSource("ageParams")
    public void filterTest(int age, Student expected) {
        assertThat(studentService.getAllStudents(age)).isEqualTo(expected);
    }

    @Test
    public void filterSecondTest() {
        assertThat(studentService.getAllStudents(18)).containsExactlyInAnyOrderElementsOf(List.of(
                new Student(1, "Harry Potter", 18),
                        new  Student(4, "Ron Weasley", 18)));
    }
    public static Stream<Arguments> ageParams() {

        return Stream.of(
                Arguments.of(new Student(1, "Harry Potter", 18)),
                Arguments.of(new Student(4, "Ron Weasley", 18))
        );
    }

    public static Stream<Arguments> filterParams() {
        return Stream.of(
                Arguments.of(1, List.of(new Student(1, "Harry Potter", 18),
                        new Student(4, "Ron Weasley", 18))),
                Arguments.of(2, List.of(new Student(2, "Luna Lovegood", 16)
                )),
                Arguments.of(3, List.of(new Student(3, "Cedric Diggory", 22)
                )),
                Arguments.of(4, Collections.emptyList())
        );
    }
}