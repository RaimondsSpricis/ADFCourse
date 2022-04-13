package rs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.data.*;
import rs.services.StudentService;

import java.util.List;

@RestController
public class StudentController {

    @Autowired private StudentService service;

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return service.getStudent(id);
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return service.getStudents();
    }

    @PostMapping("/students")
    public void addStudent(@RequestBody StudentDTO dto) {
        service.addStudent(dto);
    }

    @PutMapping("/students/{id}")
    public void updateStudent(@PathVariable Integer id, @RequestBody StudentDTO dto) {
        service.updateStudent(id, dto);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        service.deleteStudent(id);
    }

    @PostMapping("/students-courses")
    public void addStudentCourse(Integer studentId, Integer courseId) {
        service.addStudentCourse(studentId, courseId);
    }
}
