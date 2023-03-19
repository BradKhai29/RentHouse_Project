package entity.district;

import entity.DAO.BaseDAO;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DistrictDAO extends BaseDAO<District>{
    private static String SELECT_ALL = "SELECT * FROM District;";
    
    private Map<Integer, District> districtMap;

    @Override
    public Optional<District> get(int id) {
        District district = districtMap.get(id);
        if(district == null) return Optional.empty();
        
        //Return district if not null
        return Optional.of(districtMap.get(id));
    }

    @Override
    public Map<Integer, District> getAll() {
        if(districtMap == null) {
            System.out.println("Start loading data");
            districtMap = new HashMap<>();
            openQuery(SELECT_ALL);
            
            try {
                ResultSet resultSet = query.executeQuery();
                while(resultSet.next())
                {
                    int districtID = resultSet.getInt("districtID");
                    String districtName = resultSet.getString("districtName");
                    
                    District district = new District(districtID, districtName);
                    districtMap.put(districtID, district);
                }                
                System.out.println("Load District maps success!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            closeQuery();
        }
        
        return districtMap;
    }

    @Override
    public void insert(District district) {}

    @Override
    public void update(int id, String updateField, String updateValue) {}

    @Override
    public void update(District district) {}

    @Override
    public void delete(int id) {}

    @Override
    public Map<Integer, District> getAll(int id) {
        return new HashMap<>();
    }
}
