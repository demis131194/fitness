package by.epam.fitness.filter;

import by.epam.fitness.command.AttributeName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/*"}, dispatcherTypes = {
        DispatcherType.FORWARD,
        DispatcherType.INCLUDE,
        DispatcherType.REQUEST
})
public class CurrentPageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String currentPage = httpRequest.getRequestURL().toString();
        int index = currentPage.indexOf("jsp/");
        currentPage = currentPage.substring(index);
        httpRequest.getSession().setAttribute(AttributeName.CURRENT_PAGE, currentPage);
        chain.doFilter(httpRequest, response);
    }
}
