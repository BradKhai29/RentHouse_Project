package filter;

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
import support_enum.FolderEnum;
import support_enum.WebPageEnum;
import webpage_tools.URLBuilder;
import webpage_tools.URLBuilderFactory;


@WebFilter(filterName="UserFilter", urlPatterns={"/user?, /user/*"}, dispatcherTypes={DispatcherType.REQUEST, DispatcherType.FORWARD})
public class UserFilter implements Filter {
    private static final URLBuilder urlBuilder = URLBuilderFactory.get();
    private static final String HOMEPAGE = urlBuilder
                                           .addFolder(FolderEnum.RENT_HOUSE)
                                           .addPage(WebPageEnum.CREATE_NEW_RENT_HOUSE)
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
        Object user = session.getAttribute(AttributeEnum.USER.name());
                
        //Accept Create and Update action on user
        boolean isAcceptAction = action.equals(ActionEnum.CREATE) || action.equals(ActionEnum.UPDATE);
        boolean UserNonNull = Objects.nonNull(user);
        
        if(isAcceptAction && UserNonNull) chain.doFilter(request, response);
        else {
            //If user not null but try to access login or register action
            //then dispatch to homepage
            if (UserNonNull) {
                requestDispatcher = request.getRequestDispatcher(HOMEPAGE);
                requestDispatcher.forward(request, response);
            }
            //if user is null, accept the request
            else chain.doFilter(request, response);
        }
    }

    public void destroy() { 
    }

    public void init(FilterConfig filterConfig) {
	this.filterConfig = filterConfig;
        
    }

    public void log(String msg) {
	filterConfig.getServletContext().log(msg); 
    }

}
