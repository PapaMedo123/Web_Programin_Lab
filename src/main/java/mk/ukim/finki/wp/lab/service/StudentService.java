package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
//    Student findByUsername(String username);
    List<Student> listAll();
    List<Student> searchByNameOrSurname(String text);
    Student save(String username, String password, String name, String surname);
}
