package mk.ukim.finki.wp.lab.web.Servlet;


import mk.ukim.finki.wp.lab.service.Impl.CourseServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CoursesListSevlet",urlPatterns = "/listCourses")
public class CoursesListSevlet extends HttpServlet {

    private final CourseServiceImpl courses;
    private final SpringTemplateEngine tamplateengine;

    public CoursesListSevlet(CourseServiceImpl courses, SpringTemplateEngine tamplateengine) {
        this.courses = courses;
        this.tamplateengine = tamplateengine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("listcourses",courses.listAll());
        tamplateengine.process("listCourses.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ID = req.getParameter("courseId");
        req.getSession().setAttribute("courseId", ID);
        resp.sendRedirect("/addStudent");
    }
}
