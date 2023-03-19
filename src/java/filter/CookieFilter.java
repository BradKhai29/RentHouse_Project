package filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import webpage_tools.URLBuilderFactory;

@WebFilter(dispatcherTypes = {
    DispatcherType.REQUEST,
    DispatcherType.FORWARD,
    DispatcherType.INCLUDE,
    DispatcherType.ERROR
},
         urlPatterns = {"/cookieSupport", "/CookieSupportServlet"})
public final class CookieFilter implements Filter {

    private static final String HOME_PAGE = URLBuilderFactory.getURL(support_enum.ServletEnum.HOME);

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Blocking user from calling Cookie Support Servlet");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(HOME_PAGE);
        requestDispatcher.forward(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

}
