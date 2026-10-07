<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gym Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
        .header { display: flex; justify-content: space-between; align-items: center; background: #333; color: white; padding: 15px 30px; border-radius: 8px; }
        .logout-btn { background-color: #d32f2f; color: white; text-decoration: none; padding: 10px 15px; border-radius: 4px; font-weight: bold; }
        .logout-btn:hover { background-color: #b71c1c; }
        
        .container { max-width: 1000px; margin: 30px auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }
        
        .days-menu { display: flex; gap: 10px; margin-bottom: 30px; justify-content: center; flex-wrap: wrap; }
        .day-link { text-decoration: none; padding: 10px 20px; background: #eee; color: #333; border-radius: 20px; font-weight: bold; transition: 0.3s; }
        .day-link:hover { background: #1976d2; color: white; }
        /* Jo din select hoga usko blue dikhayenge */
        .active-day { background: #1976d2; color: white; }

        .workout-card { border: 1px solid #ddd; padding: 20px; border-radius: 8px; background: #fafafa; }
        .workout-title { color: #d32f2f; margin-top: 0; }
        .muscle-badge { display: inline-block; background: #333; color: white; padding: 5px 10px; border-radius: 4px; font-size: 14px; margin-bottom: 15px; }
        .exercises { font-size: 16px; line-height: 1.6; color: #555; }
    </style>
</head>
<body>

    <%-- Security Check --%>
    <% 
        if(session.getAttribute("currentUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>

    <!-- Top Header -->
    <div class="header">
        <h2>Welcome boss ${currentUser.fullName}!</h2>
        <a href="UserController?action=logout" class="logout-btn">Logout</a>
    </div>

    <!-- Main Content -->
    <div class="container">
        <h3 style="text-align:center;">Your Weekly Workout Plan</h3>
        
        <!-- Days Menu (Buttons) -->
        <div class="days-menu">
            <a href="WorkoutController?day=Monday" class="day-link ${selectedDay == 'Monday' ? 'active-day' : ''}">Monday</a>
            <a href="WorkoutController?day=Tuesday" class="day-link ${selectedDay == 'Tuesday' ? 'active-day' : ''}">Tuesday</a>
            <a href="WorkoutController?day=Wednesday" class="day-link ${selectedDay == 'Wednesday' ? 'active-day' : ''}">Wednesday</a>
            <a href="WorkoutController?day=Thursday" class="day-link ${selectedDay == 'Thursday' ? 'active-day' : ''}">Thursday</a>
            <a href="WorkoutController?day=Friday" class="day-link ${selectedDay == 'Friday' ? 'active-day' : ''}">Friday</a>
            <a href="WorkoutController?day=Saturday" class="day-link ${selectedDay == 'Saturday' ? 'active-day' : ''}">Saturday</a>
        </div>

        <!-- Workout Plan Display Area -->
        <div class="workout-card">
            <%-- Check karte hain ki kya us din ka data database mein mila ya nahi --%>
            <% if(request.getAttribute("currentWorkout") != null) { %>
                
                <h2 class="workout-title">${currentWorkout.planName}</h2>
                <div class="muscle-badge">Target: ${currentWorkout.targetMuscle}</div>
                
                <h4>Exercises:</h4>
                <div class="exercises">
                    <%-- Exercise text ko HTML format mein dikhane ke liye --%>
                    ${currentWorkout.exercises}
                </div>

            <% } else { %>
                
                <h2 class="workout-title" style="color:#666;">Rest Day or No Plan Assigned</h2>
                <p>Aaj ke liye koi specific workout plan nahi hai. Enjoy your rest or do some light cardio!</p>
                
            <% } %>
            <!-- Nutrition Plan Display Area -->
        <div class="workout-card" style="margin-top: 30px; border-left: 5px solid #4CAF50;">
            <h2 class="workout-title" style="color: #4CAF50;">Daily Nutrition Plan</h2>
            
            <% if(request.getAttribute("currentNutrition") != null) { %>
                
                <div style="display: flex; gap: 15px; margin-bottom: 20px;">
                    <div class="muscle-badge" style="background: #e8f5e9; color: #2e7d32;">🔥 Calories: ${currentNutrition.totalCalories} kcal</div>
                    <div class="muscle-badge" style="background: #e3f2fd; color: #1565c0;">🥩 Protein: ${currentNutrition.totalProtein}g</div>
                </div>
                
                <h4>🍳 Breakfast:</h4>
                <p class="exercises">${currentNutrition.breakfast}</p>
                
                <h4>🍛 Lunch:</h4>
                <p class="exercises">${currentNutrition.lunch}</p>
                
                <h4>🥗 Dinner:</h4>
                <p class="exercises">${currentNutrition.dinner}</p>

            <% } else { %>
                <p>Aaj ke liye koi specific diet plan nahi hai. Keep eating healthy and stay hydrated!</p>
            <% } %>
        </div>
        </div>
    </div>

</body>
</html>