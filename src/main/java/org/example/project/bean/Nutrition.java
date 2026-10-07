package org.example.project.bean;

public class Nutrition {
    
    // 1. Private variables matching the database columns
    private int nutritionId;
    private String dayOfWeek;
    private String breakfast;
    private String lunch;
    private String dinner;
    private int totalCalories;
    private int totalProtein;

    // 2. Default Constructor
    public Nutrition() {
    }

    // 3. Parameterized Constructor
    public Nutrition(int nutritionId, String dayOfWeek, String breakfast, String lunch, String dinner, int totalCalories, int totalProtein) {
        this.nutritionId = nutritionId;
        this.dayOfWeek = dayOfWeek;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.totalCalories = totalCalories;
        this.totalProtein = totalProtein;
    }

    // 4. Getters and Setters
    public int getNutritionId() { return nutritionId; }
    public void setNutritionId(int nutritionId) { this.nutritionId = nutritionId; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public String getBreakfast() { return breakfast; }
    public void setBreakfast(String breakfast) { this.breakfast = breakfast; }

    public String getLunch() { return lunch; }
    public void setLunch(String lunch) { this.lunch = lunch; }

    public String getDinner() { return dinner; }
    public void setDinner(String dinner) { this.dinner = dinner; }

    public int getTotalCalories() { return totalCalories; }
    public void setTotalCalories(int totalCalories) { this.totalCalories = totalCalories; }

    public int getTotalProtein() { return totalProtein; }
    public void setTotalProtein(int totalProtein) { this.totalProtein = totalProtein; }
}