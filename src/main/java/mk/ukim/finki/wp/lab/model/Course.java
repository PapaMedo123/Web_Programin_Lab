package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.enumerations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long courseId;

    private String name;

    @Column(length = 4000)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;

    @ManyToOne
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Course(String name, String description, List<Student> students, Teacher teacher, Type type) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;
        this.type = type;
    }

    public Course(){}
}
