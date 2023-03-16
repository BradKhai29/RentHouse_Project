package entity.user;

import java.sql.ResultSet;
import java.util.Map;
import java.util.Optional;

import entity.DAO.BaseDAO;
import java.util.HashMap;

public class UserDAO extends BaseDAO<User> {

    private static final String UserTable = "InAppUser";
    private static final String REGISTER_NEW_USER = "INSERT INTO " + UserTable + " (username, password, fullname, phoneNumber, gender, userRole)"
            + "VALUES (?, ?, ?, ?, ?, ?);";
    private static final String LOGIN_USER = "SELECT * FROM " + UserTable + " WHERE username = ?";
    private static final String CHECK_EXIST_USERNAME = "SELECT userID FROM " + UserTable + " WHERE username = ?";
    private static String UPDATE_PASSWORD = "UPDATE " + UserTable + " \n"
            + "SET password = ? \n"
            + "WHERE userID = ?;";

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
    public Optional<User> get(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<Integer, User> getAll() {
        // TODO Auto-generated method stub
        return null;
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
    
    @Override
    public void update(User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Map<Integer, User> getAll(int id) {
        return new HashMap<>();
    }
}
