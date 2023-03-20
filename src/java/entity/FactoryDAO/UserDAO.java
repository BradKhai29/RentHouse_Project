package entity.FactoryDAO;

import java.sql.ResultSet;
import java.util.Map;
import java.util.Optional;

import entity.DAO.BaseDAO;
import entity.user.User;
import java.util.HashMap;

public class UserDAO extends BaseDAO<User> {    
    private static final String UserTable = "InAppUser";
    private static final String FavorTable = "FavorRentHouse";
    private static final String REGISTER_NEW_USER = "INSERT INTO " + UserTable + " (username, password, fullname, phoneNumber, gender, userRole)"
            + "VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ALL = "SELECT * FROM " + UserTable;
    private static final String LOGIN_USER = "SELECT * FROM " + UserTable + " WHERE username = ?";
    private static final String CHECK_EXIST_USERNAME = "SELECT userID FROM " + UserTable + " WHERE username = ?";
    private static String UPDATE_INFO = "UPDATE " + UserTable + " \n"
            + "SET fullname = ?, phoneNumber = ?, gender = ?, password = ? \n"
            + "WHERE userID = ?;";
    private static String GET_FAVOR_RENT_HOUSE_LIST = "SELECT houseID FROM " + FavorTable + " WHERE userID = ?";
    private static String ADD_FAVOR_RENT_HOUSE = "INSERT INTO " + FavorTable + "(userID, houseID) VALUES (?, ?);";
    private static String REMOVE_FAVOR_RENT_HOUSE = "DELETE FROM " + FavorTable + " WHERE (userID = ?) AND (houseID = ?);";
    
    //In app usage map
    private Map<Integer, User> userMap;
    
    public Optional<User> Authenticate(String inputUserName, String inputPassword) {
        //Declare userOptional as empty optional
        Optional<User> userOptional = Optional.empty();
        if (inputUserName == null || inputPassword == null) {
            return userOptional;
        }

        openQuery(LOGIN_USER);

        try {
            query.setString(1, inputUserName);

            ResultSet resultSet = query.executeQuery();
            User user = User.empty();

            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String fullname = resultSet.getString("fullname");
                boolean gender = resultSet.getBoolean("gender");
                boolean userRole = resultSet.getBoolean("userRole");
                String phoneNumber = resultSet.getString("phoneNumber");

                user = new User(userID, username, password, fullname, gender, phoneNumber, userRole);
            }

            boolean isValid = user.getPassword().equals(inputPassword);

            //If valid, optional will include the customer
            if (isValid) {
                userOptional = Optional.of(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeQuery();
        return userOptional;
    }

    @Override
    public Optional<User> get(int providerID) {
        System.out.println("Provider : " + providerID);
        if(userMap == null) getAll();
        User user = userMap.get(providerID);
        return (user == null) ? Optional.empty() : Optional.of(user);
    }

    @Override
    public Map<Integer, User> getAll() {
        if (userMap == null) {
            userMap = new HashMap<>();
            openQuery(SELECT_ALL);
            
            try {
                ResultSet resultSet = query.executeQuery();
                
                while(resultSet.next()) 
                {
                    int userID = resultSet.getInt("userID");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String fullname = resultSet.getString("fullname");
                    boolean gender = resultSet.getBoolean("gender");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    boolean userRole = resultSet.getBoolean("userRole");
                    
                    User user = new User(userID, username, password, fullname, gender, phoneNumber, userRole);
                    userMap.put(userID, user);
                }
                System.out.println("Load user database success");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            closeQuery();
        }
        return userMap;
    }

    @Override
    public void insert(User user) {
        openQuery(REGISTER_NEW_USER);

        try {
            //Getting datas
            String username = user.getUsername();
            String password = user.getPassword();
            String fullname = user.getFullname();
            String phoneNumber = user.getPhoneNumber();
            boolean gender = user.getGender();
            boolean userRole = user.getUserRole();

            //insert into query
            query.setString(1, username);
            query.setString(2, password);
            query.setString(3, fullname);
            query.setString(4, phoneNumber);
            query.setBoolean(5, gender);
            query.setBoolean(6, userRole);

            query.executeUpdate();
            System.out.println("Register [" + username + "] success");
        } catch (Exception e) {
            e.printStackTrace();
        }

        closeQuery();
    }

    public boolean isExistUsername(String inputUsername) {
        boolean isExist = false;
        openQuery(CHECK_EXIST_USERNAME);

        try {
            query.setString(1, inputUsername);
            ResultSet resultset = query.executeQuery();

            while (resultset.next() && !isExist) {
                isExist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isExist;
    }

    @Override
    public void update(int id, String updateField, String updateValue) {
        // TODO Auto-generated method stub

    }
    
    //fullname = ?, phoneNumber = ?, gender = ?, password = ?
    @Override
    public void update(User user) {
        openQuery(UPDATE_INFO);
        
        try {
            query.setString(1, user.getFullname());
            query.setString(2, user.getPhoneNumber());
            query.setBoolean(3, user.getGender());
            query.setString(4, user.getPassword());
            query.setInt(5, user.getUserID());
            query.executeUpdate();
            
            System.out.println("Update info at DAO success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Map<Integer, User> getAll(int userID) {
        openQuery(GET_FAVOR_RENT_HOUSE_LIST);
        Map<Integer, User> favorMap = new HashMap<>();
        try {
            query.setInt(1, userID);
            ResultSet resultSet = query.executeQuery();
            while(resultSet.next()) 
            {
                int houseID = resultSet.getInt("houseID");
                favorMap.put(houseID, null);
            }
            System.out.println("load favor map success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
        return favorMap;
    }
    
    public void AddFavorHouse(int userID, int houseID) {
        openQuery(ADD_FAVOR_RENT_HOUSE);
        
        try {
            query.setInt(1, userID);
            query.setInt(2, houseID);
            query.executeUpdate();
            
            System.out.println("Add favor success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
    }
    
    public void RemoveFavorHouse(int userID, int houseID) {
        openQuery(REMOVE_FAVOR_RENT_HOUSE);
        
        try {
            query.setInt(1, userID);
            query.setInt(2, houseID);
            query.executeUpdate();
            
            System.out.println("Remove favor success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
    }
}
