package mk.ukim.finki.wp.lab.model.exeption;

public class TeacherNotFoundExeption extends RuntimeException {
    public TeacherNotFoundExeption(Long Id) {
        super(String.format("Teacher with ID:%d not found",Id));
    }
}
