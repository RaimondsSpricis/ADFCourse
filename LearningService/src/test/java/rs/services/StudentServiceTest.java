package rs.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.data.Student;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentServiceTest {

    @Autowired EntityManager em;
    @Autowired
    StudentService service;

    @Test
    void getStudentTest() {
        var s = service.getStudent(2);
        assertThat(s.getFirstName()).isEqualTo("Jane");
        assertThat(s.getSurname()).isEqualTo("Eastwood");
        assertThat(s.getEmail()).isEqualTo("jane.eastwood@qwerty.com");
        assertThat(s.getEmailNotification()).isTrue();
    }

    @Test
    void getStudentsTest() {
        var results = service.getStudents();
        assertThat(results).hasSize(2)
                .extracting(Student::getSurname)
                .contains("Smith", "Eastwood");
    }
}