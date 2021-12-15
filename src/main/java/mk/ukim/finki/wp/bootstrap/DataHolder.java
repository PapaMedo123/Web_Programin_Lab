package mk.ukim.finki.wp.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataHolder {

    private static Random random = new Random();

    public static List<Student> students = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();

    public static Long GetId(){
        return random.nextLong();
    }

    @PostConstruct
    public void init(){
        students.add(new Student("1van4e","ij","Ivan","Janev"));
        students.add(new Student("M!t4e","mm","Mite","Mazgaliev"));
        students.add(new Student("Mart!n4e","md","Martin","Dinev"));
        students.add(new Student("Pan4e","pk","Pance","Kotev"));

        teachers.add(new Teacher(random.nextLong(), "Petar", "Babaev"));
        teachers.add(new Teacher(random.nextLong(), "Dejan", "Slamkov"));
        teachers.add(new Teacher(random.nextLong(), "Kire", "Bucev"));
        teachers.add(new Teacher(random.nextLong(), "Kapul", "Rasten"));
        teachers.add(new Teacher(random.nextLong(), "Rasputin", "Russian"));

        courses.add(new  Course("Calculus", "Mathematics", List.copyOf(students),teachers.get(0)));
        courses.add(new  Course("Verojatnos", "Life", List.copyOf(students),teachers.get(1)));
        courses.add(new  Course("Bazi Na Podatoci", "Data", List.copyOf(students),teachers.get(2)));
        courses.add(new  Course("Robotika", "Dog", List.copyOf(students),teachers.get(3)));
        courses.add(new  Course("Paralelizacija", "Linearnost", List.copyOf(students),teachers.get(4)));
    }
}
