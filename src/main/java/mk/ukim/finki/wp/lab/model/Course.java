package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.List;

@Data
public class Course {
    private Long courseId;
    private String name;
    private String description;
    private List<Student> students;

    public Course(Long courseId, String name, String description) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.students = null;
    }

    public Long getCourseId() {
        return courseId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
