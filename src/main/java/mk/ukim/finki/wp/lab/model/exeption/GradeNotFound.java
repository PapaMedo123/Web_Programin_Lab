package mk.ukim.finki.wp.lab.model.exeption;

public class GradeNotFound extends RuntimeException{
    public GradeNotFound(Long Id) {
        super("Grade with Id "+Id+" Not Found");
    }
}
