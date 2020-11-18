package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("courses", courseService.listAll());
        return "courses";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam long id){
        this.courseService.save(name, description, id);
        return "redirect:/courses";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        this.courseService.deleteById(id);
        return "redirect:/courses";
    }

    @PostMapping
    public void sendStudentsPage(HttpServletRequest request, HttpServletResponse response, Model model){
        String courseId = request.getParameter("courseId");
        request.getSession().setAttribute("course", courseId);
        try {
            response.sendRedirect("/addStudent");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/add-form")
    public String getAddCoursesPage(Model model){
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("edit", false);
        return "add-course";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model){

        if(courseService.findById(id)!=null){
            Course c=courseService.findById(id);
            model.addAttribute("teachers",teacherService.findAll());
            model.addAttribute("course", c);
            return "add-course";
        }

        return "redirect:/courses?error=CourseNotFound";
    }
}
