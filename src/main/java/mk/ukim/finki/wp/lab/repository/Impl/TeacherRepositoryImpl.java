package mk.ukim.finki.wp.lab.repository.Impl;

//import mk.ukim.finki.wp.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/*
@Repository
public class TeacherRepositoryImpl{


    public List<Teacher> findAll(){
        return DataHolder.teachers;
    }

    public Optional<Teacher> findById(Long Id){
        return DataHolder.teachers.stream().filter(t -> t.getId().equals(Id)).findFirst();
    }

    public Teacher createOrUpdate(Long Id, String name, String surname){
        Teacher prof = new Teacher(name, surname);
        DataHolder.teachers.removeIf(s -> s.getId().equals(prof.getId()));
        DataHolder.teachers.add(prof);
        return prof;
    }

    public void deleteTeacher(Long id) {
        DataHolder.teachers.removeIf(t -> t.getId().equals(id));
    }
}

 */
