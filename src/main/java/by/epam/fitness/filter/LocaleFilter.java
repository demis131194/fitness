package by.epam.fitness.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Locale filter.
 */
@WebFilter(urlPatterns = {"/jsp/*"}, dispatcherTypes = {
        DispatcherType.FORWARD,
        DispatcherType.INCLUDE,
        DispatcherType.REQUEST
})
public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getSession(false) != null && httpRequest.getSession(false).getAttribute("locale") == null) {
            httpRequest.getSession().setAttribute("locale", "en_EN");
        }
        chain.doFilter(httpRequest, response);
    }
}
