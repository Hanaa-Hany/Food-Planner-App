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
    //Insert meal to database
    void insertMealPlan(Meal meal);



    LiveData<List<Meal>> getMealsOfDaySaturday();
    LiveData<List<Meal>> getMealsOfDaySunday();
    LiveData<List<Meal>> getMealsOfDayMonday();
    LiveData<List<Meal>> getMealsOfDayTuesday();
    LiveData<List<Meal>> getMealsOfDayWednseday();
    LiveData<List<Meal>> getMealsOfDayThursday();
    LiveData<List<Meal>> getMealsOfDayFriday();

    void updateDayOfMeal(String id, String day);
}
