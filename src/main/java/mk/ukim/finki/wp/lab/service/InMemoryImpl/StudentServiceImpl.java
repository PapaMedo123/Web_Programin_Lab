package mk.ukim.finki.wp.lab.service.InMemoryImpl;

import mk.ukim.finki.wp.lab.model.Student;
//import mk.ukim.finki.wp.lab.repository.Impl.StudentRepositoryImpl;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

/*
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryImpl repository;

    public StudentServiceImpl(StudentRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> listAll() {
        return repository.findAllStudents();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        if(text==null || text.isEmpty()){
            throw new IllegalArgumentException("Text not valid");
        }
        List<Student> students = null;
        students = repository.findAllByNameOrSurname(text);

        if(students == null || students.isEmpty()) {
            throw new IllegalArgumentException("Name or Surname not found");
        }
        return students;
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()
        || name==null || name.isEmpty() || surname==null || surname.isEmpty())
        {
            throw new IllegalArgumentException("Arguments not vaild");
        }
        return repository.createOrUpdate(username, password, name, surname);
    }
}

 */
