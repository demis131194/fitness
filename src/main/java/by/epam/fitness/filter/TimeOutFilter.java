package by.epam.fitness.filter;

import by.epam.fitness.command.PagePath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class TimeOutFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        if(session.isNew()) {
            request.getRequestDispatcher(PagePath.MAIN_PATH).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
