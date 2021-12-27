package mk.ukim.finki.wp.lab.repository.Impl;

//import mk.ukim.finki.wp.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enumerations.Type;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
@Repository
public class CourseRepositoryImpl{

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

    public Course createOrUpdate(Long Id,String name, String description, List<Student> students, Teacher teacher){
        boolean flag = DataHolder.courses.removeIf(s -> s.getCourseId().equals(Id));
        DataHolder.courses.add(new Course(name,description,students,teacher, Type.SUMMER));
        return new Course(name,description,students,teacher,Type.SUMMER);
    }

    public void deleteCourse(Long Id){
        DataHolder.courses.removeIf(c -> c.getCourseId().equals(Id));
    }

}


 */