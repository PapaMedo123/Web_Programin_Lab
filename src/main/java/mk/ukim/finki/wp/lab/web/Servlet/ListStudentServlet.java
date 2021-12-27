package mk.ukim.finki.wp.lab.web.Servlet;


import mk.ukim.finki.wp.lab.service.Impl.StudentServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ListStudentServlet" , urlPatterns = "/addStudent")
public class ListStudentServlet extends HttpServlet {

    private final StudentServiceImpl students;
    private final SpringTemplateEngine tamplateengine;

    public ListStudentServlet(StudentServiceImpl students, SpringTemplateEngine tamplateengine) {
        this.students = students;
        this.tamplateengine = tamplateengine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        String ID = (String) req.getSession().getAttribute("courseId");
        context.setVariable("courseId",ID);
        context.setVariable("liststudents",students.listAll());

        tamplateengine.process("listStudents.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String student = req.getParameter("chosenStudent");
        req.getSession().setAttribute("chosenStudent",student);
        resp.sendRedirect("/studentEnrollmentSummary");
    }
}
