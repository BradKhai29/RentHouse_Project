package entity.FactoryDAO;

import entity.DAO.BaseDAO;
import entity.comment.Comment;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommentDAO extends BaseDAO<Comment>{
    private static String CommentTable = "Comment";
    private Map<Integer, Comment> commentMap;
    
    private static final String SELECT_ALL = "SELECT * FROM " + CommentTable;
    private static final String INSERT_NEW_COMMENT = "INSERT INTO " + CommentTable + " (commentID, starRate, details, userID, houseID, createdAt) "
                                                    + "VALUES (?, ?, ?, ?, ?, GETDATE());";
    private static final String DELETE_COMMENT = "DELETE FROM " + CommentTable + " WHERE commentID = ?";
    private static final String UPDATE_COMMENT = "UPDATE " + CommentTable
                                                + " SET starRate = ?, details = ?, createdAt = GETDATE()"
                                                + " WHERE commentID = ?;";
    
    @Override
    public Optional<Comment> get(int id) {
        Comment comment = commentMap.get(id);
        return (comment == null) ? Optional.empty() : Optional.of(comment);
    }

    @Override
    public Map<Integer, Comment> getAll() {
        if(commentMap == null) {
            commentMap = new HashMap<>();
            openQuery(SELECT_ALL);
            
            try {
                ResultSet resultSet = query.executeQuery();                
                while(resultSet.next()) 
                {
                    int commentID = resultSet.getInt("commentID");
                    int starRate = resultSet.getInt("starRate");
                    String details = resultSet.getString("details");
                    int userID = resultSet.getInt("userID");
                    int houseID = resultSet.getInt("houseID");
                    
                    Comment comment = new Comment(commentID, starRate, details, userID, houseID);
                    commentMap.put(commentID, comment);
                }
                System.out.println("Load Comment success");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            closeQuery();
        }
        
        return commentMap;
    }

    @Override
    public Map<Integer, Comment> getAll(int houseID) {
        if(commentMap == null) getAll();
        
        System.out.println("Get all with house [" + houseID + "]");
        Map<Integer, Comment> resultMap = new HashMap<>();
        commentMap.forEach(((commentID, comment) -> {
            if(comment.getHouseID() == houseID) resultMap.put(commentID, comment);
        }));
        
        return resultMap;
    }

    /**
     * Insert new Comment into database with order: starRate, details, userID, houseID
     * @param comment 
     */
    @Override
    public void insert(Comment comment) {
        openQuery(INSERT_NEW_COMMENT);
        
        try {
            int commentID = comment.hashCode();
            query.setInt(1, commentID);
            query.setInt(2, comment.getStarRate());
            query.setString(3, comment.getDetails());
            query.setInt(4, comment.getUserID());
            query.setInt(5, comment.getHouseID());
            query.executeUpdate();
            
            System.out.println("Insert comment success");
            commentMap.put(commentID, comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
    }

    @Override
    public void update(int id, String updateField, String updateValue) {
        
    }

    @Override
    public void update(Comment comment) {
        openQuery(UPDATE_COMMENT);
        
        try {
            query.setInt(1, comment.getStarRate());
            query.setString(2, comment.getDetails());
            query.setInt(3, comment.getCommentID());
            query.executeUpdate();
            
            System.out.println("Update comment success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
    }

    @Override
    public void delete(int id) {
        openQuery(DELETE_COMMENT);
        
        try {
            query.setInt(1, id);
            query.executeUpdate();
            
            System.out.println("Delete comment success");
            commentMap.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        closeQuery();
    }
}
