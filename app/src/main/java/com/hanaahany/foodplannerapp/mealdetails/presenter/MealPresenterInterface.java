package com.hanaahany.foodplannerapp.mealdetails.presenter;

import com.hanaahany.foodplannerapp.model.Meal;

public interface MealPresenterInterface {
    void getMeal();
    void insertMealToFavourite(Meal meal);
}
