package rs.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.data.*;

import java.time.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentServiceTest {

    @Autowired StudentService service;

    @Test
    void getStudent() {
        var time = LocalDateTime.of(2022, 4, 4, 19, 9, 1);
        var test = new Student(2, time, "Jane", "Eastwood", "jane.eastwood@qwerty.com", true);
        var s = service.getStudent(test.getId());
        assertThat(s).isEqualTo(test);
    }

    @Test
    void getStudents() {
        var time1 = LocalDateTime.of(2022, 2, 28, 8, 23, 31);
        var time2 = LocalDateTime.of(2022, 4, 4, 19, 9, 1);
        var tests = List.of(
                new Student(1, time1, "John", "Smith", "john.smith@abc.com", false),
                new Student(2, time2, "Jane", "Eastwood", "jane.eastwood@qwerty.com", true)
        );
        var results = service.getStudents();
        assertThat(results).hasSize(2);
        for (int i = 0; i < results.size(); i++)
            assertThat(results.get(i)).isEqualTo(tests.get(i));
    }

    @Test
    void crudStudent() {
        var test = new Student(null, null, "Janett", "Fox", "janett.fox@qwerty.com", false);
        var dto = new StudentDTO(test.getFirstName(), test.getSurname(), test.getEmail(),
                test.getEmailNotification());
        var id = service.addStudent(dto);
        var s = service.getStudent(id);
        test.setId(id);
        test.setRegistered(s.getRegistered());
        assertThat(s).isEqualTo(test);

        test = new Student(id, s.getRegistered(), "Jane", "Wolf", "jane.wolf@qwertz.com", true);
        dto = new StudentDTO(test.getFirstName(), test.getSurname(), test.getEmail(),
                test.getEmailNotification());
        service.updateStudent(id, dto);
        s = service.getStudent(id);
        assertThat(s).isEqualTo(test);

        service.deleteStudent(id);
        s = service.getStudent(id);
        assertThat(s).isNull();
    }
}