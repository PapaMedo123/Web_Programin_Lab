package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {

    public List<Course> findAllCourses(){
        return DataHolder.courses;
    }

    public Optional<Course> findById(Long courseId){
        return DataHolder.courses.stream().filter(r -> r.getCourseId().equals(courseId)).findFirst();
    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        Optional<Course> course = findById(courseId);
        if(course.isPresent()) {
            return course.get().getStudents();
        }
        return null;
    }

    public Course addStudentToCourse(Student student, Course course){
        Course local_course = DataHolder.courses.stream().filter(r -> r.equals(course)).findFirst().get();
        local_course.getStudents().add(student);
        return local_course;
    }
}
