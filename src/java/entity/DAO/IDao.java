package entity.DAO;

import java.util.Map;
import java.util.Optional;

public interface IDao<T> {
    
    Optional<T> get(int id);
    
    Map<Integer, T> getAll();
    Map<Integer, T> getAll(int id);
    
    void insert(T obj);
    
    /**
     * Specified the Field and Value to be updated
     * @param id
     * @param updateField
     * @param updateValue 
     */
    void update(int id, String updateField, String updateValue);
    
    void update(T obj);
    
    void delete(int id);
}
