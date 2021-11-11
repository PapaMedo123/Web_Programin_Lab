package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.Impl.CourseServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentSumaryServlet",urlPatterns = "/studentEnrollmentSummary")
public class StudentSumaryServlet extends HttpServlet {

    private final CourseServiceImpl courseService;
    private final SpringTemplateEngine templateEngine;

    public StudentSumaryServlet(CourseServiceImpl studentService, SpringTemplateEngine templateEngine) {
        this.courseService = studentService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        String student = (String) req.getSession().getAttribute("chosenStudent");
        String ID = (String) req.getSession().getAttribute("courseId");

        Long course = Long.parseLong(ID);
        this.courseService.addStudentInCourse(student, course);
        String courseName = this.courseService.courseById(course).getName();

        List<Student> students = this.courseService.listStudentsByCourse(course);
        context.setVariable("studentsInCourse",students);
        context.setVariable("courseName",courseName);

        this.templateEngine.process("studentsInCourse",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
