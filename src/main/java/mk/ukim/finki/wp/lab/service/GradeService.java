package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GradeService {
    Grade gradeById(Long Id);
    List<Grade> listAll();
    Grade save(Character grade, String username, Long courseId, LocalDateTime timestamp);
    Grade update(Long Id, Character grade, String username, Long courseId, LocalDateTime timestamp);
    void delete(Long Id);
    List<Grade> listStudentGradesByCourse(Long courseId);
    List<Grade> listGradesByStudent(String username);
    Optional<Grade> findGradeByCourseAndStudent(Long courseId, String username);
    List<Grade> findGradeByTimestampBetween (LocalDateTime from, LocalDateTime to);

}
