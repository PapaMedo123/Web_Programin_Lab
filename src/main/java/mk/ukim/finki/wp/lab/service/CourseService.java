package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.enumerations.Type;

import java.util.List;

public interface CourseService{
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    List<Course> listAll();
    Course courseById(Long courseId);
    void delete(Long Id);
    Course save(String name, String description, List<Student> students, Long teacher, Type type);
    Course update(Long Id, String name, String description, Long teacher, Type type);
    List<Course> listLimitedAmountOfCourses(int number);
}
