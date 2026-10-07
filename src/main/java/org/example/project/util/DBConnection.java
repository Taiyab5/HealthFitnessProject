package org.example.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/gym_database";
    private static final String USER = "root"; // Yahan apna sahi MySQL username likhna
    private static final String PASS = "Taiyab@123"; // Yahan apna sahi MySQL password likhna
    
    // Naya method jo har baar fresh connection banayega
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver nahi mila!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection fail ho gaya!");
            e.printStackTrace();
        }
        return connection; // Har request ke liye naya connection jayega
    }
}