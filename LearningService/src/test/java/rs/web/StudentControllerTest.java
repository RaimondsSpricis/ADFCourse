package rs.web;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rs.data.Student;
import rs.services.StudentService;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired MockMvc mvc;
    @MockBean StudentService service;

    @Test
    void getStudent() throws Exception {
        var id = 22;
        var time = LocalDateTime.of(2022, 1, 3, 9, 9, 22);
        var s = new Student(id, time, "Jane", "Wolf", "jane.wolf@qwerty.com", true);
        Mockito.when(service.getStudent(id))
                .thenReturn(s);
        mvc.perform(get("/students/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.registered", is(time.toString())))
                .andExpect(jsonPath("$.firstName", is(s.getFirstName())))
                .andExpect(jsonPath("$.surname", is(s.getSurname())))
                .andExpect(jsonPath("$.email", is(s.getEmail())))
                .andExpect(jsonPath("$.emailNotification", is(s.getEmailNotification())));
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
        mvc.perform(get("/students").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[*].id", contains(list.get(0).getId(), list.get(1).getId())))
                .andExpect(jsonPath("$[*].registered", contains(list.get(0).getRegistered().toString(), list.get(1).getRegistered().toString())))
                .andExpect(jsonPath("$[*].firstName", contains(list.get(0).getFirstName(), list.get(1).getFirstName())))
                .andExpect(jsonPath("$[*].surname", contains(list.get(0).getSurname(), list.get(1).getSurname())))
                .andExpect(jsonPath("$[*].email", contains(list.get(0).getEmail(), list.get(1).getEmail())))
                .andExpect(jsonPath("$[*].emailNotification", contains(list.get(0).getEmailNotification(), list.get(1).getEmailNotification())));
    }
}