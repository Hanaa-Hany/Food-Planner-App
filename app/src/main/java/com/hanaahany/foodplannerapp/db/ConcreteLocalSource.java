package com.hanaahany.foodplannerapp.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.hanaahany.foodplannerapp.chooseday.view.ChooseDayFragment;
import com.hanaahany.foodplannerapp.model.Meal;

import java.util.List;

public class ConcreteLocalSource implements IConcreteLocalSource{
    private MealDAO mealDAO;
    private LiveData<List<Meal>>storedMeals;
    private LiveData<List<Meal>>storedMealsPlanSat;
    private LiveData<List<Meal>>storedMealsPlanSun;
    private LiveData<List<Meal>>storedMealsPlanMon;
    private LiveData<List<Meal>>storedMealsPlanTue;
    private LiveData<List<Meal>>storedMealsPlanWed;
    private LiveData<List<Meal>>storedMealsPlanThu;
    private LiveData<List<Meal>>storedMealsPlanFri;
    Context context;
    private static ConcreteLocalSource localSource=null;

    private ConcreteLocalSource( Context context){
        this.context=context;
        AppDataBase appDataBase=AppDataBase.getInstance(context);
        mealDAO=appDataBase.getMealDAO();
        storedMeals=mealDAO.getMeals();
        storedMealsPlanSat=mealDAO.getMealsOfDay("Saturday");
        storedMealsPlanSun=mealDAO.getMealsOfDay("Sunday");
        storedMealsPlanMon=mealDAO.getMealsOfDay("Monday");
        storedMealsPlanTue=mealDAO.getMealsOfDay("Tuesday");
        storedMealsPlanWed=mealDAO.getMealsOfDay("Wednesday");
        storedMealsPlanThu=mealDAO.getMealsOfDay("Thursday");
        storedMealsPlanFri=mealDAO.getMealsOfDay("Friday");



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
    public LiveData<List<Meal>> getMealsOfDaySaturday() {
        return storedMealsPlanSat;
    }

    @Override
    public LiveData<List<Meal>> getMealsOfDaySunday() {
        return storedMealsPlanSun;
    }

    @Override
    public LiveData<List<Meal>> getMealsOfDayMonday() {
        return storedMealsPlanMon;
    }

    @Override
    public LiveData<List<Meal>> getMealsOfDayTuesday() {
        return storedMealsPlanTue;
    }

    @Override
    public LiveData<List<Meal>> getMealsOfDayWednseday() {
        return storedMealsPlanWed;
    }

    @Override
    public LiveData<List<Meal>> getMealsOfDayThursday() {
        return storedMealsPlanThu;
    }

    @Override
    public LiveData<List<Meal>> getMealsOfDayFriday() {
        return storedMealsPlanFri;
    }

//    @Override
//    public LiveData<List<Meal>> getMealsOfDay(String day) {
//        return storedMealsPlan;
//    }



    @Override
    public void updateDayOfMeal(String id, String day) {
        mealDAO.updateColumnDay(id,day);
    }


}
