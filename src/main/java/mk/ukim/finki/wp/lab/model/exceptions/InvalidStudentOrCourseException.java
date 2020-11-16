package mk.ukim.finki.wp.lab.model.exceptions;

public class InvalidStudentOrCourseException extends RuntimeException{

    public InvalidStudentOrCourseException(){
        super("Invalid student or course");
    }
}

