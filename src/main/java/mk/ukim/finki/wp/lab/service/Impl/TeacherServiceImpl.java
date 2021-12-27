package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.TeacherFullname;
import mk.ukim.finki.wp.lab.model.exeption.TeacherNotFoundExeption;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;


    public TeacherServiceImpl(TeacherRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Teacher> findAll() {
        return repository.findAll();
    }

    @Override
    public Teacher findById(Long Id) {
        return repository.findById(Id).orElseThrow(() -> new TeacherNotFoundExeption(Id));
    }

    @Override
    public void delete(Long Id) {
        repository.delete(this.findById(Id));
    }

    @Override
    public Teacher save(String name, String surname, LocalDate dateOfEmployment) {
        return repository.save(new Teacher(new TeacherFullname(name,surname), dateOfEmployment));
    }

    @Override
    public Teacher update(Long Id, String name, String surname, LocalDate dateOfEmployment) {
        Teacher teacher = this.findById(Id);
        teacher.getPersonName().setName(name);
        teacher.getPersonName().setSurname(surname);
        teacher.setDateOfEmployment(dateOfEmployment);
        return repository.save(teacher);
    }
}
