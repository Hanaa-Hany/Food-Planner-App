package com.hanaahany.foodplannerapp.db;

import androidx.lifecycle.LiveData;

import com.hanaahany.foodplannerapp.model.Meal;

import java.util.List;

public interface IConcreteLocalSource {

    //get Meals from database
    LiveData<List<Meal>> getStoredMeals();

    //Insert meal to database
    void insertMeal(Meal meal);

    //Delete meal to database
    void deleteMeal(Meal meal);



    LiveData<List<Meal>> getMealsOfDay(String day);
    void updateDayOfMeal(String id, String day);
}
