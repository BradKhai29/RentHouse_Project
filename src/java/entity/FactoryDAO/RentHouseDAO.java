package entity.FactoryDAO;

import entity.DAO.BaseDAO;
import entity.rent_house.RentHouse;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import webpage_tools.PaginationTools;

public class RentHouseDAO extends BaseDAO<RentHouse> {

    private static final String RentHouseTable = "RentHouse";
    private static final String INSERT_RENT_HOUSE = "INSERT INTO " + RentHouseTable + " (houseName, imgURL, streetNumber, streetID, districtID, area, price, details, providerID, createdAt) "
                                                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE() );";
    private static final String SELECT_ALL = "SELECT * FROM " + RentHouseTable + ";";
    private static final String GET_RECENT_HOUSEID = "SELECT TOP 1 houseID FROM " + RentHouseTable + " ORDER BY houseID DESC;";
    private static final String UPDATE_RENT_HOUSE = "UPDATE " + RentHouseTable + " SET houseName = ?,"
            + " streetID = ?, districtID = ?, area = ?, price = ?, details = ?, createdAt = GETDATE() "
            + "WHERE houseID = ?";
    private static final String DELETE_RENTHOUSE = "DELETE FROM " + RentHouseTable + " WHERE houseID = ?";

    private Map<Integer, RentHouse> rentHouseMap;

    @Override
    public Optional<RentHouse> get(int id) {
        RentHouse rentHouse = rentHouseMap.get(id);
        return (rentHouse == null) ? Optional.empty() : Optional.of(rentHouse);
    }

    @Override
    public Map<Integer, RentHouse> getAll() {
        if (rentHouseMap == null) {
            rentHouseMap = new HashMap<>();
            openQuery(SELECT_ALL);

            try {
                ResultSet resultSet = query.executeQuery();
                int houseID = 1;
                while (resultSet.next()) {
                    houseID = resultSet.getInt("houseID");
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
                    rentHouse.setItemNo(PaginationTools.generate());
                    rentHouseMap.put(houseID, rentHouse);
                }

                PaginationTools.setLastHouseID(houseID);
                System.out.println("Load RentHouse Success");
            } catch (Exception e) {
                e.printStackTrace();
            }

            closeQuery();
        }

        return rentHouseMap;
    }

    /**
     * INSERT INTO " + RentHouseTable + " (houseName, imgURL, streetNumber,
     * streetID, districtID, area, price, details, providerID, createdAt)" +
     * "VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , GETDATE());";
     *
     * @param rentHouse
     */
    @Override
    public void insert(RentHouse rentHouse) {
        if(rentHouse != null) {
            openQuery(INSERT_RENT_HOUSE);
            
            try {
                PreparedStatement statement = DBConnection.prepareStatement(INSERT_RENT_HOUSE);
                int houseID = getLastHouseID() + 1;
                statement.setString(1, rentHouse.getHouseName());
                statement.setString(2, "");
                statement.setInt(3, new Random().nextInt(1, 100));
                statement.setInt(4, rentHouse.getStreetID());
                statement.setInt(5, rentHouse.getDistrictID());
                statement.setInt(6, rentHouse.getArea());
                statement.setInt(7, rentHouse.getPrice());
                statement.setString(8, rentHouse.getDetails());
                statement.setInt(9, rentHouse.getProviderID());
                statement.execute();
                
                System.out.println("Add new house success");
                rentHouse.setHouseID(houseID);
                rentHouseMap.put(houseID, rentHouse);
                rentHouse.setItemNo(PaginationTools.generate());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            closeQuery();
        }
    }

    public int getLastHouseID() {
        openQuery(GET_RECENT_HOUSEID);
        int lastHouseID = 0;
        try {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                lastHouseID = resultSet.getInt("houseID");
                break;
            }
            System.out.println("Get [" + lastHouseID + "] success");
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeQuery();
        return lastHouseID;
    }

    @Override
    public void update(int id, String updateField, String updateValue) {

    }

    /*SET houseName = ?,"
            + " streetID = ?, districtID = ?, area = ?, price = ?, details = ?, createdAt = GETDATE() "
            + "WHERE houseID = ?"
     */
    @Override
    public void update(RentHouse rentHouse) {
        openQuery(UPDATE_RENT_HOUSE);

        try {
            query.setString(1, rentHouse.getHouseName());
            query.setInt(2, rentHouse.getStreetID());
            query.setInt(3, rentHouse.getDistrictID());
            query.setInt(4, rentHouse.getArea());
            query.setInt(5, rentHouse.getPrice());
            query.setString(6, rentHouse.getDetails());
            query.setInt(7, rentHouse.getHouseID());
            query.executeUpdate();

            System.out.println("Update rentHouse" + rentHouse + " success");
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeQuery();
    }

    @Override
    public void delete(int houseID) {
        openQuery(DELETE_RENTHOUSE);

        try {
            query.setInt(1, houseID);
//            query.executeUpdate();

            rentHouseMap.remove(houseID);

            PaginationTools.resetCnt();
            rentHouseMap.forEach(((id, rentHouse) -> {
                rentHouse.setItemNo(PaginationTools.generate());
            }));

            System.out.println("Delete house success");
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeQuery();
    }

    @Override
    public Map<Integer, RentHouse> getAll(int providerID) {
        return new HashMap<>();
    }

}
