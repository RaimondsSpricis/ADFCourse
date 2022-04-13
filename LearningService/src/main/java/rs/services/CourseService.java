package rs.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.data.*;

import javax.persistence.*;
import java.util.List;

@Service
@Transactional
public class CourseService {

    @PersistenceContext private EntityManager em;

    public Course getCourse(Integer id) {
        return em.find(Course.class, id);
    }

    public List<Course> getCourses() {
        var query = "from Course";
        return em.createQuery(query, Course.class).getResultList();
    }

    public void addCourse(CourseDTO dto) {
        var c = new Course();
        c.setTitle(dto.title());
        c.setDescription(dto.description());
        c.setStudyTime(dto.studyTime());
        em.persist(c);
    }

    public void updateCourse(Integer id, CourseDTO dto) {
        var c = getCourse(id);
        c.setTitle(dto.title());
        c.setDescription(dto.description());
        c.setStudyTime(dto.studyTime());
        em.merge(c);
    }

    public void deleteCourse(Integer id) {
        var query = "delete from StudentCourse sc where sc.courseId = :id";
        em.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
        query = "delete from Course c where c.id = :id";
        em.createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }
}
