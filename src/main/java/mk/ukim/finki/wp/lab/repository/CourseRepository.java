package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    public static List<Course> courses=new ArrayList<>();

    @PostConstruct
    public void init(){
        courses.add(new Course(1l, "Diskretna Matematika 1","DM1"));
        courses.add(new Course(2l, "Strukturno Programiranje","SP"));
        courses.add(new Course(3l, "Objektno-Orientirano Programiranje","OOP"));
        courses.add(new Course(4l, "Napredno Programiranje","NP"));
        courses.add(new Course(5l, "Web Programiranje","WP"));
    }

    public List<Course> findAllCourses(){
        return courses;
    }

    public Course findById(Long courseId){
        return courses.stream().filter(r->r.getCourseId().equals(courseId)).findFirst().get();
    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        Course course = courses.stream().filter(r->r.getCourseId().equals(courseId)).findFirst().get();
        return course.getStudents();
    }

    public Course addStudentToCourse(Student student, Course course){
        Course c = courses.stream().filter(r->r.getCourseId().equals(course.getCourseId())).findFirst().get();
        c.getStudents().removeIf(r->r.getUsername().equals(student.getUsername()));
        c.getStudents().add(student);
        return course;
    }
}
