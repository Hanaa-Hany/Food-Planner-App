package com.hanaahany.foodplannerapp.favmeal.view;

import com.hanaahany.foodplannerapp.model.Meal;

public interface OnFavClick {
    void getMealDetails(String id);
    void deleteMeal(Meal meal);
}
