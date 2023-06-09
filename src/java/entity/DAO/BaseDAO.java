package entity.DAO;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class BaseDAO<T> implements IDao<T>{
    //DBConnection section
    protected static Connection DBConnection;
    protected static PreparedStatement query;
    protected String SQLQuery;
    
    /**
     * Open Connection to the Database
     */
    protected static void openConnection() {
        DBConnection = DatabaseConnection.getConnection();
    }

    /**
     * Close the current connection to the Database
     */
    protected static void closeConnection() {
        try {
            DBConnection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    /**
     * Open Connection and create a new PrepareStatement with given <span style="color:red">SQLQuery</span>
     * @param SQLQuery 
     */
    protected void openQuery(String SQLQuery) {
        openConnection();
        this.SQLQuery = SQLQuery;
        
        try {
            query = DBConnection.prepareStatement(SQLQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Close this Query and Connection
     * @param SQLQuery 
     */
    protected void closeQuery() {
        try {
            query.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeConnection();
    }
}
