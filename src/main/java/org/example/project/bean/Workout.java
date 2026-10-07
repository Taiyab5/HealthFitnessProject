package org.example.project.bean;

public class Workout {
    
    // 1. Private variables matching the database columns
    private int workoutId;
    private String planName;
    private String dayOfWeek;
    private String exercises;
    private String targetMuscle;

    // 2. Default Constructor
    public Workout() {
    }

    // 3. Parameterized Constructor
    public Workout(int workoutId, String planName, String dayOfWeek, String exercises, String targetMuscle) {
        this.workoutId = workoutId;
        this.planName = planName;
        this.dayOfWeek = dayOfWeek;
        this.exercises = exercises;
        this.targetMuscle = targetMuscle;
    }

    // 4. Getters and Setters
    public int getWorkoutId() { return workoutId; }
    public void setWorkoutId(int workoutId) { this.workoutId = workoutId; }

    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public String getExercises() { return exercises; }
    public void setExercises(String exercises) { this.exercises = exercises; }

    public String getTargetMuscle() { return targetMuscle; }
    public void setTargetMuscle(String targetMuscle) { this.targetMuscle = targetMuscle; }
}