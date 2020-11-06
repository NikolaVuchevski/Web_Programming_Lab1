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
        return (Course)courses.stream().filter(r->r.getCourseId().equals(courseId));
    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        Course course=findById(courseId);
        return course.getStudents();
    }

    public Course addStudentToCourse(Student student, Course course){
        List<Student> students=findAllStudentsByCourse(course.getCourseId());
        students.add(student);
        course.setStudents(students);
        return course;
    }

    public long getCourseId(){
        return courses.size();
    }
}
