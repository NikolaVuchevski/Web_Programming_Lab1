package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.sevice.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/listCourses")
public class CoursesListSevlet extends HttpServlet {
    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;

    public CoursesListSevlet(CourseService courseService, SpringTemplateEngine springTemplateEngine) {
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext=new WebContext(req, resp, req.getServletContext());
        List<Course> courses=courseService.findAll();
        webContext.setVariable("courses", courses);
        resp.setContentType("text/html; charset=UTF-8");
        this.springTemplateEngine.process("/listCourses.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        session.setAttribute("course",req.getParameter("course"));
        long courseID = courseService.getCourseId();
        Course c = new Course(courseID, "","");
        session.setAttribute("course", c);
        resp.sendRedirect("/AddStudent.do");
    }
}
