package entity.FactoryDAO;

import entity.DAO.IDao;
import entity.district.DistrictDAO;
import entity.rent_house.RentHouseDAO;
import entity.street.StreetDAO;
import entity.user.UserDAO;

public enum FactoryDAO {
    DISTRICT,
    STREET,
    RENT_HOUSE,
    USER;

    private static DistrictDAO districtDAO;
    private static StreetDAO streetDAO;
    private static RentHouseDAO rentHouseDAO;
    private static UserDAO userDAO;

    private static void loadDAO(FactoryDAO inputFactoryDAO) {
        switch (inputFactoryDAO) {
            case DISTRICT:
                if(districtDAO == null) districtDAO = new DistrictDAO();
                break;
            case STREET:
                if(streetDAO == null) streetDAO = new StreetDAO();
                break;
            case RENT_HOUSE:
                if(rentHouseDAO == null) rentHouseDAO = new RentHouseDAO();
                break;
            case USER:
                if(userDAO == null) userDAO = new UserDAO();
                break;
        }
    }
    
    public static IDao getDao(FactoryDAO inputFactoryDAO) {
        loadDAO(inputFactoryDAO);
        IDao resultDAO = null;
        switch (inputFactoryDAO) {
            case DISTRICT:
                resultDAO = districtDAO;
                break;
            case STREET:
                resultDAO =  streetDAO;
                break;
            case RENT_HOUSE:
                resultDAO =  rentHouseDAO;
                break;
            case USER:
                resultDAO = userDAO;
                break;
        } 
        return resultDAO;
    }
}
