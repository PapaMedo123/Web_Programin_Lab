package mk.ukim.finki.wp.lab.web.Controller;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.enumerations.Type;
import mk.ukim.finki.wp.lab.model.exeption.CourseNameIdentedy;
import mk.ukim.finki.wp.lab.model.exeption.TeacherNotFoundExeption;
import mk.ukim.finki.wp.lab.service.Impl.CourseServiceImpl;
import mk.ukim.finki.wp.lab.service.Impl.TeacherServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
@RequestMapping("/courses")
public class CourseController   {

    private final CourseServiceImpl courseService;
    private final SpringTemplateEngine tamplateengine;
    private final TeacherServiceImpl teacherService;

    public CourseController(CourseServiceImpl courses, SpringTemplateEngine tamplateengine, TeacherServiceImpl teacherService) {
        this.courseService = courses;
        this.tamplateengine = tamplateengine;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("listcourses",courseService.listAll());
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("errormessage",error);
        }
        return "listCourses";
    }

    @PostMapping
    public String postCoursesPage(@RequestParam Long courseId, HttpSession session) {
        session.setAttribute("courseId", courseId);
        return "redirect:/addStudent";
    }

    @GetMapping("pageble")
    public String getCoursesPagePageble(@RequestParam(required = false) Integer number,
                                        @RequestParam(required = false) String error,
                                        Model model){
        if(number != null){
            if(number < 1){
                number = 1;
            }
            int size=courseService.listAll().size();
            if(number > size){
                number = size;
            }
        }
        else {
            number = 1;
        }
        model.addAttribute("listcourses",courseService.listLimitedAmountOfCourses(number));
        model.addAttribute("num",number);
        model.addAttribute("pageble",true);
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("errormessage",error);
        }
        return "listCourses";
    }

    @GetMapping("/add-form")
    public String getAddCoursePage(Model model){
        model.addAttribute("teachers",teacherService.findAll());
        return "add-course";
    }

    @PostMapping("/add/{id}")
    public String postSaveCourse(@RequestParam String name,
                                 @RequestParam String description,
                                 @RequestParam Long TeacherID,
                                 @RequestParam String typeName,
                                 @PathVariable(required = false) Long id) {
        try {
            Type type = Type.valueOf(typeName);
            if (id == null || id == 0) {
                this.courseService.save(name, description, new ArrayList<>(), TeacherID, type);
            } else {
                this.courseService.update(id, name, description, TeacherID, type);
            }
        }
        catch(TeacherNotFoundExeption | CourseNameIdentedy exeption){
            System.out.println(exeption.getMessage());
            return "redirect:/courses?error=" + exeption.getMessage();
        }
        return "redirect:/courses";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model,HttpSession session){
        Course c = courseService.courseById(id);
        model.addAttribute("teachers",teacherService.findAll());
        if(c == null)
        {
            return "redirect:/courses?error="+String.format("Course with ID:%d does not exist",id);
        }
        model.addAttribute("course",c);
        return "add-course";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.delete(id);
        return "redirect:/courses";
    }

    //Имплементирајте метод public String deleteCourse(@PathVariable Long id).

    // Нека одговара на mapping /courses/delete/{id}, и при успешно избришан курс од листата повторно нека ја прикажува
    // листата со курсеви.

}
