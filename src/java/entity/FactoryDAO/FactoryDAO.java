package entity.FactoryDAO;

import entity.DAO.IDao;

public enum FactoryDAO {
    DISTRICT,
    STREET,
    RENT_HOUSE,
    USER,
    COMMENT;

    private static DistrictDAO districtDAO;
    private static StreetDAO streetDAO;
    private static RentHouseDAO rentHouseDAO;
    private static UserDAO userDAO;
    private static CommentDAO commentDAO;

    
    public static IDao getDao(FactoryDAO inputFactoryDAO) {      
        IDao resultDAO = null;
        switch (inputFactoryDAO) 
        {
            case DISTRICT:
                if(districtDAO == null) districtDAO = new DistrictDAO();
                resultDAO = districtDAO;
                break;
            case STREET:
                if(streetDAO == null) streetDAO = new StreetDAO();
                resultDAO = streetDAO;
                break;
            case RENT_HOUSE:
                if(rentHouseDAO == null) rentHouseDAO = new RentHouseDAO();
                resultDAO = rentHouseDAO;
                break;
            case USER:
                if(userDAO == null) userDAO = new UserDAO();
                resultDAO = userDAO;
                break;
            case COMMENT:
                if(commentDAO == null) commentDAO = new CommentDAO();
                resultDAO = commentDAO;
                break;
        } 
        return resultDAO;
    }
}
