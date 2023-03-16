package entity.DAO;

import java.util.Map;
import java.util.Optional;

public interface IDao<T> {
    
    Optional<T> get(int id);
    
    Map<Integer, T> getAll();
    
    void insert(T obj);
    
    /**
     * Specified the Field and Value to be updated
     * @param id
     * @param updateField
     * @param updateValue 
     */
    void update(int id, String updateField, String updateValue);
    
    /**
     * This will follow the order of the parameter to update
     * @param id
     * @param updateValue : String array of Update Value
     */
    void update(int id, String... updateValue);
    
    void delete(int id);
}
