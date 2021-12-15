package mk.ukim.finki.wp.lab.model.exeption;

public class CourseNameIdentedy extends RuntimeException{
    public CourseNameIdentedy() {
        super("Course can not be created with same name as other course");
    }
}
