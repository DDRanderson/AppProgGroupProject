package com.example.leftovertracker;

public class LeftoverList {
    String leftoverFoodName;
    String leftoverDaystoEat;

    public LeftoverList(String leftoverFoodName, String leftoverDaystoEat) {
        this.leftoverFoodName = leftoverFoodName;
        this.leftoverDaystoEat = leftoverDaystoEat;
    }

    public String getLeftoverFoodName() {
        return leftoverFoodName;
    }

    public String getLeftoverDaystoEat() {
        return leftoverDaystoEat;
    }
}
