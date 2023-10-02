package com.hanaahany.foodplannerapp.db;

import androidx.lifecycle.LiveData;

import com.hanaahany.foodplannerapp.model.Meal;

import java.util.List;

public interface IConcreteLocalSource {

    //get Meals from database
    LiveData<List<Meal>> getStoredMeals();

    //Insert meal to database
    void insertMeal(Meal meal);
}
