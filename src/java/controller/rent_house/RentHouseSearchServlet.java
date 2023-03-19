package controller.rent_house;

import entity.district.District;
import entity.rent_house.RentHouse;
import entity.street.Street;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import support_enum.AttributeEnum;
import support_enum.FolderEnum;
import support_enum.WebPageEnum;
import webpage_tools.PrintTools;
import webpage_tools.URLBuilderFactory;

@WebServlet(name = "RentHouseSearchServlet", urlPatterns = {"/rent_house/search"})
public class RentHouseSearchServlet extends HttpServlet {
    private static final String RENT_HOUSE_SEARCH_PAGE = URLBuilderFactory
                                                        .get()
                                                        .addFolder(FolderEnum.RENT_HOUSE)
                                                        .addPage(WebPageEnum.RENT_HOUSE_SEARCH)
                                                        .getURL();
    
    //default Selected Value : in situation when user not selected any different district
    private static int defaultSelectedValue = 0;
    private Map<Integer, District> selectedDistrictMap;
    private Map<Integer, Street> selectedStreetMap;
    private List<RentHouse> selectedRentHouseList;

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
        System.out.println("Get served at [" + getServletName() + "]");
        String[] inputDistrictIDs = request.getParameterValues("districtID");
        ServletContext application = request.getServletContext();    
        
        selectedDistrictMap = getSelectedDistrictMap(request);
        selectedStreetMap = getSelectedStreetMap(request);
        selectedRentHouseList = getSelectedRentHouseList(request);
        Map<Integer, RentHouse> rentHouseMap = (Map)application.getAttribute(AttributeEnum.rentHouseMap.name());
        
        if(selectedDistrictMap.isEmpty()) {
            Map<Integer, District> districtMap = (Map)application.getAttribute(AttributeEnum.districtMap.name());
            selectedDistrictMap.putAll(districtMap);
        }
        
        RequestDispatcher requestDispatcher;
        boolean arrayIsNull = inputDistrictIDs == null;
        if (arrayIsNull) 
        {
            if(selectedRentHouseList.isEmpty()) 
            {
                rentHouseMap.forEach((houseID, rentHouse) -> selectedRentHouseList.add(rentHouse));
            }
        }
        else {
            //Reset districts as not selected
            selectedDistrictMap.forEach((districtID, district) -> {
                district.setSelected(false);
            });
            
            Stream.of(inputDistrictIDs).forEach(districtID -> {
                District selectedDistrict = selectedDistrictMap.get(Integer.parseInt(districtID));
                selectedDistrict.setSelected(true);
                
                PrintTools.print(selectedDistrict);
            });
            
            rentHouseMap.forEach((houseID, rentHouse) -> {
                int districtID = rentHouse.getDistrictID();
                if(selectedDistrictMap.get(districtID).isSelected())
                {
                    if(selectedRentHouseList.contains(rentHouse) == false)
                    {
                        selectedRentHouseList.add(rentHouse);
                    }
                }
                else selectedRentHouseList.remove(rentHouse);
            });
            
            request.getSession().setAttribute(AttributeEnum.selectedRentHouseList.name(), selectedRentHouseList);
        }
        requestDispatcher = request.getRequestDispatcher(RENT_HOUSE_SEARCH_PAGE);
        requestDispatcher.forward(request, response);
    }
    
    /**
     * Get the map contains all selected districts when user searching
     * <br>if map is null, create new one
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    private Map<Integer, District> getSelectedDistrictMap (HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Integer, District> selectedMap = (Map)session.getAttribute(AttributeEnum.selectedDistrictMap.name());
        if(selectedMap == null) {
            System.out.println("Found null selected district map");
            selectedMap = new HashMap<>();
            session.setAttribute(AttributeEnum.selectedDistrictMap.name(), selectedMap);
        }
        return selectedMap;
    }
    
    /**
     * Get the map contains all selected streets when user searching
     * <br>if map is null, create new one
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    private Map<Integer, Street> getSelectedStreetMap (HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Integer, Street> selectedMap = (Map)session.getAttribute(AttributeEnum.selectedStreetMap.name());
        if(selectedMap == null) {
            System.out.println("Found null selected street map");
            selectedMap = new HashMap<>();
            session.setAttribute(AttributeEnum.selectedStreetMap.name(), selectedMap);
        }
        return selectedMap;
    }
    
    /**
     * Get the map contains all selected rent houses when user searching
     * <br>if map is null, create new one
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    private List<RentHouse> getSelectedRentHouseList (HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<RentHouse> selectedList = (List)session.getAttribute(AttributeEnum.selectedRentHouseList.name());
        if(selectedList == null) {
            System.out.println("Found null selected rent house map");
            selectedList = new ArrayList<>();
            session.setAttribute(AttributeEnum.selectedRentHouseList.name(), selectedList);
        }
        return selectedList;
    }
    
    private <V>  Map<Integer, V> getSelectedMap(HttpServletRequest request, AttributeEnum inputEnum) {
        HttpSession session = request.getSession();
        Map<Integer, V> selectedMap = (Map)session.getAttribute(inputEnum.name());
        if(selectedMap == null) {
            System.out.println("Found null [" + inputEnum + "]");
            selectedMap = new HashMap<>();
            session.setAttribute(AttributeEnum.selectedRentHouseList.name(), selectedMap);
        }
        return selectedMap;
    }
    
    private void tes(){
        
    }
}
