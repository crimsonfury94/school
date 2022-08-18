package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.stream.Stream;

@Service
public class StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long lastId = 0;


    public Student createStudent(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student findStudent(long id) {
        return students.get(id);
    }

    public Student editStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;

    }

    public Student deleteStudent(long id) {
        return students.remove(id);
    }

    public Stream<Student> getAllStudents(int age) {
        return  students.values().stream()
                .filter(e -> e.getAge() == age);
    }
}
