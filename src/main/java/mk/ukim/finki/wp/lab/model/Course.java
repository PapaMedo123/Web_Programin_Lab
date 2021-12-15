package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.List;
import java.util.Random;

@Data
public class Course {
    private Long courseId;
    private String name;
    private String description;
    private List<Student> students;
    private Teacher teacher;

    private static Random random = new Random();

    public Course(String name, String description, List<Student> students, Teacher teacher) {
        this.courseId = genarateID();
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;
    }

    public static long genarateID(){
        return random.nextLong();
    }
}
