package rs.web;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rs.data.*;
import rs.services.StudentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired MockMvc mvc;
    @MockBean StudentService service;

    @Test
    void getStudent() throws Exception {
        var id = 22;
        var time = LocalDateTime.of(2022, 1, 3, 9, 9, 22);
        var s = new Student(id, time, "Jane", "Wolf", "jane.wolf@qwertz.com", true);
        Mockito.when(service.getStudent(id))
                .thenReturn(s);
        mvc.perform(get("/students/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.registered", is(time.toString())))
                .andExpect(jsonPath("$.firstName", is(s.getFirstName())))
                .andExpect(jsonPath("$.surname", is(s.getSurname())))
                .andExpect(jsonPath("$.email", is(s.getEmail())))
                .andExpect(jsonPath("$.emailNotification", is(s.getEmailNotification())));
    }

    @Test
    void getStudentJson() throws Exception {
        var id = 22;
        var time = LocalDateTime.of(2022, 1, 3, 9, 9, 22);
        var s = new Student(id, time, "Jane", "Wolf", "jane.wolf@qwertz.com", true);
        var test = """
            {
                "id": 22,
                "registered": "2022-01-03T09:09:22",
                "firstName": "Jane",
                "surname": "Wolf",
                "email": "jane.wolf@qwertz.com",
                "emailNotification": true
            }""";
        Mockito.when(service.getStudent(id))
                .thenReturn(s);
        mvc.perform(get("/students/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(test));
    }

    @Test
    void getStudents() throws Exception {
        var time1 = LocalDateTime.of(2022, 1, 3, 9, 9, 22);
        var time2 = LocalDateTime.of(2022, 4, 4, 22, 59, 1);
        var list = List.of(
                new Student(22, time1, "Jane", "Wolf", "jane.wolf@qwerty.com", true),
                new Student(33, time2, "John", "Smith", "jjohn.smith@abc.com", false)
        );
        Mockito.when(service.getStudents())
                .thenReturn(list);
        mvc.perform(get("/students").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[*].id", is(list.stream().map(Student::getId).collect(Collectors.toList()))))
                .andExpect(jsonPath("$[*].registered", is(list.stream().map(x -> x.getRegistered().toString()).collect(Collectors.toList()))))
                .andExpect(jsonPath("$[*].firstName", is(list.stream().map(Student::getFirstName).collect(Collectors.toList()))))
                .andExpect(jsonPath("$[*].surname", is(list.stream().map(Student::getSurname).collect(Collectors.toList()))))
                .andExpect(jsonPath("$[*].email", is(list.stream().map(Student::getEmail).collect(Collectors.toList()))))
                .andExpect(jsonPath("$[*].emailNotification", is(list.stream().map(Student::getEmailNotification).collect(Collectors.toList()))));
    }

    @Test
    void addStudent() throws Exception {
        var id = 22;
        var dto = new StudentDTO("Jane", "Wolf", "jane.wolf@qwerty.com", true);
        Mockito.when(service.addStudent(dto))
                .thenReturn(id);
        mvc.perform(post("/students")
                .content(Util.toJson(dto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(id)));
    }
}