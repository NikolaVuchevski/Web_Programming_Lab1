package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Course {
    private Long courseId;
    private String name;
    private String description;
    private List<Student> students;
    private Teacher teacher;

    public Course(String name, String description) {
        this.courseId = (long) (Math.random()*1000);
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
        this.teacher=null;
    }

    public Course(String name, String description, Teacher teacher) {
        this.courseId = (long) (Math.random()*1000);
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
        this.teacher=teacher;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
