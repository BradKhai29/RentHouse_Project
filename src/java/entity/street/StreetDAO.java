package entity.street;

import entity.DAO.BaseDAO;
import entity.district.District;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StreetDAO extends BaseDAO<Street> {
    private static String SELECT_ALL = "SELECT * FROM Street;";
    private Map<Integer, Street> streetMap;

    @Override
    public Optional<Street> get(int id) {
        Street street = streetMap.get(id);
        if(street == null) return Optional.empty();
        
        //Return district if not null
        return Optional.of(streetMap.get(id));
    }

    @Override
    public Map<Integer, Street> getAll() {
        if(streetMap == null) {
            streetMap = new HashMap<>();
            openQuery(SELECT_ALL);
            
            try {
                ResultSet resultSet = query.executeQuery();
                while(resultSet.next())
                {
                    int streetID = resultSet.getInt("streetID");
                    int districtID = resultSet.getInt("districtID");
                    String streetName = resultSet.getString("streetName");
                    
                    Street street = new Street(streetID, districtID, streetName);
                    streetMap.put(streetID, street);
                }                
                System.out.println("Load Street maps success!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            closeQuery();
        }
        
        return streetMap;
    }
    
    /**
     * Get all street entities belong to input districtID
     * @param districtID
     * @return 
     */
    public Map<Integer, Street> getAll(int districtID) {
        if(streetMap == null) getAll();
        Map<Integer, Street> resultStreetMap = new HashMap<>();
        
        streetMap.forEach((streetID, streetEntity) -> {
            if(streetEntity.getDistrictID() == districtID) resultStreetMap.put(streetID, streetEntity);
        }
        );
        return resultStreetMap;
    }

    @Override
    public void insert(Street obj) {}

    @Override
    public void update(int id, String updateField, String updateValue) {}

    @Override
    public void update(Street t) {}

    @Override
    public void delete(int id) {}
}
