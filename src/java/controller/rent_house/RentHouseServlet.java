package controller.rent_house;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import support_enum.ActionEnum;
import support_enum.AttributeEnum;
import support_enum.FolderEnum;
import support_enum.WebPageEnum;
import webpage_tools.URLBuilder;
import webpage_tools.URLBuilderFactory;


@WebServlet(name="RentHouseServlet", urlPatterns={"/user/rent_house"})
public class RentHouseServlet extends HttpServlet {
    private static URLBuilder urlBuilder = URLBuilderFactory.get();
    private static final String RENT_HOUSE_CREATE_PAGE = urlBuilder
                                                        .addFolder(FolderEnum.RENT_HOUSE)
                                                        .addPage(WebPageEnum.CREATE_NEW_RENT_HOUSE)
                                                        .getURL();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String receiveAction = request.getParameter("action");
        ActionEnum action = ActionEnum.get(receiveAction);
        
        switch (action) {
            case CREATE:
                processCreateAction(request, response);
                break;
            case CONFIRM_CREATE:
                processConfirmCreate(request, response);
                break;
            case UPDATE:
                
                break;
            case CONFIRM_UPDATE:
                break;
            default:
                processDefaultAction(request, response);
        }
        
    }
    
    private void processCreateAction(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        Object CheckPoint = session.getAttribute(AttributeEnum.STEP1.name());
        boolean checkPointNotFound = Objects.isNull(CheckPoint);
        
        
        //If the request do not have CreateCheckPoint obj,
        //so that, user has just accessed the page without doing any action
        if(checkPointNotFound) {
            //Create the checkpoint to confirm that user access the page and prepare to input some info
            session.setAttribute(AttributeEnum.STEP1.name(), "");
        }
        //else if checkpoint found, so that, user has input some info want to input addition info such as Street name and street number
        else session.setAttribute(AttributeEnum.STEP2.name(), "");
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(RENT_HOUSE_CREATE_PAGE);
        requestDispatcher.forward(request, response);
    }
    
    private void processConfirmCreate(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        
        //Checking if the user have enter information for rent house or not by this checkpoint
        Object createCheckPoint = request.getParameter(AttributeEnum.STEP2.name());
        boolean haveCheckPoint = Objects.nonNull(createCheckPoint);
        
        if(haveCheckPoint) {
            
        }
        else {
            //If check point not found, redirect user back to rent house page to input 
            //all needed detail
            session.removeAttribute(AttributeEnum.STEP2.name());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(RENT_HOUSE_CREATE_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
    
    private void processDefaultAction(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException 
    {
        System.out.println("Process default at RentHouse Servlet");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(RENT_HOUSE_CREATE_PAGE);
        requestDispatcher.forward(request, response);
    }

}
