package com.hanaahany.foodplannerapp.plan.presenter;

import com.hanaahany.foodplannerapp.model.Meal;

public interface PlanPresenterInterface {
    //Plan
    void getMealSat();
    void getMealSun();
    void getMealMon();
    void getMealTue();
    void getMealWed();
    void getMealThu();
    void getMealFri();
    void deleteMeal(Meal meal);
}
