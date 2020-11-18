package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {
    public static List<Course> courses=new ArrayList<>();

    @PostConstruct
    public void init(){
        courses.add(new Course("Diskretna Matematika 1","DM1"));
        courses.add(new Course("Strukturno Programiranje","SP"));
        courses.add(new Course("Objektno-Orientirano Programiranje","OOP"));
        courses.add(new Course("Napredno Programiranje","NP"));
        courses.add(new Course("Web Programiranje","WP"));
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

    public Optional<Course> save(String name, String description, Teacher teacher) {
        courses.removeIf(c->c.getName().equals(name)||c.getDescription().equals(description));
        Course course = new Course(name, description, teacher);
        courses.add(course);
        return Optional.of(course);
    }

    public void deleteById(Long id) {
        courses.removeIf(i -> i.getCourseId().equals(id));
    }

}
