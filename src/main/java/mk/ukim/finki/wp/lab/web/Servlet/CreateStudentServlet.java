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

@WebServlet(name = "CreateStudentServlet", urlPatterns = "/createStudent")
public class CreateStudentServlet extends HttpServlet {

    private final StudentServiceImpl studentService;
    private final SpringTemplateEngine templateEngine;

    public CreateStudentServlet(StudentServiceImpl studentService, SpringTemplateEngine templateEngine) {
        this.studentService = studentService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        templateEngine.process("createstudent.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        try {
            studentService.save(username, password, name, surname);
            resp.sendRedirect("/addStudent");
        }
        catch (IllegalArgumentException e){
            WebContext context = new WebContext(req,resp,req.getServletContext());
            context.setVariable("hasError",true);
            context.setVariable("errormessage",e.getMessage());
            templateEngine.process("createstudent.html",context,resp.getWriter());
            resp.sendRedirect("/createStudent");
        }
    }
}
