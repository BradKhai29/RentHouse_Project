package filter;

import entity.user.User;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import support_enum.AttributeEnum;
import support_enum.WebPageEnum;
import webpage_tools.URLBuilderFactory;


@WebFilter(filterName="RentHouseFilter", urlPatterns={"/user/rent_house", "/user/rent_house/*"}, dispatcherTypes={DispatcherType.REQUEST, DispatcherType.FORWARD})
public class RentHouseFilter implements Filter {
    private static final String HOMEPAGE = URLBuilderFactory.get()
                                           .addPage(WebPageEnum.HOME)
                                           .getURL();
    private FilterConfig filterConfig = null;

    public FilterConfig getFilterConfig() {
	return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
	this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
	throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession(true);
        User user = (User)session.getAttribute(AttributeEnum.USER.name());
        RequestDispatcher requestDispatcher;
        
        if(user == null) {
            requestDispatcher = request.getRequestDispatcher(HOMEPAGE);
            requestDispatcher.forward(request, response);
            return;
        }
        boolean isRentHouseProvider = user.getUserRole();
        if(isRentHouseProvider) {
            chain.doFilter(request, response);
        }
        else {
            System.out.println("Not a rent house provider");
            requestDispatcher = request.getRequestDispatcher(HOMEPAGE);
            requestDispatcher.forward(request, response);
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
