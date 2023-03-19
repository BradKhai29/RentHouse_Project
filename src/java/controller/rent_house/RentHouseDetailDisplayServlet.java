package controller.rent_house;

import controller.validation.InputValidation;
import entity.FactoryDAO.FactoryDAO;
import entity.rent_house.RentHouse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import support_enum.*;
import webpage_tools.URLBuilder;
import webpage_tools.URLBuilderFactory;

@WebServlet(name = "RentHouseDetailDisplayServlet", urlPatterns = "/rent_house/detail")
public class RentHouseDetailDisplayServlet extends HttpServlet {

    private static URLBuilder urlBuilder = URLBuilderFactory.get();
    private static final String HOMEPAGE = URLBuilderFactory.getURL(ServletEnum.HOME);
    private static final String RENT_HOUSE_DETAIL_PAGE = urlBuilder
            .addFolder(FolderEnum.RENT_HOUSE)
            .addPage(WebPageEnum.RENT_HOUSE_DETAIL)
            .getURL();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RentHouse rentHouse = (RentHouse)session.getAttribute(AttributeEnum.rentHouse.name());
        if (rentHouse != null) {
            request.getRequestDispatcher(RENT_HOUSE_DETAIL_PAGE).forward(request, response);
        }
        else request.getRequestDispatcher(HOMEPAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String inputHouseID = request.getParameter("houseID");
        HttpSession session = request.getSession();
        ServletContext application  = request.getServletContext();

        int houseID = InputValidation.getNumber(inputHouseID);

        if (houseID == InputValidation.default_value.getValue()) {
            session.setAttribute(ErrorEnum.PARAMETER_ERROR.name(), "");
            System.out.println("Invalid houseID");
            request.getRequestDispatcher(HOMEPAGE).forward(request, response);
        } else {
            session.setAttribute(AttributeEnum.houseID.name(), houseID);
            System.out.println("Getting houseId = " + houseID);
            
            Map<Integer, RentHouse> rentHouseMap = (Map)application.getAttribute(AttributeEnum.rentHouseMap.name());
            RentHouse rentHouse = rentHouseMap.get(houseID);

            if (rentHouse == null) {
                request.getRequestDispatcher(HOMEPAGE).forward(request, response);
            }
            else {
                session.setAttribute(AttributeEnum.rentHouse.name(), rentHouse);
                request.getRequestDispatcher(RENT_HOUSE_DETAIL_PAGE).forward(request, response);
            }
            
        }
    }
}
