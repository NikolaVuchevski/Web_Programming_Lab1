package mk.ukim.finki.wp.lab.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class RedirectFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String courseId = (String) request.getSession().getAttribute("courseId");

        String path = request.getServletPath();

        if(path.equals("/listCourses") || courseId!=null){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            response.sendRedirect("listCourses");
        }
    }
}
