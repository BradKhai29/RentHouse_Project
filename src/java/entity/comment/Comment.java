package entity.comment;

import java.sql.Date;

public class Comment {
    private int commentID;
    private int starRate;
    private String details;
    private int userID;
    private int houseID;
//    private Date createdAt;

    public Comment() {
    }

    public Comment(int commentID, int starRate, String details, int userID, int houseID) {
        this.commentID = commentID;
        this.starRate = starRate;
        this.details = details;
        this.userID = userID;
        this.houseID = houseID;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getStarRate() {
        return starRate;
    }

    public void setStarRate(int starRate) {
        this.starRate = starRate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getHouseID() {
        return houseID;
    }

    public void setHouseID(int houseID) {
        this.houseID = houseID;
    }
}
