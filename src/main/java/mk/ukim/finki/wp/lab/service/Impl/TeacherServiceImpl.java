package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exeption.CourseNameIdentedy;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Teacher> findById(Long Id) {
        return repository.findById(Id);
    }

    @Override
    public Optional<Teacher> teacherById(Long teacherId) {
        return repository.findById(teacherId);
    }

    @Override
    public void delete(Long Id) {
        repository.deleteTeacher(Id);
    }

    @Override
    public Teacher save(Long Id, String name, String surname) {
        Long ID = Id;
        if(Id == null || Id == 0) {
            ID = DataHolder.GetId();
        }
        return repository.createOrUpdate(ID, name, surname);
    }
}
