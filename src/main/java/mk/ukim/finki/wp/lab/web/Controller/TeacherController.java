package mk.ukim.finki.wp.lab.web.Controller;


import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exeption.TeacherNotFoundExeption;
import mk.ukim.finki.wp.lab.service.Impl.CourseServiceImpl;
import mk.ukim.finki.wp.lab.service.Impl.TeacherServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final CourseServiceImpl courseService;
    private final SpringTemplateEngine tamplateengine;
    private final TeacherServiceImpl teacherService;

    public TeacherController(CourseServiceImpl courses, SpringTemplateEngine tamplateengine, TeacherServiceImpl teacherService) {
        this.courseService = courses;
        this.tamplateengine = tamplateengine;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getTeacherPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("listTeachers",teacherService.findAll());
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("errormessage",error);
        }
        return "list_Teachers";
    }

    @GetMapping("/add-form")
    public String getAddTeacherPage(Model model){
        model.addAttribute("teachers",teacherService.findAll());
        return "add_teacher";
    }

    @PostMapping("/add/{id}")
    public String postSaveCourse(@RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                 @PathVariable(required = false) Long id) {
        try {
            if (id == null || id == 0) {
                this.teacherService.save(name, surname, date);
            } else {
                this.teacherService.update(id, name, surname, date);
            }
        }catch (TeacherNotFoundExeption e){
            System.out.println(e.getMessage());
        }
        return "redirect:/teachers";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditTeacherPage(@PathVariable Long id, Model model,HttpSession session){
        Teacher t = null;
        try {
            t = teacherService.findById(id);
            model.addAttribute("teachers", teacherService.findAll());
            model.addAttribute("teacher", t);

            return "add_teacher";
        }
        catch(TeacherNotFoundExeption e){
            System.out.println(e.getMessage());
            return "redirect:/teachers?error=" + String.format("Teacher with ID:%d does not exist", id);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id){
        teacherService.delete(id);
        return "redirect:/teachers";
    }
}
