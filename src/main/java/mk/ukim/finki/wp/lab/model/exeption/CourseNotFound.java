package mk.ukim.finki.wp.lab.model.exeption;

public class CourseNotFound extends RuntimeException{
    public CourseNotFound(Long Id) {
        super("Student with Id "+Id+" Not Found");
    }
}

