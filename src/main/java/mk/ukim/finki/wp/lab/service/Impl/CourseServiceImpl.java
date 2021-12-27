package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumerations.Type;
import mk.ukim.finki.wp.lab.model.exeption.*;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        if(courseId==null){
            throw new IllegalArgumentException("ID not valid");
        }

        Course course = this.courseById(courseId);
        List<Student> students = course.getStudents();
        if(students==null || students.isEmpty()){
            throw new StudentsNotFoundInCourse(courseId);
        }
        return students;
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        if(courseId==null || username==null || username.isEmpty()){
            throw new IllegalArgumentException("ID or username not valid");
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

        if(course.get().getStudents().contains(student.get())){
            return course.get();
        }
        course.get().getStudents().add(student.get());
        return courseRepository.save(course.get());
    }

    @Override
    public List<Course> listAll(){
        return courseRepository.findAll();
    }

    @Override
    public Course courseById(Long courseId){
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()){
            throw new CourseNotFound(courseId);
        }
        return course.get();
    }

    @Override
    public void delete(Long Id) {
        courseRepository.delete(this.courseById(Id));
    }

    @Override
    public Course save(String name, String description, List<Student> students, Long teacher, Type type){
        Teacher add_teacher = teacherRepository.findById(teacher).orElseThrow(() -> new TeacherNotFoundExeption(teacher));

        if(courseRepository.findAll().stream().anyMatch(s -> s.getName().equals(name))){
            throw new CourseNameIdentedy();
        }

        return courseRepository.save(new Course(name, description, students, add_teacher,type));
    }

    @Override
    public Course update(Long Id, String name, String description, Long teacher,Type type) {
        Course course = this.courseById(Id);
        course.setName(name);
        course.setDescription(description);
        course.setTeacher(teacherRepository.findById(teacher).get());
        course.setType(type);
        return courseRepository.save(course);
    }

    @Override
    public List<Course> listLimitedAmountOfCourses(int number) {
        Pageable selectNuberOdCourses = PageRequest.of(0, number);
        Page<Course> allCourses = courseRepository.findAll(selectNuberOdCourses);
        return allCourses.stream().collect(Collectors.toList());
    }

}
