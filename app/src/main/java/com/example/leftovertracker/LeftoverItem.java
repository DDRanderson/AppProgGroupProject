package com.example.leftovertracker;

public class LeftoverItem {
    private int id;
    private String name;
    private double calories;
    private double servings;
    private int daysLeft;

    public LeftoverItem(int id, String name, double calories, double servings, int daysLeft) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.servings = servings;
        this.daysLeft = daysLeft;
    }

    public LeftoverItem(int id, String name, int daysLeft) {
        this.id = id;
        this.name = name;
        this.daysLeft = daysLeft;
    }

    public int getId() {
        return id;
    }

    private void setId() {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getServings() {
        return servings;
    }

    public void setServings(double servings) {
        this.servings = servings;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }
}
