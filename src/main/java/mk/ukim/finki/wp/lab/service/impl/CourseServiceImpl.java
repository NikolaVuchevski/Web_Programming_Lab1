package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidStudentOrCourseException;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CourseServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Course course = courseRepository.findById(courseId);
        Student student = studentRepository.findByUsername(username);
        if (course == null || student == null) {
            throw new InvalidStudentOrCourseException();
        }
        courseRepository.addStudentToCourse(student, course);
        return course;
    }

    @Override
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public Optional<Course> save(String name, String description, long id) {
        Teacher teacher=teacherRepository.findById(id);
        return this.courseRepository.save(name, description, teacher);
    }

    @Override
    public void deleteById(Long id) {
        this.courseRepository.deleteById(id);
    }
}
