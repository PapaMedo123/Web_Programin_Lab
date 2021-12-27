package mk.ukim.finki.wp.lab.repository.Impl;

//import mk.ukim.finki.wp.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/*
@Repository
public class StudentRepositoryImpl{

    public List<Student> findAllStudents(){
        return DataHolder.students;
    }

    public List<Student> findAllByNameOrSurname(String text){
        return DataHolder.students.stream()
                .filter(r -> r.getPersonName().getName().equals(text) || r.getSurname().equals(text))
                .collect(Collectors.toList());
    }

    public Student createOrUpdate(String username, String password, String name, String surname){
        Student student = new Student(username, password, name, surname);
        DataHolder.students.removeIf(s -> s.getName().equals(student.getName()) && s.getSurname().equals(student.getSurname()));
        DataHolder.students.add(student);
        return student;
    }
}

 */
