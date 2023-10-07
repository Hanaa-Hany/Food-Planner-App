package com.hanaahany.foodplannerapp.favmeal.presenter;

import com.hanaahany.foodplannerapp.model.Meal;

public interface  FavPresenterInterface {
    void getFavMeal();
    void deleteMeal(Meal meal);
     void backupFav(Meal meal);
}
