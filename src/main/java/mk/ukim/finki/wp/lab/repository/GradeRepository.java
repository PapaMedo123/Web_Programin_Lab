package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {
    List<Grade> findAllByCourse(Course course);
    List<Grade> findAllByStudent(Student student);
    Optional<Grade> findGradeByCourseAndStudent(Course course, Student student);
    List<Grade> findAllByTimestampBetween(LocalDateTime from, LocalDateTime to);
}
