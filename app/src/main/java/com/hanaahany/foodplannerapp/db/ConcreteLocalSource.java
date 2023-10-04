package com.hanaahany.foodplannerapp.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.hanaahany.foodplannerapp.model.Meal;

import java.util.List;

public class ConcreteLocalSource implements IConcreteLocalSource{
    private MealDAO mealDAO;
    private LiveData<List<Meal>>storedMeals;
    Context context;
    private static ConcreteLocalSource localSource=null;

    private ConcreteLocalSource( Context context){
        this.context=context;
        AppDataBase appDataBase=AppDataBase.getInstance(context);
        mealDAO=appDataBase.getMealDAO();
        storedMeals=mealDAO.getMeals();



    }
    public static ConcreteLocalSource getInstance( Context context){
        if (localSource==null){
            localSource=new ConcreteLocalSource(context);
        }
        return localSource;
    }

    //Fav
    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return storedMeals;
    }

    @Override
    public void insertMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertMeal(meal);
            }
        }).start();
    }

    @Override
    public void deleteMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteMeal(meal);
            }
        }).start();
    }

    @Override
    public LiveData<List<Meal>> getMealsOfDay(String day) {
        return mealDAO.getMealsOfDay(day);
    }

    @Override
    public void updateDayOfMeal(String id, String day) {
        mealDAO.updateColumnDay(id,day);
    }


}
