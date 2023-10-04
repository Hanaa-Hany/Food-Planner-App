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
    LiveData<List<Meal>> getMealsOfDay(String day);
    void updateDayOfMeal(String id, String day);

}
