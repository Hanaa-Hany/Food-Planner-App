package com.hanaahany.foodplannerapp.model;

import androidx.lifecycle.LiveData;

import com.hanaahany.foodplannerapp.network.NetworkCallBack;

import java.util.List;

public interface RepositoryInterface {

    //for remotely data
    void makeNetworkCall(NetworkCallBack networkCallBack, int id);
    //get meal from database
    LiveData<List<Meal>> getStoredMeals();

    //Insert meals to database
    void insertMeal(Meal meal);

}
