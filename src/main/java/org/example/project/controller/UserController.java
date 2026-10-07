package org.example.project.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.example.project.bean.User;
import org.example.project.dao.UserDAO;
import org.example.project.dao.impl.UserDAOImpl;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Engine (DAO) ka connection banaya taaki database ke functions call kar sakein
    private UserDAO userDAO = new UserDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Frontend form se ek hidden input aayega "action" naam ka, jis se pata chalega user kya karna chahta hai
        String action = request.getParameter("action");
        
        if ("register".equals(action)) {
            // 1. Form se registration ka data nikalna
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String role = "member"; // By default naya user member hi hoga

            // 2. Data ko User ke dabbe (Bean) mein pack karna
            User newUser = new User(0, fullName, email, password, phone, role);
            
            // 3. DAO ke through database mein save karna
            boolean isRegistered = userDAO.registerUser(newUser);
            
            // 4. Result ke hisaab se page change karna
            if (isRegistered) {
                response.sendRedirect("login.jsp?msg=registered"); // Success: Login page par bhejo
            } else {
                response.sendRedirect("register.jsp?error=failed"); // Fail: Wapas register page par bhejo
            }
            
        } else if ("login".equals(action)) {
            // 1. Form se login ka data nikalna
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            // 2. Database mein email/password check karna
            User loggedInUser = userDAO.loginUser(email, password);
            
            if (loggedInUser != null) {
                // 1. X-Ray: Console mein print karke dekhna
                System.out.println("==== DEBUGGING LOGIN ====");
                System.out.println("Name: " + loggedInUser.getFullName());
                System.out.println("Role inside DB: '" + loggedInUser.getRole() + "'");
                
                // 2. Session set karna
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", loggedInUser);

                // 3. Null and Space safety (Space hatane ke liye trim lagaya)
                String userRole = "";
                if (loggedInUser.getRole() != null) {
                    userRole = loggedInUser.getRole().trim();
                }

                // 4. Checking Role
                if (userRole.equalsIgnoreCase("admin")) {
                    System.out.println("Boss Detected! Sending to AdminController...");
                    response.sendRedirect("AdminController");
                } else {
                    System.out.println("Member Detected! Sending to WorkoutController...");
                    response.sendRedirect("WorkoutController");
                }
                System.out.println("=========================");
            } else {
                response.sendRedirect("login.jsp?error=invalid");
            }
        }
    }
 // Naya method: Get requests (links) handle karne ke liye
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("logout".equals(action)) {
            // 1. Current user ka session memory dhoondo
            HttpSession session = request.getSession(false);
            
            if (session != null) {
                // 2. Memory ko permanently destroy kar do
                session.invalidate();
            }
            
            // 3. User ko securely login page par bhej do ek message ke sath
            response.sendRedirect("login.jsp?msg=loggedout");
        }
    }
}