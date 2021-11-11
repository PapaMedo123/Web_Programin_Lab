package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.List;

@Data
public class Course {
    //Во mk.ukim.finki.wp.lab.model креирајте Course класа која ќе содржи:
    private Long courseId;
    private String name;
    private String description;
    private List<Student> students;

    public Course(Long courseId, String name, String description, List<Student> students) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.students = students;
    }

}
