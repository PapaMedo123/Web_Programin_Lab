package mk.ukim.finki.wp.lab.model.exeption;

public class StudentsNotFoundInCourse extends RuntimeException{
    public StudentsNotFoundInCourse(Long Id) {
        super("Students in Course with Id "+Id+" Not Found");
    }
}
