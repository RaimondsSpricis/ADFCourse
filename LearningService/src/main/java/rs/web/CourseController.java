package rs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.data.*;
import rs.services.CourseService;

import java.util.List;

@RestController
public class CourseController {

    @Autowired private CourseService service;

    @GetMapping("/courses/{id}")
    public Course getCourse(@PathVariable Integer id) {
        return service.getCourse(id);
    }

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return service.getCourses();
    }

    @PostMapping("/courses")
    public Integer addCourse(@RequestBody CourseDTO dto) {
        return service.addCourse(dto);
    }

    @PutMapping("/courses/{id}")
    public void updateCourse(@PathVariable Integer id, @RequestBody CourseDTO dto) {
        service.updateCourse(id, dto);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable Integer id) {
        service.deleteCourse(id);
    }
}
