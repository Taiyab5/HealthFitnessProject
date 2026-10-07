 package org.example.project.dao;

import org.example.project.bean.User;
import java.util.List;

public interface UserDAO {
    
    // Naya user register karne ka method
    boolean registerUser(User user);
    
    // User ko login karwane ka method
    User loginUser(String email, String password); 
    List<User> getAllUsers();
}
