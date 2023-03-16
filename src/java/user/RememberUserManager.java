package controller.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import entity.user.User;

public final class RememberUserManager {
    private static final Map<String, User> rememberUsers;
    
    static {
        rememberUsers = new HashMap<>();
    }
    
    public static Optional<User> get(String hashCode)
    {
        //Init as empty optional
        Optional<User> customerOptional = Optional.empty();
        User rememberUser = rememberUsers.get(hashCode);
        
        boolean isExist = rememberUser != null;
        if(isExist) {
            System.out.println("Get User from key : ["  + hashCode + "]");
            customerOptional = Optional.of(rememberUser);
        }
        
        return customerOptional;
    }
    
    public static void add(String hashCode, User rememberUser)
    {
        if(!rememberUsers.containsKey(hashCode)) 
        {
            System.out.println("Add User with key : ["  + hashCode + "]");
            rememberUsers.put(hashCode, rememberUser);
        }
    }
    
    public static boolean remove(String hashCode)
    {
        boolean removeSuccess = false;
        
        if(rememberUsers.containsKey(hashCode)) 
        {
            rememberUsers.remove(hashCode);
            removeSuccess = true;
            System.out.println("Remove User with key : ["  + hashCode + "]");
        }
        
        return removeSuccess;
    }
}

