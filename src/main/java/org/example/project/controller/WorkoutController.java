package org.example.project.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.example.project.bean.Workout;
import org.example.project.dao.WorkoutDAO;
import org.example.project.dao.impl.WorkoutDAOImpl;

// Nutrition ki files import kar rahe hain
import org.example.project.bean.Nutrition;
import org.example.project.dao.NutritionDAO;
import org.example.project.dao.impl.NutritionDAOImpl;

@WebServlet("/WorkoutController")
public class WorkoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Dono engines (Workout aur Diet) ko start kar rahe hain
    private WorkoutDAO workoutDAO = new WorkoutDAOImpl();
    private NutritionDAO nutritionDAO = new NutritionDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. Security Check
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 2. Din (Day) pata karna
        String day = request.getParameter("day");
        if (day == null || day.trim().isEmpty()) {
            day = "Monday";
        }

        // 3. Database se Workout aur Diet DONO uthana
        Workout workoutPlan = workoutDAO.getWorkoutByDay(day);
        Nutrition nutritionPlan = nutritionDAO.getNutritionByDay(day);

        // 4. Dono ko request dabbe mein pack karna
        request.setAttribute("currentWorkout", workoutPlan);
        request.setAttribute("currentNutrition", nutritionPlan);
        request.setAttribute("selectedDay", day);

        // 5. Dashboard par bhej dena
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}