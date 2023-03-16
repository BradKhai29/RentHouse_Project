package entity.street;

public class Street {
    //Database Attributes
    private int streetID;
    private int districtID;
    private String streetName;

    public Street() {
    }

    public Street(int streetID, int districtID, String streetName) {
        this.streetID = streetID;
        this.districtID = districtID;
        this.streetName = streetName;
    }

    public int getStreetID() {
        return streetID;
    }

    public void setStreetID(int streetID) {
        this.streetID = streetID;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }
}
