package entity.rent_house;

import entity.DAO.IDao;
import entity.FactoryDAO.FactoryDAO;
import entity.comment.Comment;
import entity.district.District;
import entity.street.Street;
import entity.user.User;
import java.util.HashMap;
import java.util.Map;
import support_enum.FolderEnum;

public final class RentHouse {
    private int houseID;
    private String houseName;
    private String imgURL = "";
    private String details;
    private int streetID;
    private int streetNumber;
    private int districtID;
    private int price;
    private int area;
    private int providerID;
    
    //In App attribute
    private Map<Integer, Comment> commentMap;
    private String streetName;
    private String districtName;
    private User provider;
    private boolean haveNewComment = false;

    public RentHouse() {
    }
    
    public static RentHouse empty(){
        return new RentHouse();
    }

    public RentHouse(int houseID, String houseName, String imgURL, String details, int streetID, int streetNumber, int districtID, int price, int area, int providerID) {
        StringBuffer buffer = new StringBuffer(FolderEnum.RENT_HOUSE_IMAGES.getFolderName());
        this.houseID = houseID;
        this.houseName = houseName;
        this.imgURL = buffer.append(imgURL).toString();
        this.details = details;
        this.streetID = streetID;
        this.streetNumber = streetNumber;
        this.districtID = districtID;
        this.price = price;
        this.area = area;
        this.providerID = providerID;
    }
    
    public int getHouseID() {
        return houseID;
    }

    public void setHouseID(int houseID) {
        this.houseID = houseID;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = String.valueOf(details).trim();
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        StringBuffer buffer = new StringBuffer(FolderEnum.RENT_HOUSE_IMAGES.getFolderName());
        this.imgURL = buffer.append(imgURL).toString();
    }

    public int getStreetID() {
        return streetID;
    }

    public void setStreetID(int streetID) {
        this.streetID = streetID;
    }

    public String getStreetName() {
        if(streetName == null) {
            IDao<Street> streetDao = FactoryDAO.getDao(FactoryDAO.STREET);
            Street street = streetDao.get(this.streetID).get();
            streetName = street.getStreetName();
        }
        return streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

    public String getDistrictName() {
        if(districtName == null) {
            IDao<District> districtDao = FactoryDAO.getDao(FactoryDAO.DISTRICT);
            District district = districtDao.get(this.districtID).get();
            districtName = district.getDistrictName();
        }
        return districtName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
    
    public int getProviderID() {
        return providerID;
    }

    public void setProviderID(int providerID) {
        this.providerID = providerID;
    }

    public Map<Integer, Comment> getCommentMap() {
        if(commentMap == null || haveNewComment) {
            commentMap = new HashMap<>();
            IDao<Comment> commentDao = FactoryDAO.getDao(FactoryDAO.COMMENT);
            commentMap.putAll(commentDao.getAll(houseID));
        }
        return commentMap;
    }

    public boolean isHaveNewComment() {
        return haveNewComment;
    }

    public void setHaveNewComment(boolean haveNewComment) {
        this.haveNewComment = haveNewComment;
    }
    
    public void confirmComment() {
        haveNewComment = false;
    }

    public User getProvider() {
        if(provider == null) {
            IDao<User> userDao = FactoryDAO.getDao(FactoryDAO.USER);
            provider = userDao.get(providerID).get();
        }
        return provider;
    }

    public void setProvider(User provider) {
        this.provider = provider;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("House [id = ");
        builder.append(houseID).append("] : [").append(houseName).append("]");
        return builder.toString();
    }
}
