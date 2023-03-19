package filter;

import entity.DAO.IDao;
import entity.FactoryDAO.FactoryDAO;
import entity.district.District;
import entity.rent_house.RentHouse;
import entity.street.Street;
import java.io.IOException;
import java.util.Map;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import support_enum.AttributeEnum;
import support_enum.ServletEnum;
import support_enum.URLEnum;
import webpage_tools.PrintTools;

@WebFilter(dispatcherTypes = {
    DispatcherType.REQUEST,
    DispatcherType.FORWARD,
    DispatcherType.INCLUDE,
    DispatcherType.ERROR
},
         urlPatterns = {"/*"})
public final class RootFilter implements Filter {
    private static IDao<District> districtDao = FactoryDAO.getDao(FactoryDAO.DISTRICT);
    private static IDao<Street> streetDao = FactoryDAO.getDao(FactoryDAO.STREET);
    private static IDao<RentHouse> rentHouseDao = FactoryDAO.getDao(FactoryDAO.RENT_HOUSE);
    
    public RootFilter() {}

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("Served at [root filter]");
        request.setCharacterEncoding(PrintTools.getUTF8());
        ServletContext application = request.getServletContext();
        
        //Getting application scope attribute, check if these attribute is null or not
        //if null, init new then add to application scope
        Map<Integer, District> districtMap = (Map)application.getAttribute(AttributeEnum.districtMap.name());
        Map<Integer, Street> streetMap = (Map)application.getAttribute(AttributeEnum.streetMap.name());
        Map<Integer, RentHouse> rentHouseMap = (Map)application.getAttribute(AttributeEnum.streetMap.name());
        Object rootURL = application.getAttribute(AttributeEnum.root.name());
        
        if(districtMap == null) {
            districtMap = districtDao.getAll();
            application.setAttribute(AttributeEnum.districtMap.name(), districtMap);
        }
        
        if(streetMap == null) {
            streetMap = streetDao.getAll();
            application.setAttribute(AttributeEnum.streetMap.name(), streetMap);
        }
        
        if(rentHouseMap == null) {
            rentHouseMap = rentHouseDao.getAll();
            application.setAttribute(AttributeEnum.rentHouseMap.name(), rentHouseMap);
        }
        
        if(rootURL == null) InitRootURLs(application);
        HttpSession session = ((HttpServletRequest)request).getSession();
        Object checkPoint = session.getAttribute(AttributeEnum.checkpoint.name());
        if(checkPoint == null) InitPagination((HttpServletRequest)request);
      
        chain.doFilter(request, response);
    }
    
    private void InitRootURLs(ServletContext application) {
        String rootURL = application.getContextPath();
        //Set some URLs
        application.setAttribute(URLEnum.root.name(), rootURL);
        application.setAttribute(URLEnum.user.name(), rootURL.concat(ServletEnum.USER.getURL()));
        application.setAttribute(URLEnum.user_update.name(), rootURL.concat(ServletEnum.USER_UPDATE.getURL()));
        application.setAttribute(URLEnum.user_comment.name(), rootURL.concat(ServletEnum.USER_COMMENT.getURL()));
        application.setAttribute(URLEnum.rent_house.name(), rootURL.concat(ServletEnum.RENT_HOUSE.getURL()));
        application.setAttribute(URLEnum.rent_house_detail.name(), rootURL.concat(ServletEnum.RENT_HOUSE_DETAIL.getURL()));
        application.setAttribute(URLEnum.rent_house_search.name(), rootURL.concat(ServletEnum.RENT_HOUSE_SEARCH.getURL()));
    }
    
    private void InitPagination(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(AttributeEnum.checkpoint.name(), "");
        session.setAttribute(AttributeEnum.lowerPageNum.name(), 1);
        session.setAttribute(AttributeEnum.upperPageNum.name(), 9);
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

}
