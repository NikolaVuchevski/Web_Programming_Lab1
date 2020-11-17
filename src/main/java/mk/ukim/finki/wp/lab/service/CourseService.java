package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> listAll();
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    Course findById(Long courseId);
    Optional<Course> save(String name, String description, long id);
    void deleteById(Long id);
}
