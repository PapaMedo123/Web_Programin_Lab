package mk.ukim.finki.wp.lab.service.InMemoryImpl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exeption.CourseNameIdentedy;
import mk.ukim.finki.wp.lab.model.exeption.TeacherNotFoundExeption;
//import mk.ukim.finki.wp.lab.repository.Impl.CourseRepositoryImpl;
//import mk.ukim.finki.wp.lab.repository.Impl.TeacherRepositoryImpl;
import mk.ukim.finki.wp.lab.service.CourseService;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepositoryImpl repository;
    private final StudentServiceImpl studentService;
    private final TeacherRepositoryImpl teacherRepository;

    public CourseServiceImpl(CourseRepositoryImpl repository, StudentServiceImpl studentService, TeacherRepositoryImpl teacherRepository) {
        this.repository = repository;
        this.studentService = studentService;
        this.teacherRepository = teacherRepository;
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

    @Override
    public void delete(Long Id) {
        repository.deleteCourse(Id);
    }

    @Override
    public Course save(Long Id, String name, String description, List<Student> students, Long teacher){
        Teacher add_teacher = teacherRepository.findById(teacher).orElseThrow(() -> new TeacherNotFoundExeption(teacher));
        if(repository.findAllCourses().stream().anyMatch(s -> s.getName().equals(name) && !s.getCourseId().equals(Id))){
            throw new CourseNameIdentedy();
        }
        return repository.createOrUpdate(Id, name, description, students, add_teacher);
    }
}
 */
