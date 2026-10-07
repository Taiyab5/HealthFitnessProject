<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.example.project.bean.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Boss Panel - Gym Members</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #2c3e50; margin: 0; padding: 20px; color: white; }
        .header { display: flex; justify-content: space-between; align-items: center; background: #1a252f; padding: 15px 30px; border-radius: 8px; }
        .logout-btn { background-color: #e74c3c; color: white; text-decoration: none; padding: 10px 15px; border-radius: 4px; font-weight: bold; }
        .logout-btn:hover { background-color: #c0392b; }
        
        .container { max-width: 1000px; margin: 30px auto; background: white; padding: 30px; border-radius: 8px; color: #333; box-shadow: 0 4px 15px rgba(0,0,0,0.3); }
        h3 { color: #2c3e50; font-size: 24px; border-bottom: 2px solid #3498db; padding-bottom: 10px; }
        
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #3498db; color: white; font-weight: bold; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        tr:hover { background-color: #f1f1f1; }
        
        .role-badge { padding: 6px 12px; border-radius: 12px; font-size: 12px; font-weight: bold; color: white; text-align: center; display: inline-block;}
        .admin { background-color: #e74c3c; } /* Boss ke liye Red badge */
        .member { background-color: #2ecc71; } /* Normal user ke liye Green badge */
    </style>
</head>
<body>

    <!-- Header Section -->
    <div class="header">
        <h2>👑 Boss Panel - Welcome ${currentUser.fullName}</h2>
        <a href="UserController?action=logout" class="logout-btn">Logout</a>
    </div>

    <!-- Table Section -->
    <div class="container">
        <h3>All Registered Gym Members</h3>
        
        <table>
            <thead>
                <tr>
                    <th>User ID</th>
                    <th>Full Name</th>
                    <th>Email Address</th>
                    <th>Phone Number</th>
                    <th>System Role</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    // Controller se bheja hua data yahan nikal rahe hain
                    List<User> userList = (List<User>) request.getAttribute("userList");
                    if(userList != null && !userList.isEmpty()) {
                        for(User u : userList) { 
                %>
                <tr>
                    <td><strong>#<%= u.getId() %></strong></td>
                    <td><%= u.getFullName() %></td>
                    <td><%= u.getEmail() %></td>
                    <td><%= u.getPhone() != null ? u.getPhone() : "Not Provided" %></td>
                    <td>
                        <!-- Role ke hisaab se badge ka color change hoga -->
                        <span class="role-badge <%= "admin".equalsIgnoreCase(u.getRole()) ? "admin" : "member" %>">
                            <%= u.getRole().toUpperCase() %>
                        </span>
                    </td>
                </tr>
                <%      }
                    } else {
                %>
                <tr>
                    <td colspan="5" style="text-align:center; color:red;">No members found in the database.</td>
                </tr>
                <%  } %>
            </tbody>
        </table>
    </div>

</body>
</html>