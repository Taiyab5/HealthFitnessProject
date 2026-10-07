package org.example.project.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import org.example.project.bean.User;
import org.example.project.dao.UserDAO;
import org.example.project.dao.impl.UserDAOImpl;

@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Engine load kar rahe hain
    private UserDAO userDAO = new UserDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        // Security Check 1: Bina login kiye koi is link par nahi aa sakta
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User currentUser = (User) session.getAttribute("currentUser");

        // Security Check 2: Sirf "admin" (Boss) is page ko dekh sakta hai. 
        // Agar normal user hack karke URL mein AdminController likhega, toh yeh usko wapas Workout page par bhej dega!
        if (!"admin".equals(currentUser.getRole())) {
            response.sendRedirect("WorkoutController"); 
            return;
        }

        // Agar check pass ho gaya (matlab aap Boss ho), toh database se sabki list nikal lo
        List<User> allUsers = userDAO.getAllUsers();
        request.setAttribute("userList", allUsers);

        // Data ko lekar Admin Dashboard par bhej do
        request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
    }
}