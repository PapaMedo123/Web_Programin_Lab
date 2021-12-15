package mk.ukim.finki.wp.lab.service;


import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    public List<Teacher> findAll();
    public Optional<Teacher> findById(Long Id);
    public Optional<Teacher> teacherById(Long teacherId);
    public void delete(Long Id);
    public Teacher save(Long Id, String name, String surname);
}
