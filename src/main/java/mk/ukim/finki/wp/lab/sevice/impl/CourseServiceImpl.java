package mk.ukim.finki.wp.lab.sevice.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.sevice.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public CourseServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        if(username==null||username.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Student student=studentRepository.findByUsername(username);
        Course course=courseRepository.findById(courseId);
        courseRepository.addStudentToCourse(student, course);
        return course;
    }
}
