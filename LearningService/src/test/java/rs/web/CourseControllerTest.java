package rs.web;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rs.data.*;
import rs.services.CourseService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired MockMvc mvc;
    @MockBean CourseService service;

    @Test
    void getCourse() throws Exception {
        var id = 4;
        var c = new Course(id, "Java Concurrency",
                "Multithreading, concurrency and parallelism in Java.", new BigDecimal("20.00"));
        Mockito.when(service.getCourse(id))
                .thenReturn(c);
        mvc.perform(get("/courses/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.title", is(c.getTitle())))
                .andExpect(jsonPath("$.description", is(c.getDescription())))
                .andExpect(jsonPath("$.studyTime", is(c.getStudyTime().toString())));
    }

    @Test
    void getCourses() throws Exception {
        var list = List.of(
                new Course(1, "JavaScript for Beginners",
                        "The basics of JavaScript: language syntax, GUI building in the browser, Node.js runtime environment, practice projects.", new BigDecimal("160.00")),
                new Course(2, "Algorithms for Competitive Programming",
                        "Common algorithms and data structures for programming contests. Main topics: arrays, lists, maps, trees, graphs, strings, mathematical algorithms, dynamic programming.", new BigDecimal("320.00")),
                new Course(3, "Python Fundamentals",
                        "Python practice projects.", new BigDecimal("40.00")),
                new Course(4, "Java Concurrency",
                        "Multithreading, concurrency and parallelism in Java.", new BigDecimal("20.00"))
        );
        Mockito.when(service.getCourses())
                .thenReturn(list);
        mvc.perform(get("/courses").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(4)))
                .andExpect(jsonPath("$[*].id", is(list.stream().map(Course::getId).collect(Collectors.toList()))))
                .andExpect(jsonPath("$[*].title", is(list.stream().map(Course::getTitle).collect(Collectors.toList()))))
                .andExpect(jsonPath("$[*].description", is(list.stream().map(Course::getDescription).collect(Collectors.toList()))))
                .andExpect(jsonPath("$[*].studyTime", is(list.stream().map(x -> x.getStudyTime().toString()).collect(Collectors.toList()))));
    }

    @Test
    void addCourse() throws Exception {
        var id = 4;
        var dto = new CourseDTO("Java Concurrency",
                "Multithreading, concurrency and parallelism in Java.", new BigDecimal("20.00"));
        Mockito.when(service.addCourse(dto))
                .thenReturn(id);
        mvc.perform(post("/courses")
                        .content(Util.toJson(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(id)));
    }
}
