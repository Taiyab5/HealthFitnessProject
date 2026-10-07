package org.example.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.project.bean.Nutrition;
import org.example.project.dao.NutritionDAO;
import org.example.project.util.DBConnection;

public class NutritionDAOImpl implements NutritionDAO {

    @Override
    public Nutrition getNutritionByDay(String dayOfWeek) {
        Nutrition nutrition = null; // Shuru mein khali dabba

        // SQL Query: Sirf us specific din ka diet plan nikalna
        String query = "SELECT * FROM nutrition WHERE day_of_week = ?";

        // Try-with-resources: Naya connection banana aur automatically close karna
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, dayOfWeek);
            
            ResultSet rs = pstmt.executeQuery();

            // Agar database mein us din ka diet mil gaya
            if (rs.next()) {
                nutrition = new Nutrition(); // Khali dabba banaya
                
                // Text (String) data ko set karna
                nutrition.setNutritionId(rs.getInt("nutrition_id"));
                nutrition.setDayOfWeek(rs.getString("day_of_week"));
                nutrition.setBreakfast(rs.getString("breakfast"));
                nutrition.setLunch(rs.getString("lunch"));
                nutrition.setDinner(rs.getString("dinner"));
                
                // Numbers (INT) data ko set karna
                nutrition.setTotalCalories(rs.getInt("total_calories"));
                nutrition.setTotalProtein(rs.getInt("total_protein"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Error aane par console mein batayega
        }

        return nutrition; // Bhara hua khane ka dabba wapas bhej diya
    }
}