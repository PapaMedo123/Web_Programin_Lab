package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.Converters.TeacherFullnameConverter;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Convert(converter = TeacherFullnameConverter.class)
    private TeacherFullname personName;

    private LocalDate dateOfEmployment;

    public Teacher(TeacherFullname personName, LocalDate dateOfEmployment) {
        this.personName = personName;
        this.dateOfEmployment = dateOfEmployment;
    }

    public Teacher() {
    }
}
