package org.example.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.project.bean.User;
import org.example.project.dao.UserDAO;
import org.example.project.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    // 1. REGISTER METHOD
    @Override
    public boolean registerUser(User user) {
        boolean isRegistered = false;
        // Yahan fullName ko full_name kiya gaya hai taaki naye users bhi theek se register hon
        String query = "INSERT INTO users (full_name, email, password, phone) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getPhone());
            
            int rowCount = pstmt.executeUpdate();
            if (rowCount > 0) {
                isRegistered = true;
            }
        } catch (SQLException e) {
            System.out.println("Register Error: " + e.getMessage()); // Error ab saaf dikhega
        }
        return isRegistered;
    }

    // 2. LOGIN METHOD
    @Override
    public User loginUser(String email, String password) {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                // Yahan full_name kiya gaya hai
                user.setFullName(rs.getString("full_name")); 
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role")); 
            }
        } catch (SQLException e) {
            System.out.println("Login Error: " + e.getMessage());
        }
        return user; // Agar column names theek honge tabhi poora user banega
    }

    // 3. GET ALL USERS METHOD
    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                // Yahan full_name kiya gaya hai taaki Admin panel theek se list dikhaye
                user.setFullName(rs.getString("full_name")); 
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Get All Users Error: " + e.getMessage());
        }
        return userList;
    }
}