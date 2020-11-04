package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    public static List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init(){
        students.add(new Student("nikola", "p1","Nikola","Vuchevski"));
        students.add(new Student("stefani", "p1","Stefani","Kostic"));
        students.add(new Student("dimitar", "p1","Dimitar","Ugrov"));
        students.add(new Student("nenad", "p1","Nenad","Pantic"));
        students.add(new Student("svetle", "p1","Svetlana","Ugrova"));
    }

    public List<Student> findAllStudents(){
        return students;
    }

    public List<Student> findAllByNameOrSurname(String text){
        return students.stream().filter(r->r.getName().contains(text)||r.getSurname().contains(text)).collect(Collectors.toList());
    }

    public Student findByUsername(String text){
        return (Student)students.stream().filter(r->r.getUsername().equals(text));
    }

    public Student save(Student s) {
        if (s==null || s.getName().isEmpty()) {
            return null;
        }
        students.removeIf(r->r.getName().equals(s.getName()));
        students.add(s);
        return s;
    }

}
