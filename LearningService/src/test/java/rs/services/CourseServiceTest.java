package rs.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.data.*;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CourseServiceTest {

    @Autowired CourseService service;

    @Test
    void getCourse() {
        var test = new Course(4, "Java Concurrency",
                "Multithreading, concurrency and parallelism in Java.", new BigDecimal("20.00"));
        var c = service.getCourse(test.getId());
        assertThat(c).isEqualTo(test);
    }

    @Test
    void getCourses() {
        var tests = List.of(
                new Course(1, "JavaScript for Beginners",
                        "The basics of JavaScript: language syntax, GUI building in the browser, Node.js runtime environment, practice projects.", new BigDecimal("160.00")),
                new Course(2, "Algorithms for Competitive Programming",
                        "Common algorithms and data structures for programming contests. Main topics: arrays, lists, maps, trees, graphs, strings, mathematical algorithms, dynamic programming.", new BigDecimal("320.00")),
                new Course(3, "Python Fundamentals",
                        "Python practice projects.", new BigDecimal("40.00")),
                new Course(4, "Java Concurrency",
                        "Multithreading, concurrency and parallelism in Java.", new BigDecimal("20.00"))
        );
        var results = service.getCourses();
        assertThat(results).hasSize(4);
        for (int i = 0; i < results.size(); i++)
            assertThat(results.get(i)).isEqualTo(tests.get(i));
    }

    @Test
    void crudCourse() {
        var test = new Course(null, "C++ Fundamentals",
                "C++ practice projects and solutions.", new BigDecimal("40.25"));
        var dto = new CourseDTO(test.getTitle(), test.getDescription(), test.getStudyTime());
        var id = service.addCourse(dto);
        var c = service.getCourse(id);
        test.setId(id);
        assertThat(c).isEqualTo(test);

        test = new Course(id, "Programming Gems",
                "Case studies of various advanced programming topics.", new BigDecimal("10.33"));
        dto = new CourseDTO(test.getTitle(), test.getDescription(), test.getStudyTime());
        service.updateCourse(id, dto);
        c = service.getCourse(id);
        assertThat(c).isEqualTo(test);

        service.deleteCourse(id);
        c = service.getCourse(id);
        assertThat(c).isNull();
    }
}
