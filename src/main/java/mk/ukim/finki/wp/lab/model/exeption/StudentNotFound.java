package mk.ukim.finki.wp.lab.model.exeption;

public class StudentNotFound extends RuntimeException{
    public StudentNotFound(String username) {
        super("Student with username "+username+" Not Found");
    }
}

