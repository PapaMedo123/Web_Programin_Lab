package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;

import mk.ukim.finki.wp.lab.model.exeption.CourseNotFound;
import mk.ukim.finki.wp.lab.model.exeption.GradeNotFound;
import mk.ukim.finki.wp.lab.model.exeption.StudentNotFound;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.GradeRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public GradeServiceImpl(GradeRepository gradeRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Grade gradeById(Long Id) {
        Optional<Grade> grade = gradeRepository.findById(Id);
        if (grade.isEmpty()){
            throw new GradeNotFound(Id);
        }
        return grade.get();
    }

    @Override
    public List<Grade> listAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade save(Character grade, String username, Long courseId, LocalDateTime timestamp) {
        if(courseId==null || username==null || username.isEmpty()){
            throw new IllegalArgumentException("Course or Student not valid");
        }
        Optional<Student> student = null;
        student = studentRepository.findById(username);
        if (student.isEmpty()){
            throw new StudentNotFound(username);
        }

        Optional<Course> course = null;
        course = courseRepository.findById(courseId);
        if(course.isEmpty()){
            throw new CourseNotFound(courseId);
        }

        return gradeRepository.save(new Grade(grade,student.get(),course.get(),timestamp));
    }

    @Override
    public Grade update(Long Id, Character grade_char, String username, Long courseId, LocalDateTime timestamp) {
        Optional<Student> student = null;
        student = studentRepository.findById(username);
        if (student.isEmpty()){
            throw new StudentNotFound(username);
        }

        Optional<Course> course = null;
        course = courseRepository.findById(courseId);
        if(course.isEmpty()){
            throw new CourseNotFound(courseId);
        }

        Grade grade = this.gradeById(Id);
        grade.setGrade(grade_char);
        grade.setStudent(student.get());
        grade.setCourse(course.get());
        grade.setTimestamp(timestamp);
        return gradeRepository.save(grade);
    }

    @Override
    public void delete(Long Id) {
        gradeRepository.delete(this.gradeById(Id));
    }

    @Override
    public List<Grade> listStudentGradesByCourse(Long courseId) {
        Optional<Course> course = null;
        course = courseRepository.findById(courseId);
        if(course.isEmpty()){
            throw new CourseNotFound(courseId);
        }
        return gradeRepository.findAllByCourse(course.get());
    }

    @Override
    public List<Grade> listGradesByStudent(String username) {
        Optional<Student> student = null;
        student = studentRepository.findById(username);
        if (student.isEmpty()){
            throw new StudentNotFound(username);
        }
        return gradeRepository.findAllByStudent(student.get());
    }

    @Override
    public Optional<Grade> findGradeByCourseAndStudent(Long courseId, String username) {
        Optional<Student> student = null;
        student = studentRepository.findById(username);
        if (student.isEmpty()){
            throw new StudentNotFound(username);
        }

        Optional<Course> course = null;
        course = courseRepository.findById(courseId);
        if(course.isEmpty()){
            throw new CourseNotFound(courseId);
        }
        return gradeRepository.findGradeByCourseAndStudent(course.get(),student.get());
    }

    @Override
    public List<Grade> findGradeByTimestampBetween(LocalDateTime from, LocalDateTime to) {
        return gradeRepository.findAllByTimestampBetween(from,to);
    }
}
