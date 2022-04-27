package rs.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.data.*;

import javax.persistence.*;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @PersistenceContext private EntityManager em;

    public Student getStudent(Integer id) {
        return em.find(Student.class, id);
    }

    public List<Student> getStudents() {
        var query = "from Student";
        return em.createQuery(query, Student.class).getResultList();
    }

    public Integer addStudent(StudentDTO dto) {
        var s = new Student();
        s.setFirstName(dto.firstName());
        s.setSurname(dto.surname());
        s.setEmail(dto.email());
        s.setEmailNotification(dto.emailNotification());
        em.persist(s);
        return s.getId();
    }

    public void updateStudent(Integer id, StudentDTO dto) {
        var s = getStudent(id);
        s.setFirstName(dto.firstName());
        s.setSurname(dto.surname());
        s.setEmail(dto.email());
        s.setEmailNotification(dto.emailNotification());
        em.merge(s);
    }

    public void deleteStudent(Integer id) {
        var query = "delete from StudentCourse sc where sc.studentId = :id";
        em.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
        query = "delete from Student s where s.id = :id";
        em.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    public void addStudentCourse(Integer studentId, Integer courseId) {
        var st = new StudentCourse();
        st.setStudentId(studentId);
        st.setCourseId(courseId);
        em.persist(st);
    }
}
