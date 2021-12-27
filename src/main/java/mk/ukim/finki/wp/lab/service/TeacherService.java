package mk.ukim.finki.wp.lab.service;


import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> findAll();
    Teacher findById(Long Id);
    void delete(Long Id);
    Teacher save(String name, String surname, LocalDate dateOfEmployment);
    Teacher update(Long Id, String name, String surname, LocalDate dateOfEmployment);
}
