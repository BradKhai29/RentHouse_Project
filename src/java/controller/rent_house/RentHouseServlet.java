package controller.rent_house;

import controller.validation.InputValidation;
import entity.DAO.IDao;
import entity.FactoryDAO.FactoryDAO;
import entity.rent_house.RentHouse;
import entity.user.User;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import support_enum.ActionEnum;
import support_enum.AttributeEnum;
import support_enum.ErrorEnum;
import support_enum.FolderEnum;
import support_enum.ServletEnum;
import support_enum.WebPageEnum;
import webpage_tools.PaginationTools;
import webpage_tools.PrintTools;
import webpage_tools.URLBuilder;
import webpage_tools.URLBuilderFactory;

@WebServlet(name = "RentHouseServlet", urlPatterns = {"/user/rent_house"})
public class RentHouseServlet extends HttpServlet {
    private static final IDao<RentHouse> rentHouseDao = FactoryDAO.getDao(FactoryDAO.RENT_HOUSE);
    
    private static URLBuilder urlBuilder = URLBuilderFactory.get();
    private static final String HOMEPAGE = URLBuilderFactory.getURL(ServletEnum.HOME);
    private static final String RENT_HOUSE_CREATE_PAGE = urlBuilder
            .addFolder(FolderEnum.RENT_HOUSE)
            .addPage(WebPageEnum.CREATE_NEW_RENT_HOUSE)
            .getURL();
    private static final String RENT_HOUSE_UPDATE_PAGE = urlBuilder
            .addFolder(FolderEnum.RENT_HOUSE)
            .addPage(WebPageEnum.RENT_HOUSE_UPDATE_INFO)
            .getURL();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(AttributeEnum.STEP2.name());

        Object updateRentHouseCheckPoint = session.getAttribute(AttributeEnum.UPDATE_RENTHOUSE_CHECKPOINT.name());
        boolean haveCheckPoint = updateRentHouseCheckPoint != null;

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(RENT_HOUSE_CREATE_PAGE);
        if (haveCheckPoint) {
            requestDispatcher = request.getRequestDispatcher(RENT_HOUSE_UPDATE_PAGE);
        }

        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(PrintTools.getUTF8());
        String receiveAction = request.getParameter("action");
        ActionEnum action = ActionEnum.get(receiveAction);

