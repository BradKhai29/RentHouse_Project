package entity.district;

import entity.DAO.IDao;
import entity.FactoryDAO.FactoryDAO;
import entity.street.Street;
import java.util.Map;

public class District {
    //Database Attributes
    private int districtID;
    private String districtName;
    
    //InApp Support Attributes
    private Map<Integer, Street> streetMap;
    private boolean selected = false;

    public District() {}

    public District(int districtID, String districtName) {
        this.districtID = districtID;
        this.districtName = districtName;
    }

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Map<Integer, Street> getStreetMap() {
        if(streetMap == null) {
            IDao streetDao = FactoryDAO.getDao(FactoryDAO.STREET);
            streetMap = streetDao.getAll(this.districtID);
        }
        return streetMap;
    }

    public void setStreetMap(Map<Integer, Street> streetMap) {
        this.streetMap = streetMap;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("District: [");
        builder.append(districtID).append("] : [").append(districtName).append("]");
        return builder.toString();
    }
}
