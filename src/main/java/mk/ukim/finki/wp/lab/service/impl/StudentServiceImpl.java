package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAllStudents();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        if(text.isEmpty() || text == null){
            throw new InvalidArgumentsException();
        }
        return studentRepository.findAllByNameOrSurname(text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if(username.isEmpty() || username == null && password.isEmpty() || password == null){
            throw new InvalidArgumentsException();
        }
        Student student = new Student(username, password, name, surname);
        studentRepository.save(student);
        return student;
    }

    @Override
    public Student findStudentByUsername(String username) {
        return studentRepository.findByUsername(username);
    }
}
