package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    private final StudentService studentService;

    public CourseServiceImpl(CourseRepository repository, StudentService studentService) {
        this.repository = repository;
        this.studentService = studentService;
    }


    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        if(courseId==null){
            throw new IllegalArgumentException("ID not valid");
        }
        List<Student> students = null;
        students = repository.findAllStudentsByCourse(courseId);
        if(students==null || students.isEmpty()){
            throw new IllegalArgumentException("No students with ID");
        }
        return students;
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        if(courseId==null || username==null || username.isEmpty()){
            throw new IllegalArgumentException("ID or username not valid");
        }
        Student student = null;
        student = studentService.listAll().stream().filter(s -> s.getUsername().equals(username)).findFirst().get();
        Optional<Course> course = null;
        course = repository.findById(courseId);
        if(course==null || course.isEmpty()){
            throw new IllegalArgumentException("No course with ID");
        }
        if(course.get().getStudents().contains(student)){
            return course.get();
        }
        return repository.addStudentToCourse(student,course.get());
    }

    @Override
    public List<Course> listAll(){
        return repository.findAllCourses();
    }

    @Override
    public Course courseById(Long courseId){
        Course course = null;
        if (repository.findById(courseId).isPresent()){
            course = repository.findById(courseId).get();
        }
        return course;
    }
}
