package mk.ukim.finki.wp.lab.model.exeption;

public class StudentsByNameOrSurnameNotFound extends RuntimeException {
    public StudentsByNameOrSurnameNotFound(String text) {
        super("Students with Name or Surname "+text+" Not Found");
    }

}
