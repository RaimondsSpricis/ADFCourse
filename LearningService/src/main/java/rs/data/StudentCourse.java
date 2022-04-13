package rs.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_courses")
@IdClass(StudentCourseId.class)
public class StudentCourse {
    @Id
    @Column(name = "student_id") private Integer studentId;
    @Id
    @Column(name = "course_id") private Integer courseId;
    @Column(name = "registered") private LocalDateTime registered = LocalDateTime.now();
    @Column(name = "completed") private LocalDateTime completed;
    @Column(name = "message_sent") private Boolean messageSent = false;

    public StudentCourse() {}

    public StudentCourse(Integer studentId, Integer courseId, LocalDateTime registered, LocalDateTime completed, Boolean messageSent) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.registered = registered;
        this.completed = completed;
        this.messageSent = messageSent;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public LocalDateTime getCompleted() {
        return completed;
    }

    public void setCompleted(LocalDateTime completed) {
        this.completed = completed;
    }

    public Boolean getMessageSent() {
        return messageSent;
    }

    public void setMessageSent(Boolean messageSent) {
        this.messageSent = messageSent;
    }
}
