package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherRepository {
    public static List<Teacher> teachers=new ArrayList<>();

    @PostConstruct
    public void init(){
        teachers.add(new Teacher("Riste", "Stojanov"));
        teachers.add(new Teacher("Dimitar", "Trajanov"));
        teachers.add(new Teacher("Kostadin", "Mishev"));
        teachers.add(new Teacher("Ana", "Todorovska"));
        teachers.add(new Teacher("Ivan", "Kitanovski"));
    }

    public List<Teacher> findAll(){
        return teachers;
    }

    public Teacher findById(long id){
        return teachers.stream().filter(r->r.getId().equals(id)).findFirst().get();
    }
}
