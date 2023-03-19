package entity.rent_house;

import entity.DAO.BaseDAO;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RentHouseDAO extends BaseDAO<RentHouse>{
    private static final String RentHouseTable = "RentHouse";
    private static final String INSERT_RENT_HOUSE = "INSERT INTO " + RentHouseTable + " (houseName, imgURL, streetNumber, streetID, districtID, area, price, details, providerID, createdAt)"
                                                  + "VALUES (?, ?, 1, ?, ?, ?, ?, ?, ?, GETDATE());";
    private static final String SELECT_ALL  = "SELECT * FROM " + RentHouseTable + ";";
    private Map<Integer, RentHouse> rentHouseMap;
    
    @Override
    public Optional<RentHouse> get(int id) {
        RentHouse rentHouse = rentHouseMap.get(id);
        return (rentHouse == null) ? Optional.empty() : Optional.of(rentHouse);
    }

    @Override
    public Map<Integer, RentHouse> getAll() {
        if(rentHouseMap == null) {
            rentHouseMap = new HashMap<>();
            openQuery(SELECT_ALL);
        
            try {
                ResultSet resultSet = query.executeQuery();
                
                while(resultSet.next())
                {
                    int houseID = resultSet.getInt("houseID");
                    String houseName = resultSet.getString("houseName");
                    String imgURL = resultSet.getString("imgURL");
                    int streetNumber = resultSet.getInt("streetNumber");
                    int streetID = resultSet.getInt("streetID");
                    int districtID = resultSet.getInt("districtID");
                    int area = resultSet.getInt("area");
                    int price = resultSet.getInt("price");
                    String details = resultSet.getString("details");
                    int providerID = resultSet.getInt("providerID");
//                    Date createdAt = resultSet.getDate("createdAt");
                    
                    RentHouse rentHouse = new RentHouse(houseID, houseName, imgURL, details, streetID, streetNumber, districtID, area, price, providerID);
                    rentHouseMap.put(houseID, rentHouse);
                }
                System.out.println("Load RentHouse Success");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            closeQuery();
        }
        
        return rentHouseMap;
    }

    /**
     * Insert RentHouse wiht order : (houseName, imgURL, streetNumber
     * , streetID, districtID, area, price, details, providerID, createAt)"
     * @param rentHouse 
     */
    @Override
    public void insert(RentHouse rentHouse) {
        if(rentHouse == null) {
            System.out.println("NULL FOUND WHEN INSERT RENTHOUSE");
            return;
        }
        openQuery(INSERT_RENT_HOUSE);
        
        try {
            query.setString(1, rentHouse.getHouseName());
            query.setString(2, rentHouse.getImgURL());
            query.setInt(3, rentHouse.getStreetID());
            query.setInt(4, rentHouse.getDistrictID());
            query.setInt(5, rentHouse.getArea());
            query.setInt(6, rentHouse.getPrice());
            query.setString(7, rentHouse.getDetails());
            query.setInt(8, rentHouse.getProviderID());
            query.executeUpdate();
            
            System.out.println("Insert RentHouse success");
            rentHouseMap.put(rentHouse.getHouseID(), rentHouse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
    }

    @Override
    public void update(int id, String updateField, String updateValue) {
        
    }

    @Override
    public void update(RentHouse rentHouse) {
        
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<Integer, RentHouse> getAll(int providerID) {
        return new HashMap<>();
    }

}
