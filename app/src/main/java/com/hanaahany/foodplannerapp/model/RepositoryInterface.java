package com.hanaahany.foodplannerapp.model;

import androidx.lifecycle.LiveData;

import com.hanaahany.foodplannerapp.network.NetworkCallBack;

import java.util.List;

public interface RepositoryInterface {

    //Fav
    //for remotely data
    void makeNetworkCall(NetworkCallBack networkCallBack, int id);
    //get meal from database
    LiveData<List<Meal>> getStoredMeals();

    //Insert meals to database
    void insertMealFav(Meal meal);

    //delete meals from database
    void deleteMealFav(Meal meal);


    //Plan
    LiveData<List<Meal>> getMealsOfDaySat();
    LiveData<List<Meal>> getMealsOfDaySun();
    LiveData<List<Meal>> getMealsOfDayMon();
    LiveData<List<Meal>> getMealsOfDayTue();
    LiveData<List<Meal>> getMealsOfDayWed();
    LiveData<List<Meal>> getMealsOfDayThu();
    LiveData<List<Meal>> getMealsOfDayFri();
    void updateDayOfMeal(String id, String day);

}
