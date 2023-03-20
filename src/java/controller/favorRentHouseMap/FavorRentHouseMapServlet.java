package controller.favorRentHouseMap;

import controller.validation.InputValidation;
import entity.user.User;
import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import support_enum.ActionEnum;
import support_enum.AttributeEnum;
import support_enum.FolderEnum;
import support_enum.ServletEnum;
import support_enum.WebPageEnum;
import webpage_tools.URLBuilderFactory;


@WebServlet(name="FavorRentHouseMapServlet", urlPatterns={"/user/favor"})
public class FavorRentHouseMapServlet extends HttpServlet {
    private static String FAVOR_LIST_PAGE = URLBuilderFactory
            .get()
            .addFolder(FolderEnum.USER)
            .addPage(WebPageEnum.USER_FAVOR_RENT_HOUSE_LIST)
            .getURL();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String inputAction = request.getParameter(AttributeEnum.action.name());
        ActionEnum action = ActionEnum.get(inputAction);
        RequestDispatcher requestDispatcher = null;
        String url;
        
        System.out.println(action);
        switch (action) {
            case FAVOR:
                processAddFavor(request, response);
                break;
            case REMOVE_FAVOR: 
                processRemoveFavor(request, response);   
            default :
                System.out.println("Default");
                url = FAVOR_LIST_PAGE;
        }
        
        String requestPage = request.getParameter("page");
        WebPageEnum page = WebPageEnum.get(requestPage);
        
        switch (page) {
            case RENT_HOUSE_DETAIL:
                url = URLBuilderFactory.getURL(ServletEnum.RENT_HOUSE_DETAIL);
                break;
            case RENT_HOUSE_SEARCH:
                url = URLBuilderFactory.getURL(ServletEnum.RENT_HOUSE_SEARCH);
                break;
            case USER_FAVOR_RENT_HOUSE_LIST:
            default:
                url = FAVOR_LIST_PAGE;
        }
        
        requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }
    
    private void processAddFavor(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        User user = getUser(request);
        int houseID = InputValidation.getNumber(request.getParameter("houseID"));
        
        if(houseID != InputValidation.default_value.getValue()) 
        {
            user.addFavorHouse(houseID);
        }
    }
    
    private void processRemoveFavor(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        User user = getUser(request);
        int houseID = InputValidation.getNumber(request.getParameter("houseID"));
        
        if(houseID != InputValidation.default_value.getValue()) 
        {
            user.removeFavorHouse(houseID);
        }
    }
    
    private User getUser(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(AttributeEnum.USER.name());
        return user;
    }
}
