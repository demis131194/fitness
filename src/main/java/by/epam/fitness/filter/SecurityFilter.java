package by.epam.fitness.filter;

import by.epam.fitness.command.AttributeName;
import by.epam.fitness.command.PagePath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Security filter.
 */
@WebFilter(urlPatterns = { "/jsp/pages/admin/*",
        "/jsp/pages/client/*",
        "/jsp/pages/trainer/*"
})
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String userRole = ((String) httpRequest.getSession().getAttribute(AttributeName.USER_ROLE));
        if (userRole != null) {
            userRole = userRole.toLowerCase();
            String currentPage = httpRequest.getRequestURL().toString();

            if (currentPage.contains("/" + userRole + "/")) {
                chain.doFilter(request, response);
            } else {
                httpRequest.getRequestDispatcher(PagePath.ILLEGAL_ACCESS).forward(request, response);
            }
        } else {
            httpRequest.getRequestDispatcher(PagePath.ILLEGAL_ACCESS).forward(request, response);
        }
    }
}
