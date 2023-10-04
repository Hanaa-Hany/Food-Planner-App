package com.hanaahany.foodplannerapp.model;

import androidx.lifecycle.LiveData;

import com.hanaahany.foodplannerapp.db.ConcreteLocalSource;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;
import com.hanaahany.foodplannerapp.network.RemoteSource;

import java.util.List;

public class Repository implements RepositoryInterface{
    RemoteSource remoteSource;
    ConcreteLocalSource localSource;
    private  static Repository repository=null;

    public Repository(RemoteSource remoteSource, ConcreteLocalSource localSource) {
        this.remoteSource = remoteSource;
        this.localSource=localSource;
    }

    public static Repository getInstance(RemoteSource remoteSource,ConcreteLocalSource localSource){
        if (repository==null){
            repository=new Repository(remoteSource,localSource);
        }
        return repository;
    }



    @Override
    public void makeNetworkCall(NetworkCallBack networkCallBack, int id) {
        remoteSource.makeNetworkCallCategories(networkCallBack,id);
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return localSource.getStoredMeals();
    }

    @Override
    public void insertMealFav(Meal meal) {
        localSource.insertMeal(meal);
    }

    @Override
    public void deleteMealFav(Meal meal) {

    }

    @Override
    public LiveData<List<Meal>> getMealsOfDay(String day) {
        return localSource.getMealsOfDay(day);
    }

    @Override
    public void updateDayOfMeal(String id, String day) {
        localSource.updateDayOfMeal(id,day);
    }


}
