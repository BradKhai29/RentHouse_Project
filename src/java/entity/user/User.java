package entity.user;

public final class User {

    private int userID;
    private String username;
    private String password;
    private String fullname;
    private boolean gender;
    private String phoneNumber;
    //userRole = true : RentHouse provider
    private boolean userRole;

    public User() {
    }

    public static User empty() {
        return new User(0, "", "", "", true, "", false);
    }

    public User(int userID, String username, String password, String fullname, boolean gender, String phoneNumber,
            boolean userRole) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getUserRole() {
        return userRole;
    }

    public void setUserRole(boolean userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Username [");
        builder.append(username).append("] : [").append(userID).append("]");
        return builder.toString();
    }
}
