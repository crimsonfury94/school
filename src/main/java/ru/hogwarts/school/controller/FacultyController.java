package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getBook(@PathVariable long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createBook(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editBook(@RequestBody Faculty faculty) {
        Faculty foundFaculty =  facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public Faculty deleteBook(@PathVariable long id) {
        return facultyService.deleteFaculty(id);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllStudents(String color) {
        return ResponseEntity.ok(facultyService.getAllFaculties(color));
    }
}
