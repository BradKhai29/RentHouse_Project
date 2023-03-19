package filter;

import entity.user.User;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import support_enum.ActionEnum;
import support_enum.AttributeEnum;
import support_enum.WebPageEnum;
import webpage_tools.URLBuilder;
import webpage_tools.URLBuilderFactory;


@WebFilter(filterName="UserFilter", urlPatterns={"/user", "/user/*"}, dispatcherTypes={DispatcherType.REQUEST, DispatcherType.FORWARD})
public class UserFilter implements Filter {
    private static final URLBuilder urlBuilder = URLBuilderFactory.get();
    private static final String HOMEPAGE = urlBuilder
                                           .addPage(WebPageEnum.HOME)
                                           .getURL();
    
    private FilterConfig filterConfig;

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException {
        String receiveAction = request.getParameter("action");
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession(true);
        RequestDispatcher requestDispatcher;
        
        ActionEnum action = ActionEnum.get(receiveAction);
        User user = (User)session.getAttribute(AttributeEnum.USER.name());
        boolean isLoginUser = Objects.nonNull(user);
        
        if(isLoginUser) {
            boolean isRegisterAction = action.equals(ActionEnum.REGISTER);
            if(isRegisterAction) {
                requestDispatcher = request.getRequestDispatcher(HOMEPAGE);
                requestDispatcher.forward(request, response);
            }
            else chain.doFilter(request, response);
        }
        else {
            StringBuffer requestURL = httpRequest.getRequestURL();
            boolean isValidAction = action.equals(ActionEnum.CREATE) == false 
                                    && (requestURL.toString().contains("comment") == false);
            if(isValidAction) {
                chain.doFilter(request, response);
            }
            else {
                requestDispatcher = request.getRequestDispatcher(HOMEPAGE);
                requestDispatcher.forward(request, response);
            }
        }
    }

    
    public void destroy() {}

    public void init(FilterConfig filterConfig) {
	this.filterConfig = filterConfig;
    }

    public void log(String msg) {
	filterConfig.getServletContext().log(msg); 
    }
}
