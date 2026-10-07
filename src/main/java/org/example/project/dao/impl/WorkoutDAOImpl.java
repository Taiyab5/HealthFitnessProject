package org.example.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.project.bean.Workout;
import org.example.project.dao.WorkoutDAO;
import org.example.project.util.DBConnection;

public class WorkoutDAOImpl implements WorkoutDAO {

    @Override
    public Workout getWorkoutByDay(String dayOfWeek) {
        Workout workout = null; // Shuru mein khali dabba
        
        // SQL Query: Sirf us din ka data laao jo form/button se aayega
        String query = "SELECT * FROM workouts WHERE day_of_week = ?";

        // Try-with-resources: Database connection open karo aur automatically close karo
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // '?' ki jagah par us din ka naam (jaise "Monday") set kar rahe hain
            pstmt.setString(1, dayOfWeek);
            
            // Database se data mangwana
            ResultSet rs = pstmt.executeQuery();

            // Agar database mein us din ka data mil gaya
            if (rs.next()) {
                workout = new Workout(); // Ek naya Workout dabba banaya
                
                // Database column se data nikal kar Java dabbe mein set kar rahe hain
                workout.setWorkoutId(rs.getInt("workout_id"));
                workout.setPlanName(rs.getString("plan_name"));
                workout.setDayOfWeek(rs.getString("day_of_week"));
                workout.setExercises(rs.getString("exercises"));
                workout.setTargetMuscle(rs.getString("target_muscle"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Agar error aaye toh console mein dikhaye
        }
        
        // Bhara hua dabba (ya null) wapas bhej do
        return workout;
    }
}
