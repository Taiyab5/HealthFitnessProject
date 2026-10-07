package org.example.project.dao;

import org.example.project.bean.Nutrition;

public interface NutritionDAO {
    
    // Din ke hisaab se diet plan fetch karne ka method
    Nutrition getNutritionByDay(String dayOfWeek);
    
}