        System.out.println("Action at RentHouse [" + action + "]");
        switch (action) {
            case CREATE:
                processCreateAction(request, response);
                break;
            case CONFIRM_CREATE:
                processConfirmCreate(request, response);
                break;
            case GET_UPDATE:
                RedirectToUpdatePage(request, response);
                break;
            case UPDATE:
                processUpdateAction(request, response);
                break;
            case CONFIRM_UPDATE:
                processConfirmUpdate(request, response);
                break;
            case DELETE:
                processDelete(request, response);
                break;
            default:
                processDefaultAction(request, response);
        }
    }

    /**
     * This process includes collects data from the input form and set it to
     * rent house obj
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processCreateAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding(PrintTools.getUTF8());
        System.out.println("Get to process create action");
        HttpSession session = request.getSession();

        String houseName = request.getParameter("houseName");
        String details = request.getParameter("details");
        String inputPrice = request.getParameter("price");
        String inputArea = request.getParameter("area");
        String inputDistrictID = request.getParameter("districtID");
        PrintTools.print(houseName + ":" + details + ":" + inputPrice + ":" + inputArea + ":" + inputDistrictID);
        try {
            int area = Integer.parseInt(inputArea);
            int districtID = Integer.parseInt(inputDistrictID);
            int price = Integer.parseInt(inputPrice);

            RentHouse rentHouse = RentHouse.empty();
            rentHouse.setHouseName(houseName);
            rentHouse.setDetails(details);
            rentHouse.setArea(area);
            rentHouse.setPrice(price);
            rentHouse.setDistrictID(districtID);

            session.setAttribute(AttributeEnum.createRentHouse.name(), rentHouse);
            session.setAttribute(AttributeEnum.STEP2.name(), "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(RENT_HOUSE_CREATE_PAGE);
        requestDispatcher.forward(request, response);
    }

    private void processConfirmCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User provider = (User) session.getAttribute(AttributeEnum.USER.name());

        //Checking if the user have enter information for rent house or not by this checkpoint
        Object createCheckPoint = session.getAttribute(AttributeEnum.STEP2.name());
        boolean haveCheckPoint = Objects.nonNull(createCheckPoint);

        if (haveCheckPoint) {
            System.out.println("Process at confirm create");
            RentHouse rentHouse = (RentHouse) session.getAttribute(AttributeEnum.createRentHouse.name());
            String streetID = request.getParameter("streetID");

            try {
                rentHouse.setStreetID(Integer.parseInt(streetID));
                rentHouse.setProviderID(provider.getUserID());

                //Insert renthouse into database
                IDao<RentHouse> rentHouseDao = FactoryDAO.getDao(FactoryDAO.RENT_HOUSE);
                rentHouseDao.insert(rentHouse);
                session.removeAttribute(AttributeEnum.createRentHouse.name());
            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(HOMEPAGE);
            requestDispatcher.forward(request, response);
        } else {
            //If check point not found, redirect user back to rent house page to input 
            //all needed detail
            session.setAttribute(AttributeEnum.STEP2.name(), "");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(RENT_HOUSE_CREATE_PAGE);
            requestDispatcher.forward(request, response);
        }
    }

    private void processDefaultAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(AttributeEnum.STEP2.name());
        System.out.println("Process default at RentHouse Servlet");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(RENT_HOUSE_CREATE_PAGE);
        requestDispatcher.forward(request, response);
    }

    private void RedirectToUpdatePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(RENT_HOUSE_UPDATE_PAGE);
        HttpSession session = request.getSession();
        int houseID = InputValidation.getNumber(request.getParameter("houseID"));
        System.out.println("Update houseID : " + houseID);
        
        Optional<RentHouse> renthouseOptional = FactoryDAO.getDao(FactoryDAO.RENT_HOUSE).get(houseID);
        RentHouse rentHouse = renthouseOptional.get();
        session.setAttribute(AttributeEnum.updateRentHouse.name(), rentHouse);
        
        requestDispatcher.forward(request, response);
    }

    /**
     * This process includes collects data from the input form and set it to
     * rent house obj
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processUpdateAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(RENT_HOUSE_UPDATE_PAGE);
        request.setCharacterEncoding(PrintTools.getUTF8());
        System.out.println("Get to process create action");
        HttpSession session = request.getSession();

        String houseName = request.getParameter("houseName");
        String details = request.getParameter("details");
        String inputPrice = request.getParameter("price");
        String inputArea = request.getParameter("area");
        String inputDistrictID = request.getParameter("districtID");
        PrintTools.print(houseName + ":" + details + ":" + inputPrice + ":" + inputArea + ":" + inputDistrictID);
        try {
            int area = Integer.parseInt(inputArea);
            int districtID = Integer.parseInt(inputDistrictID);
            int price = Integer.parseInt(inputPrice);

            RentHouse rentHouse = (RentHouse) session.getAttribute(AttributeEnum.updateRentHouse.name());
            rentHouse.setHouseName(houseName);
            rentHouse.setDetails(details);
            rentHouse.setArea(area);
            rentHouse.setPrice(price);
            rentHouse.setDistrictID(districtID);

            session.setAttribute(AttributeEnum.STEP2.name(), "");

        } catch (Exception e) {
            requestDispatcher = request.getRequestDispatcher(HOMEPAGE);
            request.setAttribute(ErrorEnum.UNKWOWN_ERROR.name(), ErrorEnum.UNKWOWN_ERROR.getMessage());
            e.printStackTrace();
        }

        requestDispatcher.forward(request, response);
    }

    private void processConfirmUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User provider = (User) session.getAttribute(AttributeEnum.USER.name());

        //Checking if the user have enter information for rent house or not by this checkpoint
        Object createCheckPoint = session.getAttribute(AttributeEnum.STEP2.name());
        boolean haveCheckPoint = Objects.nonNull(createCheckPoint);

        if (haveCheckPoint) {
            System.out.println("Process at confirm create");
            RentHouse rentHouse = (RentHouse) session.getAttribute(AttributeEnum.updateRentHouse.name());
            String streetID = request.getParameter("streetID");

            try {
                rentHouse.setStreetID(Integer.parseInt(streetID));
                rentHouse.setProviderID(provider.getUserID());

                //Insert renthouse into database
                IDao<RentHouse> rentHouseDao = FactoryDAO.getDao(FactoryDAO.RENT_HOUSE);
                rentHouseDao.update(rentHouse);
                session.removeAttribute(AttributeEnum.STEP2.name());
            } catch (Exception e) {
                request.setAttribute(ErrorEnum.UNKWOWN_ERROR.name(), ErrorEnum.UNKWOWN_ERROR.getMessage());
                e.printStackTrace();
            }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(RENT_HOUSE_UPDATE_PAGE);
            requestDispatcher.forward(request, response);
        } else {
            //If check point not found, redirect user back to rent house page to input 
            //all needed detail
            session.setAttribute(AttributeEnum.STEP2.name(), "");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(RENT_HOUSE_UPDATE_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
    
    private void processDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int houseID = InputValidation.getNumber(request.getParameter("houseID"));
        System.out.println("Delete houseID : " + houseID);
        
        if(houseID != InputValidation.default_value.getValue()) {
            Optional<RentHouse> renthouseOptional = rentHouseDao.get(houseID);
            
            if(renthouseOptional.isPresent()) {
                System.out.println("HouseId = " + houseID + " is present");
                rentHouseDao.delete(houseID);
            }
        }
        
        String homepage = urlBuilder.addPage(WebPageEnum.HOME).getURLWithContextPath();
        response.sendRedirect(homepage);
    }
}
