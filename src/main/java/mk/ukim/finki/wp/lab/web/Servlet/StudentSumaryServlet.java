package mk.ukim.finki.wp.lab.web.Servlet;

import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.Impl.CourseServiceImpl;
import mk.ukim.finki.wp.lab.service.Impl.GradeServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "StudentSumaryServlet",urlPatterns = "/studentEnrollmentSummary")
public class StudentSumaryServlet extends HttpServlet {

    private final CourseServiceImpl courseService;
    private final SpringTemplateEngine templateEngine;
    private final GradeServiceImpl gradeService;

    public StudentSumaryServlet(CourseServiceImpl studentService, SpringTemplateEngine templateEngine, GradeServiceImpl gradeService) {
        this.courseService = studentService;
        this.templateEngine = templateEngine;
        this.gradeService = gradeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        String student = (String) req.getSession().getAttribute("chosenStudent");
        String ID = (String) req.getSession().getAttribute("courseId");

        Long courseId = Long.parseLong(ID);
        this.courseService.addStudentInCourse(student, courseId);
        List<Student> students = this.courseService.listStudentsByCourse(courseId);
        String courseName = this.courseService.courseById(courseId).getName();
        List<Grade> grades = this.gradeService.listStudentGradesByCourse(courseId);

        List<Student> notGrated = students.stream()
                .filter(s -> grades.stream()
                        .noneMatch(g -> s.getUsername().equals(g.getStudent().getUsername()))
                )
                .collect(Collectors.toList());

        context.setVariable("studentsWithGradeInCourse",grades);
        context.setVariable("courseName",courseName);
        context.setVariable("studentsWithoutGradeInCourse",notGrated);

        this.templateEngine.process("studentsInCourse.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
