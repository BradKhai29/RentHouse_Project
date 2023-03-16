package entity.district;

import entity.street.Street;
import java.util.Map;

public class District {
    //Database Attributes
    private int districtID;
    private String districtName;
    
    //InApp Support Attributes
    private Map<Integer, Street> streetMaps;

    public District() {
    }

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

    public Map<Integer, Street> getStreetMaps() {
        if(streetMaps == null) {
            
        }
        return streetMaps;
    }

    public void setStreetMaps(Map<Integer, Street> streetMaps) {
        this.streetMaps = streetMaps;
    }
}
