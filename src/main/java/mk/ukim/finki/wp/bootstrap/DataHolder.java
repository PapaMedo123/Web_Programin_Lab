package mk.ukim.finki.wp.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataHolder {

    public static List<Student> students = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();

    @PostConstruct
    public void init(){
        students.add(new Student("1van4e","ij","Ivan","Janev"));
        students.add(new Student("M!t4e","mm","Mite","Mazgaliev"));
        students.add(new Student("Mart!n4e","md","Martin","Dinev"));
        students.add(new Student("Pan4e","pk","Pance","Kotev"));
        courses.add(new  Course(10856781051L, "Calculus", "Mathematics", List.copyOf(students)));
        courses.add(new  Course(98263095620L, "Verojatnos", "Life", List.copyOf(students)));
        courses.add(new  Course(83478695347L, "Bazi Na Podatoci", "Data", List.copyOf(students)));
        courses.add(new  Course(23974659634L, "Robotika", "Dog", students));
        courses.add(new  Course(40937593246L, "Paralelizacija", "Linearnost", List.copyOf(students)));
    }
}
