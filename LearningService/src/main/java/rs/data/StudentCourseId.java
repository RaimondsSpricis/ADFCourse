package rs.data;

import java.io.Serializable;

public class StudentCourseId implements Serializable {
    private Integer studentId;
    private Integer courseId;

    public StudentCourseId(Integer studentId, Integer courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
}
