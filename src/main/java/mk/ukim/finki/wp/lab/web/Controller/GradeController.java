package mk.ukim.finki.wp.lab.web.Controller;


import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exeption.StudentNotFound;
import mk.ukim.finki.wp.lab.service.Impl.CourseServiceImpl;
import mk.ukim.finki.wp.lab.service.Impl.GradeServiceImpl;
import mk.ukim.finki.wp.lab.service.Impl.StudentServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/grades")
public class GradeController {

    private final GradeServiceImpl gradeService;
    private final CourseServiceImpl courseService;

    public GradeController(GradeServiceImpl gradeService, CourseServiceImpl courseService) {
        this.gradeService = gradeService;
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    public String getAddGradePage(@PathVariable Long id,
                                  Model model) {
        List<Student> students = this.courseService.listStudentsByCourse(id);
        List<Grade> grated = this.gradeService.listStudentGradesByCourse(id);
        List<Student> notGrated = students.stream()
                .filter(s -> grated.stream()
                        .noneMatch(g -> s.getUsername().equals(g.getStudent().getUsername()))
                )
                .collect(Collectors.toList());
        model.addAttribute("courseId",id);
        model.addAttribute("grated",grated);
        model.addAttribute("notGrated",notGrated);

        return "gradeStudent";
    }

    @PostMapping("/addGrade/{id}")
    public String addGradeToStudent(@RequestParam String chosenStudent,
                                    @RequestParam String chosenGrade,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                                    @PathVariable Long id){
        try{
            Optional<Grade> grade = this.gradeService.findGradeByCourseAndStudent(id,chosenStudent);
            if(grade.isPresent()){
                this.gradeService.update(grade.get().getId(),chosenGrade.charAt(0), chosenStudent, id, dateTime);
            }else {
                this.gradeService.save(chosenGrade.charAt(0), chosenStudent, id, dateTime);
            }
        }catch (StudentNotFound s){
            System.out.println(s.getMessage());
        }

        return "redirect:/courses";
    }
}
