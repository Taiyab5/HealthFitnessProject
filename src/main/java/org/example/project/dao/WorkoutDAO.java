package org.example.project.dao;

import org.example.project.bean.Workout;

public interface WorkoutDAO {
    
    // Din ke hisaab se workout fetch karne ka method
    Workout getWorkoutByDay(String dayOfWeek);
    
}