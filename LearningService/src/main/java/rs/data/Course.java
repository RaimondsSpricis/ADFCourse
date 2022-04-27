package rs.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Integer id;
    @Column(name = "title") private String title;
    @Column(name = "description") private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "study_time") private BigDecimal studyTime;

    public Course() {}

    public Course(Integer id, String title, String description, BigDecimal studyTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.studyTime = studyTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(BigDecimal studyTime) {
        this.studyTime = studyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(title, course.title) && Objects.equals(description, course.description) && Objects.equals(studyTime, course.studyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, studyTime);
    }
}
