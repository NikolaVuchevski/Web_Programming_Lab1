package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CoursesListSevlet", urlPatterns = "/listCourses")
public class CoursesListServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;


    public CoursesListServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("courses", courseService.listAll());
        springTemplateEngine.process("listCourses.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseId = req.getParameter("courseId");
        req.getSession().setAttribute("courseId", courseId);
        resp.sendRedirect("/addStudent");
    }
}
