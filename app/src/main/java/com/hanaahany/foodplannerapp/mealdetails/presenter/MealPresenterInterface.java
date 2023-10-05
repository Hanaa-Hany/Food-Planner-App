package com.hanaahany.foodplannerapp.mealdetails.presenter;

import com.hanaahany.foodplannerapp.model.Meal;

public interface MealPresenterInterface {
    //Fav
    void getMeal();
    void insertMealToFavourite(Meal meal);
    void deleteMeal(Meal meal);

    //Plan
    void getMeal(String day);
//    void updateDayOfMeal(String id, String day);
//    void getMealById(String id);
//    void getMealPlan();
//    void insertMealToPlan(Meal meal,String day);
//    void deleteMealPlan(Meal meal,String id);
}
